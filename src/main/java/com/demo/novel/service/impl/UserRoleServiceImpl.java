package com.demo.novel.service.impl;

import com.demo.novel.dao.UserRoleMapper;
import com.demo.novel.entity.UserRole;
import com.demo.novel.service.UserRoleService;
import com.demo.novel.shiro.NovelRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangqj on 2017/4/26.
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private NovelRealm myShiroRealm;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void addUserRole(UserRole userRole) {
        //删除
        userRoleMapper.deleteByUserId(userRole.getUserid());
        //添加
        String[] roleids = userRole.getRoleid().split(",");
        for (String roleId : roleids) {
            UserRole u = new UserRole();
            u.setUserid(userRole.getUserid());
            u.setRoleid(roleId);
            userRoleMapper.insert(u);
        }
        //更新当前登录的用户的权限缓存
        List<Integer> userid = new ArrayList<Integer>();
        userid.add(userRole.getUserid());
        myShiroRealm.clearUserAuthByUserId(userid);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void addAPPUserRole(Integer userid) {
        //删除
        userRoleMapper.deleteByUserId(userid);
        //添加
        UserRole u = new UserRole();
        u.setUserid(userid);
        u.setRoleid("2");
        userRoleMapper.insert(u);
        //更新当前登录的用户的权限缓存
        List<Integer> userArr = new ArrayList<Integer>();
        userArr.add(userid);
        myShiroRealm.clearUserAuthByUserId(userArr);
    }
}
