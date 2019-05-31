package com.demo.novel.service.impl;

import com.demo.novel.dao.LoginLogMapper;
import com.demo.novel.entity.LoginLog;
import com.demo.novel.service.LoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    LoginLogMapper loginLogMapper;

    @Override
    public int writeLog(LoginLog loginLog) {
        return loginLogMapper.insert(loginLog);
    }
}
