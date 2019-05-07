package com.demo.novel.controller;

import com.demo.novel.entity.RoleInfo;
import com.demo.novel.entity.RolePerms;
import com.demo.novel.service.RoleInfoService;
import com.demo.novel.service.RolePermsService;
import com.demo.novel.util.Constants;
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
@RequestMapping("/roles")
public class RoleInfoController {

    @Autowired
    private RoleInfoService roleInfoService;

    @Autowired
    private RolePermsService rolePermsService;

    /**
     * 用户查询.
     * @return 用户信息页面
     */
    @RequestMapping("/roleList")
//    @RequiresPermissions("userInfo:view")//权限管理;
    public Map<String, Object> roleList(
            @RequestParam(required = false, defaultValue = "1") String draw,
            @RequestParam(required = false, defaultValue = "1") int start,
            @RequestParam(required = false, defaultValue = "10") int length,
            @RequestParam(required = false) String roleid,
            @RequestParam(required = false) String state) {
        System.out.println("queryrolelist");
        Map<String, Object> map = new HashMap<>();
        List<RoleInfo> roleList;
        PageInfo<RoleInfo> pageInfo = roleInfoService.queryRoleList(start,length);
        map.put("draw", draw);
        map.put("recordsTotal", pageInfo.getTotal());
        map.put("recordsFiltered", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @RequestMapping("/rolesWithSelected")
    public List<RoleInfo> rolesWithSelected(Integer roleid){
        return roleInfoService.queryRoleListWithSelected(roleid);
    }

    //分配角色
    @RequestMapping("/saveRolePerms")
    public String saveRoleResources(RolePerms rolePerms){
        if(StringUtils.isEmpty(rolePerms.getRoleid()))
            return Constants.RET_CODE_ERROR;
        try {
            rolePermsService.addRolePerms(rolePerms);
            return Constants.RET_CODE_SUCCESS;
        } catch (Exception e) {
            return Constants.RET_CODE_FAIL;
        }
    }

    @RequestMapping(value = "/add")
    public String add(RoleInfo roleInfo) {
        try {
            if(StringUtils.isEmpty(roleInfo.getRoledesc())){
                return Constants.RET_CODE_FAIL;
            }
            RoleInfo role = roleInfoService.queryRoleByRoleDesc(roleInfo.getRoledesc());
            if(role!=null){
                return Constants.RET_CODE_ERROR;
            }
            roleInfoService.save(roleInfo);
            return Constants.RET_CODE_SUCCESS;
        } catch (Exception e) {
            return Constants.RET_CODE_FAIL;
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer roleid){
        try{
            roleInfoService.delete(roleid);
            return Constants.RET_CODE_SUCCESS;
        }catch (Exception e){
            return Constants.RET_CODE_FAIL;
        }
    }
}
