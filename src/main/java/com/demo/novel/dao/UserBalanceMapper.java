package com.demo.novel.dao;

import com.demo.novel.entity.UserBalance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserBalanceMapper {
    int addUserBalance(UserBalance record);

    UserBalance selectByPrimaryKey(Long id);
    UserBalance selectByUserName(String username);

    int updateByPrimaryKey(UserBalance record);
}