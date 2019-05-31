package com.demo.novel.dao;

import com.demo.novel.entity.RoleInfo;
import com.demo.novel.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    List<Integer> findUserIdByRoleId(int roleid);
    int deleteByUserId(Integer userid);
    int insert(UserRole userRole);
}