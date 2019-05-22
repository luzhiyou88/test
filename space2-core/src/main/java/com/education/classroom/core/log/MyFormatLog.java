package com.education.classroom.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 自定义的格式化log
 * @Class Name MyFormatLog
 * @author 张永生
 * @Create In 2015年12月7日
 */
public class MyFormatLog {
	
	private static MyFormatLog object;
	private Logger logger;

	private MyFormatLog(Class<?> clazz) {
		if (null == this.logger)
			this.logger = 	LoggerFactory.getLogger(MyFormatLog.class);
	}

	public void warn(String source, Object[] args) {
		this.logger.warn(String.format(source, args));
	}

	public void error(Throwable exception, String source, Object[] args) {
		this.logger.error(String.format(source, args), exception);
	}

	public void debug(String source, Object[] args) {
		this.logger.debug(String.format(source, args));
	}

	public void info(String source, Object[] args) {
		this.logger.info(String.format(source, args));
	}
	
	public void info(String source) {
		this.logger.info(String.format(source));
	}

	public static MyFormatLog getLog(Class<?> clazz) {
		if (null == object) {
			return new MyFormatLog(clazz);
		}
		return object;
	}
}