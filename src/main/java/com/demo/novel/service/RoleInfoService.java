package com.demo.novel.service;

import com.demo.novel.entity.RoleInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface RoleInfoService {
    List<RoleInfo> queryRoleListWithSelected(int roleid);

    PageInfo<RoleInfo> queryRoleList(int start, int length);

    int save(RoleInfo roleInfo);

    int delete(Integer id);

    RoleInfo queryRoleByRoleDesc(String roledesc);
}
