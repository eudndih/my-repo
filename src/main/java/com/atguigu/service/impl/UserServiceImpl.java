package com.atguigu.service.impl;

import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 钱力川
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2026-04-18 20:23:53
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper  jwtHelper;
    @Override
    public Result<Object> login(User user) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        User u = userMapper.selectOne(wrapper);
        if(u==null){
           return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if(!MD5Util.encrypt(user.getUserPwd()).equals(u.getUserPwd())){
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
        String token = jwtHelper.createToken(Long.valueOf(u.getUid()));
        Map map =new HashMap();
        map.put("token",token);
        return Result.ok(map);
    }

    @Override
    public Result getUserInfo(String token) {
        if(jwtHelper.isExpiration(token)){
            System.out.println("过期");
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        int userId = jwtHelper.getUserId(token).intValue();
        //一定要给实体类的主键加注解！！！
        User user = userMapper.selectById(userId);
        if(user==null){
            System.out.println("没查到数据");
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        user.setUserPwd("");
        Map<String,Object> map = new HashMap<>();
        map.put("loginUser",user);
        return Result.ok(map);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        Long rows = userMapper.selectCount(wrapper);
        if(rows>0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok(null);
    }

    @Override
    public Result regist(User user) {
        String username=user.getUsername();
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        Long rows = userMapper.selectCount(wrapper);
        if(rows>0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int row= userMapper.insert(user);
            return Result.ok(null);
    }
}




