package com.demo.novel.shiro;

import com.demo.novel.entity.UserInfo;
import com.demo.novel.entity.UserSession;
import com.demo.novel.service.UserSessionService;
import com.demo.novel.util.SerializableUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Component
@Repository
public class DBShiroSessionDAO extends EnterpriseCacheSessionDAO {

    private static final Logger logger = LoggerFactory.getLogger(DBShiroSessionDAO.class);

    @Autowired
    private UserSessionService userSessionService;
    //创建session
    @Override
    public Serializable create(Session session) {
        Serializable cookie = super.create(session);
        // 保存session到数据库
        UserSession userSession = new UserSession();
        userSession.setCookieid(cookie.toString());
        userSession.setSessionval(SerializableUtils.serializ(session));
        userSessionService.insertByEntity(userSession);
        return cookie;
    }

    @Override
    public Session readSession(Serializable sessionId) {
        Session session = super.readSession(sessionId);
        if (session == null) {
            UserSession retUserSession = getUserSession(sessionId);
            // 如果不为空
            if (retUserSession != null) {
                String sessionStr64 = retUserSession.getSessionval();
                session = SerializableUtils.deserializ(sessionStr64);
            }

        }
        // 如果是APP则更新lastAccessTime
        Integer userId = getUserSession(sessionId).getUserId();
        if(userId != null && !"".equals(userId)){
            ((SimpleSession)session).setLastAccessTime(new Date());
        }
        return session;
    }

    private UserSession getUserSession(Serializable sessionId){
        UserSession userSession = new UserSession();
        userSession.setCookieid(sessionId.toString());
        return userSessionService.queryByEntity(userSession);
    }

    @Override
    public void update(Session session) {
        super.update(session);
//        //当是ValidatingSession 无效的情况下，直接退出
//        if (session instanceof ValidatingSession &&
//                !((ValidatingSession) session).isValid()) {
//            return;
//        }
        //检索到用户名
        UserSession inUserSession = new UserSession();
        inUserSession.setCookieid(session.getId().toString());
        UserSession retUserSession = userSessionService.queryByEntity(inUserSession);
        if(retUserSession!=null){
            // 如果登录成功，更新用户id
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()){
                logger.info(subject.getPrincipal().toString());
                UserInfo userInfo = (UserInfo) subject.getPrincipal();
                retUserSession.setUserId(userInfo.getUserid());
                session.setAttribute("userSession",userInfo);
                session.setAttribute("userSessionId",userInfo.getUserid());
            }
            retUserSession.setSessionval(SerializableUtils.serializ(session));

            userSessionService.updateByEntity(retUserSession);
        }
    }

    @Override
    public void delete(Session session) {
        super.delete(session);
        UserSession inUserSession = new UserSession();
        inUserSession.setCookieid(session.getId().toString());
        UserSession retUserSession = userSessionService.queryByEntity(inUserSession);
        if (retUserSession != null) {
            userSessionService.deleteByEntity(retUserSession.getId().intValue());
        }
    }
}
