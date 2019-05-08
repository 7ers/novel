package com.demo.novel.controller;

import com.demo.novel.entity.RoleInfo;
import com.demo.novel.entity.UserInfo;
import com.demo.novel.entity.UserRole;
import com.demo.novel.service.RoleInfoService;
import com.demo.novel.service.UserInfoService;
import com.demo.novel.service.UserRoleService;
import com.demo.novel.util.Constants;
import com.demo.novel.util.PasswordUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleInfoService roleInfoService;

    /**
     * 用户查询.
     * @return 用户信息页面
     */
    @RequestMapping("/userList")
//    @RequiresPermissions("userInfo:view")//权限管理;
    public Map<String, Object> userList(
            @RequestParam(required = false, defaultValue = "1") String draw,
            @RequestParam(required = false, defaultValue = "1") int start,
            @RequestParam(required = false, defaultValue = "10") int length,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String state) {
        System.out.println("queryuserlist");
        Map<String, Object> map = new HashMap<>();
        List<UserInfo> userInfoList;
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setState(state);
        PageInfo<UserInfo> pageInfo = userInfoService.queryUserList(userInfo,start,length);
        map.put("draw", draw);
        map.put("recordsTotal", pageInfo.getTotal());
        map.put("recordsFiltered", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 用户添加;
     * @return 用户添加页面
     */
    @RequestMapping("/add")
//    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(UserInfo userInfo){
        UserInfo u = userInfoService.findByUsername(userInfo.getUsername());
        if(u != null)
            return Constants.RET_CODE_ERROR;
        try {
            userInfo.setState("1");
            PasswordUtils passwordUtils = new PasswordUtils();
            passwordUtils.encryptPassword(userInfo);
            userInfoService.addUser(userInfo);
            return Constants.RET_CODE_SUCCESS;
        } catch (Exception e) {
            return Constants.RET_CODE_FAIL;
        }
    }

    /**
     * 保存用户角色
     * @param userRole 用户角色
     *  	  此处获取的参数的角色id是以 “,” 分隔的字符串
     * @return
     */
    @RequestMapping("/saveUserRoles")
    public String saveUserRoles(UserRole userRole){
        if(StringUtils.isEmpty(userRole.getUserid()))
            return "error";
        try {
            userRoleService.addUserRole(userRole);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }
    @RequestMapping("/rolesWithSelected")
    public List<RoleInfo> rolesWithSelected(Integer userid){
        return roleInfoService.queryRoleListWithSelected(userid);
    }


    /**
     * 用户删除;
     * @return 用户删除页面
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(Integer userid){
        if(StringUtils.isEmpty(userid))
            return Constants.RET_CODE_ERROR;
        try {
            userInfoService.deleteByUserId(userid);
            return Constants.RET_CODE_SUCCESS;
        } catch (Exception e) {
            return Constants.RET_CODE_FAIL;
        }
    }
}
