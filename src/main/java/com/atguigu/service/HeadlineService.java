package com.atguigu.service;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.portalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 钱力川
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2026-04-18 20:23:53
*/
public interface HeadlineService extends IService<Headline> {

     Result showHeadlineDetail(int hid);

    Result findNewsPage(portalVo portalvo);

    Result publish(Headline headline);

    Result findHeadlineByHid(Integer hid);

    Result updateHeadline(Headline headline);
}
