package com.demo.novel.controller;

import com.demo.novel.entity.PermsInfo;
import com.demo.novel.service.PermsInfoService;
import com.demo.novel.shiro.ShiroService;
import com.demo.novel.util.Constants;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/perms")
public class PermsInfoController {

    @Autowired
    PermsInfoService permsInfoService;

    @Autowired
    ShiroService shiroService;

    @RequestMapping("/permsList")
    public Map<String,Object> permsList(String draw,
                                     @RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<PermsInfo> pageInfo = permsInfoService.selectByPage(start, length);
        System.out.println("pageInfo.getTotal():"+pageInfo.getTotal());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @RequestMapping("/permsWithSelected")
    public List<PermsInfo> rolesWithSelected(Integer roleid){
        List list = permsInfoService.queryPermsListByRoleID(roleid);
        return list;
    }

    @PostMapping("/add")
    public String add(PermsInfo permsInfo) {
        try{
            permsInfoService.addPerms(permsInfo);
            //更新权限
            shiroService.updatePermission();
            return Constants.RET_CODE_SUCCESS;
        }catch (Exception e){
            return Constants.RET_CODE_FAIL;
        }
    }

    @PostMapping("/delete")
    public String delById(Integer permsid){
        try{
            permsInfoService.delById(permsid);
            return Constants.RET_CODE_SUCCESS;
        }catch (Exception e){
            return Constants.RET_CODE_FAIL;
        }
    }
}
