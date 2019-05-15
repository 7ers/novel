package com.demo.novel.controller;

import com.demo.novel.entity.LoginLog;
import com.demo.novel.entity.UserInfo;
import com.demo.novel.service.LoginLogService;
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
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Controller
public class HomeController {

    @Resource
    LoginLogService lLogService;

    @GetMapping("/login")
    public String login(){
        System.out.println("Get:HomeController.login");
        return "login";
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("Get:HomeController.test");
        return "test";
    }

    // 这里如果不写method参数的话，默认支持所有请求，如果想缩小请求范围，还是要添加method来支持get, post等等某个请求。
    @PostMapping("/login")
    public String login(HttpServletRequest request,UserInfo userInfo, Model model) throws Exception {
        System.out.println("HomeController.login");

        String redirect = "login";
        String msg = "登录成功";

        String username = userInfo.getUsername();
        String password = userInfo.getPwd();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            msg = "用户名或密码不能为空！";
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);

        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(username);
        loginLog.setCreatetime(Calendar.getInstance().getTime());
        try {
            subject.login(token);
            redirect = "redirect:admin";
            loginLog.setLastupdatetime(Calendar.getInstance().getTime());
        }catch (LockedAccountException lae) {
            token.clear();
            msg = "用户已经被锁定不能登录，请与管理员联系！";
            redirect = "login";
        } catch (AuthenticationException e) {
            token.clear();
            msg = "用户或密码不正确！";
            redirect = "login";
        }
        request.setAttribute("msg",msg);
        loginLog.setMsg(msg);
        loginLog.setClientip(request.getHeader("x-forwarded-for"));
        lLogService.writeLog(loginLog);
        return redirect;
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
