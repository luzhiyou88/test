package com.education.classroom.core.security.util;


import java.util.ResourceBundle;

import com.education.classroom.utils.StringUtils;

public class PortalPropertiesUtil {
	
	public static final String ADDITIONAL_PERMISSION_TYPE_ALLOW="allow";
	public static final String ADDITIONAL_PERMISSION_TYPE_FORBIDDEN="forbidden";

	private static PortalPropertiesUtil instance = new PortalPropertiesUtil();
	private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
	
	public static PortalPropertiesUtil getInstance(){
		return instance;
	}
	
	public static String getVal(String key){
		try{
			return resourceBundle.getString(key);
		}catch(Exception e){
			return "";
		}
	}
	
	public static boolean getAdditionalPermissionEnable(){
		String val = getVal("additionalPermissionEnable");
		return Boolean.valueOf(StringUtils.isEmpty(val)?"false":val);
	}
	
	public static String getAdditionalPermissionType(){
		String val = getVal("additionalPermissionType");
		return StringUtils.isEmpty(val)?"allow":val;
	}
	
	public static boolean getInitialPasswordResetEnable(){
		String val = getVal("initialPasswordResetEnable");
		return Boolean.valueOf(StringUtils.isEmpty(val)?"false":val);
	}
	
}
