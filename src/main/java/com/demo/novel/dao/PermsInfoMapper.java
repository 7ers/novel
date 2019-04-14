package com.demo.novel.dao;

import com.demo.novel.entity.PermsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermsInfoMapper {
    //根据角色ID查询角色对应的权限信息
    List<PermsInfo> findPermissionByRoleId(@Param("roleId") Integer roleId);
}