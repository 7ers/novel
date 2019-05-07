package com.demo.novel.service.impl;

import com.demo.novel.dao.UserInfoMapper;
import com.demo.novel.entity.NovelBase;
import com.demo.novel.entity.UserInfo;
import com.demo.novel.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

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

    @Override
    public PageInfo<UserInfo> queryUserList(UserInfo userInfo,int start,int length) {
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        List<UserInfo> userInfoList = userInfoMapper.selectByEntity(userInfo);
        return new PageInfo<>(userInfoList);
    }

    @Override
    public int addUser(UserInfo userInfo) {
        return userInfoMapper.addUser(userInfo);
    }

    @Override
    public int deleteByUserId(Integer userid) {
        return userInfoMapper.deleteByUserId(userid);
    }


}