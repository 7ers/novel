package com.demo.novel.dao;

import com.demo.novel.entity.RoleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleInfoMapper {
    //通过username查找用户角色信息
    List<RoleInfo> findRoleByUsername(@Param("username") String username);
}