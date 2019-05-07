package com.demo.novel.dao;

import com.demo.novel.entity.RoleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleInfoMapper {
    //通过username查找用户角色信息
    List<RoleInfo> findRoleByUsername(String username);
    List<RoleInfo> queryRoleListWithSelected(Integer userid);

    List<RoleInfo> queryRoleList();

    int addRole(RoleInfo roleInfo);

    int deleteByRoleId(Integer id);

    RoleInfo queryRoleByRoleDesc(String roledesc);
}