package com.education.classroom.utils;

public class UUIDRadixGenerator extends UUIDGenerator {
	
	private StringBuilder uuid = new StringBuilder(30);

	static final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
			'P', 'Q', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z' };

	public static String newUUID() {
		return new UUIDRadixGenerator().generate();
	}

	protected UUIDRadixGenerator append(int intval) {
		this.uuid.append(shift(intval), 1, 6);
		return this;
	}

	protected UUIDRadixGenerator append(short shortval) {
		this.uuid.append(shift(shortval), 4, 3);
		return this;
	}

	protected UUIDRadixGenerator append(char c) {
		this.uuid.append(c);
		return this;
	}

	private static char[] shift(int i) {
		char[] buf = { '0', '0', '0', '0', '0', '0', '0' };
		int charPos = 7;
		int radix = 32;
		int mask = radix - 1;
		do {
			buf[(--charPos)] = digits[(i & mask)];
			i >>>= 5;
		} while (i != 0);

		return buf;
	}

	protected short getHiTime() {
		return (short) (int) (System.currentTimeMillis() % 10000L);
	}

	public String generate() {
		int i = 10 + (int) (Math.random() * 90);
		return append(getIP()).append('-').append(getJVM()).append('-')
				.append(getHiTime()).append('-').append(getLoTime())
				.append('-').append(getCount()).toString()
				+ i;
	}

	public String toString() {
		return this.uuid.toString();
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 100; i++) {
			Thread.sleep(200L);
			String id = newUUID();
			System.out.println("ID: " + id + ",length: " + id.length());
		}
	}
}
