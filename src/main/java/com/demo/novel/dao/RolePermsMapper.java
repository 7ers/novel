package com.demo.novel.dao;

import com.demo.novel.entity.RolePerms;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermsMapper {
    void deleteByRoleId(int roleid);
    void insert(RolePerms rolePerms);
}