package com.atguigu.service.impl;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.atguigu.pojo.portalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.mapper.HeadlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 钱力川
 * @description 针对表【news_headline】的数据库操作Service实现
 * @createDate 2026-04-18 20:23:53
 */
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {
    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result showHeadlineDetail(int hid) {
        Map headLineDetail = headlineMapper.selectDetailMap(hid);
        //2.拼接头条对象(阅读量和version)进行数据更新
        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer) headLineDetail.get("pageViews")+1); //阅读量+1
        headline.setVersion((Integer) headLineDetail.get("version")); //设置版本
        headlineMapper.updateById(headline);

        Map<String,Object> pageInfoMap=new HashMap<>();
        pageInfoMap.put("headline",headLineDetail);
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result findNewsPage(portalVo portalvo) {
        IPage<Map> page = new Page<>(portalvo.getPageNum(), portalvo.getPageSize());
        headlineMapper.MyIpageSelect((Page) page, portalvo);
        Map<String,Object> pageInfo =new HashMap<>();
        pageInfo.put("pageData",page.getRecords());
        pageInfo.put("pageNum",page.getCurrent());
        pageInfo.put("pageSize",page.getSize());
        pageInfo.put("totalPage",page.getPages());
        pageInfo.put("totalSize",page.getTotal());

        Map<String,Object> pageInfoMap=new HashMap<>();
        pageInfoMap.put("pageInfo",pageInfo);
        // 响应JSON
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result publish(Headline headline) {
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(Integer hid) {
        Headline headline = headlineMapper.selectById(hid);
        Map map = new HashMap();
        map.put("headline",headline);
        return Result.ok(map);
    }

    @Override
    public Result updateHeadline(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setUpdateTime(new Date());
        headline.setVersion(version);
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }
}





