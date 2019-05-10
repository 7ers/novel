package com.demo.novel.service.impl;

import com.demo.novel.dao.UserBalanceMapper;
import com.demo.novel.entity.UserBalance;
import com.demo.novel.service.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceServiceImpl implements UserBalanceService {

    @Autowired
    UserBalanceMapper userBalanceMapper;

    @Override
    public UserBalance selectByUserName(String username) {
        return userBalanceMapper.selectByUserName(username);
    }

    @Override
    public int updateUserBalance(UserBalance userBalance) {
        return userBalanceMapper.updateByPrimaryKey(userBalance);
    }

    @Override
    public int addUserBalance(UserBalance userBalance) {
        return userBalanceMapper.addUserBalance(userBalance);
    }
}
