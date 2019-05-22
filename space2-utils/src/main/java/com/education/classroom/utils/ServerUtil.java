package com.education.classroom.utils;


@SuppressWarnings("rawtypes")
public class ServerUtil {

	private static final String JBOSS_CLASS = "/org/jboss/Main.class";

	private static final String TOMCAT_CLASS = "/org/apache/catalina/startup/Bootstrap.class";

	private static final String WEBLOGIC_CLASS = "/weblogic/Server.class";

	private static final String WEBSPHERE_CLASS = "/com/ibm/websphere/product/VersionInfo.class";
	
	
	private static boolean jBoss = false;
	
	private static boolean tomcat = false;
	
	private static boolean webLogic = false;
	
	private static boolean webSphere = false;

	static {
		Class c = ServerUtil.class;
		if(c.getResource(TOMCAT_CLASS) != null) {
			tomcat = true;
		}
		if(c.getResource(WEBSPHERE_CLASS) != null) {
			webSphere = true;
		}
		if (c.getResource(JBOSS_CLASS) != null) {
			jBoss = true;
		} 
		if(c.getResource(WEBLOGIC_CLASS) != null) {
			webLogic = true;
		}
	}

	public static boolean isJBoss() {
		return jBoss;
	}

	public static boolean isTomcat() {

		return tomcat;
	}

	public static boolean isWebLogic() {

		return webLogic;
	}

	public static boolean isWebSphere() {
		return webSphere;
	}


}