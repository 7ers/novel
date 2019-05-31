package com.demo.novel.service.impl;

import com.demo.novel.dao.BalanceLogMapper;
import com.demo.novel.dao.UserBalanceMapper;
import com.demo.novel.entity.BalanceLog;
import com.demo.novel.entity.UserBalance;
import com.demo.novel.service.UserBalanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;

@Service
public class UserBalanceServiceImpl implements UserBalanceService {

    @Resource
    UserBalanceMapper userBalanceMapper;

    @Resource
    BalanceLogMapper balanceLogMapper;

    @Override
    public UserBalance selectByUserName(String username) {
        return userBalanceMapper.selectByUserName(username);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public int updateUserBalance(UserBalance userBalance, String type) {
        userBalanceMapper.updateByPrimaryKey(userBalance);
        BalanceLog balanceLog = new BalanceLog();
        balanceLog.setAmount(userBalance.getMoney());
        balanceLog.setType(type);
        balanceLog.setUsername(userBalance.getUsername());
        balanceLog.setTranstime(Calendar.getInstance().getTime());
        return balanceLogMapper.insert(balanceLog);
    }

    @Override
    public int addUserBalance(UserBalance userBalance) {
        return userBalanceMapper.addUserBalance(userBalance);
    }
}
