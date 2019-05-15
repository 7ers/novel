package com.demo.novel.service;

import com.demo.novel.entity.UserBalance;

public interface UserBalanceService {
    UserBalance selectByUserName(String username);
    int updateUserBalance(UserBalance userBalance, String type);
    int addUserBalance(UserBalance userBalance);
}
