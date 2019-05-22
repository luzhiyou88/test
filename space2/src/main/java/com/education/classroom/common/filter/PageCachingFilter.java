package com.education.classroom.common.filter;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

import com.education.classroom.core.cache.util.CacheUtils;

/**
 * 
 * @Class Name PageCachingFilter
 * @author 路志友
 * @Create In 2016年5月31日
 */
public class PageCachingFilter extends SimplePageCachingFilter {

	@Override
	protected CacheManager getCacheManager() {
		return CacheUtils.getCacheManager();
	}
	
}
