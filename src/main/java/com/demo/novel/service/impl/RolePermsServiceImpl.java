package com.demo.novel.service.impl;

import com.demo.novel.dao.RolePermsMapper;
import com.demo.novel.dao.UserRoleMapper;
import com.demo.novel.entity.RolePerms;
import com.demo.novel.entity.UserRole;
import com.demo.novel.service.RolePermsService;
import com.demo.novel.shiro.NovelRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RolePermsServiceImpl implements RolePermsService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    /*@Resource
    private ShiroService shiroService;*/
    @Autowired
    private NovelRealm myShiroRealm;

    @Autowired
    private RolePermsMapper rolePermsMapper;

    @Override
    //更新权限
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void addRolePerms(RolePerms rolePerms) {
        //删除
        rolePermsMapper.deleteByRoleId(rolePerms.getRoleid());
        //添加
        if(!StringUtils.isEmpty(rolePerms.getPermsid())){
            String[] resourcesArr = rolePerms.getPermsid().split(",");
            for(String permsId:resourcesArr ){
                RolePerms rp = new RolePerms();
                rp.setRoleid(rolePerms.getRoleid());
                rp.setPermsid(permsId);
                rolePermsMapper.insert(rp);
            }
        }
        List<Integer> userIds= userRoleMapper.findUserIdByRoleId(rolePerms.getRoleid());
        //更新当前登录的用户的权限缓存
        myShiroRealm.clearUserAuthByUserId(userIds);
    }
}
