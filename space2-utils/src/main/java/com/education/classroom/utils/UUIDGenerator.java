package com.education.classroom.utils;

import java.net.InetAddress;

/**
 * UUID生成器
 * @Class Name UUIDGenerator
 * @author 张永生
 * @Create In 2016年4月15日
 */
public class UUIDGenerator {
	
	private static int IP = 0;

	private static short counter = 0;
	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	protected int getJVM() {
		return JVM;
	}

	protected short getCount() {
		synchronized (UUIDGenerator.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	protected int getIP() {
		return IP;
	}

	protected short getHiTime() {
		return (short) (int) (System.currentTimeMillis() >>> 32);
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	static {
		int ipadd;
		try {
			ipadd = BytesHelper.toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
}