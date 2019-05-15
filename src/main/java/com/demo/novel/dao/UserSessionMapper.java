package com.demo.novel.dao;

import com.demo.novel.entity.UserSession;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionMapper {
    UserSession queryByEntity(UserSession userSession);
    int deleteByEntity(Integer id);
    int updateByEntity(UserSession userSession);
    int insertByEntity(UserSession userSession);
}