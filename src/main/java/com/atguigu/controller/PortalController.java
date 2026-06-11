package com.atguigu.controller;

import com.atguigu.pojo.portalVo;
import com.atguigu.service.HeadlineService;
import com.atguigu.service.TypeService;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("portal")
@ResponseBody
@CrossOrigin
public class PortalController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private HeadlineService headlineService;
    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result allTypes = typeService.findAllTypes();
        return allTypes;
    }
    @PostMapping("findNewsPage")
        public Result findNewsPage(@RequestBody portalVo Portalvo){
        Result newsPage = headlineService.findNewsPage(Portalvo);
        return newsPage;
    }
    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail( int hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
