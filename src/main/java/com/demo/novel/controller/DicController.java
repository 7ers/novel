package com.demo.novel.controller;

import com.demo.novel.service.CommonDicService;
import com.demo.novel.util.Constants;
import com.demo.novel.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dic")
public class DicController {
    @Resource
    CommonDicService dicService;
    @GetMapping("/type")
    public JsonResult typeList(String type) {
        JsonResult jr = new JsonResult(Constants.RET_CODE_00000,Constants.RET_DESC_00000);
        jr.setObj(dicService.queryDicList(type));
        return jr;
    }
}
