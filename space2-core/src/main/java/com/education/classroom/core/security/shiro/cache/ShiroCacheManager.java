package com.education.classroom.core.security.shiro.cache;

import org.apache.shiro.cache.Cache;

/**
 * 自定义的shiro缓存管理类接口
 * @Class Name ShiroCacheManager
 * @author 张永生
 * @Create In 2016年1月14日
 */
public interface ShiroCacheManager {

    <K, V> Cache<K, V> getCache(String name);

    void destroy();

}
