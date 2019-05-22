package com.education.classroom.core.common.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;

/**
 * 分页工具类
 * @Class Name PageUtil
 * @author zhangyongsheng
 * @Create In 2016年8月5日
 */
public class PageUtil {

	public static long COUNT_ZERO = 0;
	/**
	 * 获取当前页
	 * @param request
	 * @return
	 */
	public static int getPageNo(HttpServletRequest request){
		String pageNo = request.getParameter("pageNo");
		if (pageNo == null || pageNo.equals("")) {
			pageNo = "1";
		}
		return Integer.parseInt(pageNo);
	}
	
	
	/**
	 * 分页信息转换
	 * @param <T>
	 * @param <T>
	 * @param <E>
	 * @param sourcePage
	 * @param targetPage
	 */
	public static <T, E> void convertPage(Page<E> sourcePage,Page<T> targetPage,List<T> result){
		CommonUtils.pageConversion(sourcePage, targetPage);
		targetPage.setList(result);
	}
	
	public static <T> void convertPage(PageInfo<T> pageInfo, Page<T> page) {
		page.setCount(pageInfo.getTotal());
		page.setList(pageInfo.getList());
	}

	
}
