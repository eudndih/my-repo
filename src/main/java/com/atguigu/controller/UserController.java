package com.atguigu.controller;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
@ResponseBody
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper jwtHelper;
    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result res = userService.login(user);
        return res;
    }
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result r = userService.getUserInfo(token);
        return r;
    }
    @PostMapping("checkUserName")
    public Result checkUserName(String username){
        Result result = userService.checkUserName(username);
        return result;
    }
    @PostMapping("regist")
    public Result regist(@RequestBody User user){
        Result regist = userService.regist(user);
        return regist;
    }
    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
            //没有传或者过期 未登录
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        return Result.ok(null);
    }
}
