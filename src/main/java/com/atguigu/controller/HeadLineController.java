package com.atguigu.controller;

import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("headline")
@ResponseBody
@CrossOrigin//同端口需要跨域
public class HeadLineController {
    @Autowired
    private HeadlineService headlineService;
    @Autowired
    private JwtHelper jwtHelper;
    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token){
        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        Result publish = headlineService.publish(headline);
        return publish;
    }
    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid){
        Result headlineByHid = headlineService.findHeadlineByHid(hid);
        return headlineByHid;
    }
    @PostMapping("update")
    public Result updateHeadline(@RequestBody Headline headline){
        Result result = headlineService.updateHeadline(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeById(Integer hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}
