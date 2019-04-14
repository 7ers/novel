package com.demo.novel.controller;

import com.demo.novel.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HomeController {
    @RequestMapping({"/","/index"})
    public String index(){
        System.out.println("HomeController.index");
        return"layouthome";
    }

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        System.out.println("Get:HomeController.login");
        return "login";
    }

    // 这里如果不写method参数的话，默认支持所有请求，如果想缩小请求范围，还是要添加method来支持get, post等等某个请求。
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, Map<Object,Object> map) throws Exception {

        System.out.println("HomeController.login");

        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        Object exception = request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("账户不存在");
                msg = "账户不存在或密码不正确";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("密码不正确");
                msg = "账户不存在或密码不正确";
            } else if(LockedAccountException.class.getName().equals(exception)){
                System.out.println("用户状态不正确");
                msg = "用户已经被锁定不能登录，请与管理员联系！";
            }else {
                System.out.println("其他异常");
                msg = "其他异常";
            }
        }

        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "login";
    }

    @RequestMapping("/profile")
    public String goProfile(){
        System.out.println("----用户信息----");
        return "profile";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}
