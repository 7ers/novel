package com.demo.novel.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
    @RequestMapping("/")
    private String goIndex(){
        return "index";
    }

    @RequestMapping("/test")
    private String test(){
        return "test";
    }
}
