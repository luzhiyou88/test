package com.education.classroom.core.thread;

/**
 * 线程处理帮助类
 * @Class Name ProcessorHandler
 * @author 张永生
 * @Create In 2016年5月3日
 * @param <T>
 */
public abstract interface ProcessorHandler<T> {
	
	/**
	 * 定义运行的方法
	 * 2016年5月3日
	 * By 张永生
	 * @param paramT
	 */
	public abstract void run(T paramT);
}
