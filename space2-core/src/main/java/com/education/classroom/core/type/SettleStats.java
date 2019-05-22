package com.education.classroom.core.type;

import java.util.Map;
import java.util.TreeMap;

/**
 * 结算状态
 * @Class Name SettleStats
 * @author 赵红江
 * @Create In 2015年11月4日
 */
public enum SettleStats {
	
	WJS("0"), //未结算
	YJS("1");//已结算
	
	public final String value;

	private SettleStats(String value) {
		this.value = value;
	}

	public static String getSettleStats(String value) {
		return settleStatsMap.get(value);
	}
	
	public static Map<String, String> settleStatsMap = new TreeMap<String, String>();

	public static void initSettleStats() {
		if (settleStatsMap.isEmpty()) {
			settleStatsMap.put(SettleStats.WJS.value, "未结算");
			settleStatsMap.put(SettleStats.YJS.value, "已结算");
		}
	}
	
}
