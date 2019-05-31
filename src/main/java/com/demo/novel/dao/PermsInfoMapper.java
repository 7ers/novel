package com.demo.novel.dao;

import com.demo.novel.entity.PermsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermsInfoMapper {
    //根据角色ID查询角色对应的权限信息
    List<PermsInfo> findPermissionByRoleId(Integer roleId);
    List<PermsInfo> queryAll();
    List<PermsInfo> loadUserPerms(Map<String,Object> map);

    int addPerms(PermsInfo permsInfo);

    int delById(int permsid);

    List<PermsInfo> selectByName(String permsname);

    List<PermsInfo> queryPermsListByRoleID(Integer roleid);
}