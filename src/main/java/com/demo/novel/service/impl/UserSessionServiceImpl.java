package com.demo.novel.service.impl;

import com.demo.novel.dao.UserSessionMapper;
import com.demo.novel.entity.UserSession;
import com.demo.novel.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSessionServiceImpl implements UserSessionService {
    @Autowired
    UserSessionMapper userSessionMapper;

    @Override
    public UserSession queryByEntity(UserSession userSession) {

        return userSessionMapper.queryByEntity(userSession);
    }

    @Override
    public int deleteByEntity(Integer id) {

        return userSessionMapper.deleteByEntity(id);
    }

    @Override
    public int updateByEntity(UserSession userSession) {
        return userSessionMapper.updateByEntity(userSession);
    }

    @Override
    public int insertByEntity(UserSession userSession) {
        return userSessionMapper.insertByEntity(userSession);
    }
}
