package com.demo.novel.service.impl;

import com.demo.novel.dao.LoginLogMapper;
import com.demo.novel.entity.LoginLog;
import com.demo.novel.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogMapper loginLogMapper;

    @Override
    public int writeLog(LoginLog loginLog) {
        return loginLogMapper.insert(loginLog);
    }
}
