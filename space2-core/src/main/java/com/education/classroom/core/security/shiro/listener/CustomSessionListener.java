package com.education.classroom.core.security.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.education.classroom.core.security.shiro.session.ShiroSessionRepository;

/**
 * 自定义的session监听器
 * @Class Name CustomSessionListener
 * @author 张永生
 * @Create In 2016年1月14日
 */
public class CustomSessionListener implements SessionListener {

    private ShiroSessionRepository shiroSessionRepository;

    @Override
    public void onStart(Session session) {
    }

    @Override
    public void onStop(Session session) {
    }

    @Override
    public void onExpiration(Session session) {
        shiroSessionRepository.deleteSession(session.getId());
    }

    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }

}
