package com.atguigu.mapper;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.portalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author 钱力川
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2026-04-18 20:23:53
* @Entity com.atguigu.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map> MyIpageSelect(Page page, @Param("portalvo") portalVo portalvo);

    Map selectDetailMap(Integer hid);
}




