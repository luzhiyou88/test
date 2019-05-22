package com.education.classroom.core.security.shiro.session;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * 自定义的shiroSession接口
 * @Class Name ShiroSessionRepository
 * @author 张永生
 * @Create In 2016年1月14日
 */
public interface ShiroSessionRepository {

    void saveSession(Session session);

    void deleteSession(Serializable sessionId);

    Session getSession(Serializable sessionId);

    Collection<Session> getAllSessions();
}
