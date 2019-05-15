package com.demo.novel.dao;

import com.demo.novel.entity.LoginLog;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}