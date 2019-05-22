package com.education.classroom.utils;

import java.io.Serializable;

public class UUIDHexGenerator extends UUIDGenerator {
	
	public static final String PROP_USE_DOUBLE_HEX = "bc.uuid.hex.generator.double";
	public static boolean USE_DOUBLE_HEX = Boolean
			.getBoolean("bc.uuid.hex.generator.double");

	public static void resetDefault(boolean useDoubleHex) {
		USE_DOUBLE_HEX = Boolean
				.parseBoolean(System.getProperty(
						"bc.uuid.hex.generator.double",
						Boolean.toString(useDoubleHex)));
	}

	public static String newUUID() {
		return USE_DOUBLE_HEX ? new UUIDRadixGenerator().generate()
				: (String) new UUIDHexGenerator().generate();
	}

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	public Serializable generate() {
		return 36 + format(getIP()) + format(getJVM()) + format(getHiTime())
				+ format(getLoTime()) + format(getCount());
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			String id = newUUID();
			System.out.println("ID: " + id + ",length: "+id.length());
		}
	}
}