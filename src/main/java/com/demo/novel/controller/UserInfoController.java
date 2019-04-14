package com.demo.novel.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    /**
     * 用户查询.
     * @return 用户信息页面
     */
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")//权限管理;
    public String userInfo(){
        return "/user/userInfo";
    }

    /**
     * 用户添加;
     * @return 用户添加页面
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(){
        return "/user/userInfoAdd";
    }

    /**
     * 用户删除;
     * @return 用户删除页面
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "/user/userInfoDel";
    }
}
