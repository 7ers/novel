package com.demo.novel.shiro;

import com.demo.novel.entity.UserInfo;
import com.demo.novel.entity.UserSession;
import com.demo.novel.service.UserSessionService;
import com.demo.novel.util.SerializableUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Component
@Repository
public class DBShiroSessionDAO extends EnterpriseCacheSessionDAO {

    @Autowired
    private UserSessionService userSessionService;
    //创建session
    @Override
    protected Serializable doCreate(Session session) {
        Serializable cookie = super.doCreate(session);
        // 保存session到数据库
        UserSession userSession = new UserSession();
        userSession.setCookieid(cookie.toString());
        userSession.setSessionval(SerializableUtils.serializ(session));
        userSessionService.insertByEntity(userSession);
        return cookie;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            UserSession userSession = new UserSession();
            userSession.setCookieid(sessionId.toString());
            UserSession retUserSession = userSessionService.queryByEntity(userSession);
            // 如果不为空
            if (retUserSession != null) {
                String sessionStr64 = retUserSession.getSessionval();
                session = SerializableUtils.deserializ(sessionStr64);
            }

        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        //当是ValidatingSession 无效的情况下，直接退出
        if (session instanceof ValidatingSession &&
                !((ValidatingSession) session).isValid()) {
            return;
        }
        //检索到用户名
        UserSession inUserSession = new UserSession();
        inUserSession.setCookieid(session.getId().toString());
        UserSession retUserSession = userSessionService.queryByEntity(inUserSession);
        retUserSession.setSessionval(SerializableUtils.serializ(session));
        // 如果登录成功，更新用户id
        if (SecurityUtils.getSubject().isAuthenticated()){
            UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
            retUserSession.setUserId(userInfo.getUserid());
        }

        userSessionService.updateByEntity(retUserSession);
    }

    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        UserSession inUserSession = new UserSession();
        inUserSession.setCookieid(session.getId().toString());
        UserSession retUserSession = userSessionService.queryByEntity(inUserSession);
        if (retUserSession != null) {
            userSessionService.deleteByEntity(retUserSession.getId().intValue());
        }
    }
}
