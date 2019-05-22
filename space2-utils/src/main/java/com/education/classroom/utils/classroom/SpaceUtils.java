package com.education.classroom.utils.classroom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SpaceUtils {

    private static Properties props = new Properties();

    static {
        try {
            //play框架下要用这种方式加载
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("space.properties"));
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
     * 获取space_id
     * 2016年8月6日
     * By zhujie
     * @return
     */
    public static String getSpaceId(){
    	return get("SpaceId");
    }
   
    
}
