package com.demo.novel.controller.app;

import com.demo.novel.entity.UserInfo;
import com.demo.novel.entity.UserRole;
import com.demo.novel.entity.UserSession;
import com.demo.novel.service.UserInfoService;
import com.demo.novel.service.UserRoleService;
import com.demo.novel.service.UserSessionService;
import com.demo.novel.util.Constants;
import com.demo.novel.util.JsonResult;
import com.demo.novel.util.PasswordUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class APPController {
    @Resource
    UserSessionService userSessionService;
    @Resource
    UserInfoService userInfoService;
    @Resource
    UserRoleService userRoleService;
    /**
     * appd端登录接口
     * @param username
     * @param password
     * @return Json对象{"code":"","msg":"","obj":""}
     */
    @PostMapping("/appLogin")
    public JsonResult appLogin(@RequestParam String username, @RequestParam String password){
        JsonResult jr = new JsonResult();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            jr.setCode(Constants.RET_CODE_E0001);
            jr.setMsg(Constants.RET_DESC_E0001);
            return jr;
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            UserInfo userInfo = userInfoService.findByUsername(username);
            UserSession inUs = new UserSession();
            inUs.setUserId(userInfo.getUserid());
            UserSession us = userSessionService.queryByEntity(inUs);
            if(us!=null){
                Map hm = new HashMap();
                hm.put("tocken",us.getCookieid());
                jr.setCode(Constants.RET_CODE_00000);
                jr.setMsg(Constants.RET_DESC_00000);
                jr.setObj(hm);
            }else{
                jr.setCode(Constants.RET_CODE_E0004);
                jr.setMsg(Constants.RET_DESC_E0004);
            }
            return jr;
        }catch (LockedAccountException lae) {
            token.clear();
            jr.setCode(Constants.RET_CODE_E0002);
            jr.setMsg(Constants.RET_DESC_E0002);
            return jr;
        } catch (AuthenticationException e) {
            token.clear();
            jr.setCode(Constants.RET_CODE_E0003);
            jr.setMsg(Constants.RET_DESC_E0003);
            return jr;
        }
    }

    /**
     * 用户注册，需要输入用户名、密码、邮箱及用户描述
     * @param userInfo
     * {username,//用户名
     * pwd,//密码
     * email,//邮箱
     * userdesc}//用户描述（可以默认送APP）
     * @return Json对象{"code":"","msg":"","obj":""}
     */
    public JsonResult register(UserInfo userInfo){
        UserInfo u = userInfoService.findByUsername(userInfo.getUsername());
        if(u != null)
            return new JsonResult(Constants.RET_CODE_E0005,Constants.RET_DESC_E0005);
        try {
            userInfo.setState("1");
            PasswordUtils passwordUtils = new PasswordUtils();
            passwordUtils.encryptPassword(userInfo);
            userInfoService.addUser(userInfo);
            userRoleService.addAPPUserRole(userInfo.getUserid());
            return new JsonResult(Constants.RET_CODE_00000,Constants.RET_DESC_00000);
        } catch (Exception e) {
            return new JsonResult(Constants.RET_CODE_E0006,Constants.RET_DESC_E0006);
        }
    }
}
