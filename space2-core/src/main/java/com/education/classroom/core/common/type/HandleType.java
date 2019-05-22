package com.education.classroom.core.common.type;

/**
 * 处理类型
 * 作用:操作数据库的类型定义
 * @Class Name HandleType
 * @author 张永生
 * @Create In 2015年12月4日
 */
public interface HandleType {

	/**
	 * 增加
	 */
	public static final int INSERT = 1;
	/**
	 * 删除
	 */
	public static final int DELETE = 2;
	/**
	 * 查询
	 */
	public static final int QUERY = 3;
	/**
	 * 修改
	 */
	public static final int UPDATE = 4;
	
}
