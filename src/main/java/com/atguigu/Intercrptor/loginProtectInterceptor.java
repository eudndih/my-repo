package com.atguigu.Intercrptor;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class loginProtectInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        boolean expiration = jwtHelper.isExpiration(token);
        if(!expiration){
            return true;
        }
        Result<Object> build = Result.build(null, ResultCodeEnum.NOTLOGIN);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(build);
        response.getWriter().write(json);
        return false;
    }
}
