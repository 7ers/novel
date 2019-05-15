package com.demo.novel.dao;

import com.demo.novel.entity.UserBalance;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceMapper {
    int addUserBalance(UserBalance record);

    UserBalance selectByPrimaryKey(Long id);
    UserBalance selectByUserName(String username);

    int updateByPrimaryKey(UserBalance record);
}