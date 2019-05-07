package com.demo.novel.service;

import com.demo.novel.entity.UserInfo;
import com.github.pagehelper.PageInfo;

public interface UserInfoService {
    UserInfo findByUsername(String userName);
    PageInfo<UserInfo> queryUserList(UserInfo userInfo,int start,int length);
    int addUser(UserInfo userInfo);
    int deleteByUserId(Integer userid);
}
