package com.demo.novel.dao;

import com.demo.novel.entity.UserInfo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    //通过username查找用户信息
    UserInfo findByUsername(String username);
    List<UserInfo> selectByEntity(UserInfo userInfo);
    int addUser(UserInfo userInfo);
    int deleteByUserId(Integer userid);
}