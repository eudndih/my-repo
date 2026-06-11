package com.atguigu.service;

import com.atguigu.pojo.Type;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 钱力川
* @description 针对表【news_type】的数据库操作Service
* @createDate 2026-04-18 20:23:53
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
