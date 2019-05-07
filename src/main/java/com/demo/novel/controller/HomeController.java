package com.demo.novel.controller;

import com.demo.novel.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(){
        System.out.println("Get:HomeController.login");
        return "login";
    }

    // 这里如果不写method参数的话，默认支持所有请求，如果想缩小请求范围，还是要添加method来支持get, post等等某个请求。
    @PostMapping("/login")
    public String login(HttpServletRequest request,UserInfo userInfo, Model model) throws Exception {
        System.out.println("HomeController.login");

        String username = userInfo.getUsername();
        String password = userInfo.getPwd();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return "redirect:admin";
        }catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或密码不正确！");
            return "login";
        }
    }

    @RequestMapping({"/admin"})
    public String index(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        System.out.println("HomeController.index");
        model.addAttribute("userInfo",session.getAttribute("userSession"));
        model.addAttribute("userAvatar","/adminlte/dist/img/avatar.png");
        return"layouthome";
    }

    @RequestMapping("/profile")
    public String goProfile(){
        System.out.println("----用户信息----");
        return "profile";
    }

    @RequestMapping("/content")
    public String novelManagement(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        model.addAttribute("userInfo",session.getAttribute("userSession"));
        model.addAttribute("userAvatar","/adminlte/dist/img/avatar.png");
        return "content/novelmanagement";
    }

    @RequestMapping("/users")
    public String userManagement(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        model.addAttribute("userInfo",session.getAttribute("userSession"));
        model.addAttribute("userAvatar","/adminlte/dist/img/avatar.png");
        return "user/usermanagement";
    }

    @RequestMapping("/roles")
    public String roleManagement(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        model.addAttribute("userInfo",session.getAttribute("userSession"));
        model.addAttribute("userAvatar","/adminlte/dist/img/avatar.png");
        return "user/rolemanagement";
    }

    @RequestMapping("/perms")
    public String permsManagement(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        model.addAttribute("userInfo",session.getAttribute("userSession"));
        model.addAttribute("userAvatar","/adminlte/dist/img/avatar.png");
        return "user/permsmanagement";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}
