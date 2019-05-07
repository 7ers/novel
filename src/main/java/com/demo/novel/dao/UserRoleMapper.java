package com.demo.novel.dao;

import com.demo.novel.entity.RoleInfo;
import com.demo.novel.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {
    List<Integer> findUserIdByRoleId(int roleid);
    int deleteByUserId(Integer userid);
    int insert(UserRole userRole);
}