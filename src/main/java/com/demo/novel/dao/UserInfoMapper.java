package com.demo.novel.dao;

import com.demo.novel.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {
    //通过username查找用户信息
    UserInfo findByUsername(@Param("username") String username);
}