package com.demo.novel.service;

import com.demo.novel.entity.UserInfo;

public interface UserInfoService {
    UserInfo findByUsername(String userName);
}
