package com.demo.novel.service;

import com.demo.novel.entity.UserSession;

public interface UserSessionService {
    UserSession queryByEntity(UserSession userSession);
    int deleteByEntity(Integer id);
    int updateByEntity(UserSession userSession);
    int insertByEntity(UserSession userSession);
}
