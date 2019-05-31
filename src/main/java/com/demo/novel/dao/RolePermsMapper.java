package com.demo.novel.dao;

import com.demo.novel.entity.RolePerms;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface RolePermsMapper {
    void deleteByRoleId(int roleid);
    void insert(RolePerms rolePerms);
}