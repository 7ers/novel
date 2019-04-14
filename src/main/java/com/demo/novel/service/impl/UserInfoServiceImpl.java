package com.demo.novel.service.impl;

import com.demo.novel.dao.UserInfoMapper;
import com.demo.novel.entity.UserInfo;
import com.demo.novel.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 模糊查询
     * @param userName
     * @return
     */
    @Override
    public UserInfo findByUsername(String userName) {

        return userInfoMapper.findByUsername(userName);
    }
}