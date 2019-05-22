
package com.education.classroom.utils.OssUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件
 * @Class Name ConfOss
 * @author 路志友
 * @Create In 2016年8月11日
 */
public class ConfOss {

	private static Properties props = new Properties();

	static {
		try {
			//play框架下要用这种方式加载
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("oss-config.properties"));
			//props.load(ConfKit.class.getResourceAsStream("/wechat.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}

    public static void setProps(Properties p){
        props = p;
    }
    
    
   

	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return get("adminPath");
	}
	
}
