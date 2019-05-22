package com.education.classroom.core.common.util;

import java.math.BigDecimal;

/**
 * 金额工具类
 * @Class Name MoneyUtil
 * @author 张永生
 * @Create In 2016年5月20日
 */
public class MoneyUtil {

	/**
	 * 将金额从元为单位转为以分为单位
	 * 2016年5月20日
	 * By 张永生
	 * @param money
	 * @return
	 */
	public static Long convertMoney(BigDecimal money) {
		String result = "";
		if (money == null || money == BigDecimal.ZERO) {
			return 0L;
		}
		money = money.multiply(new BigDecimal(100));
		int idx = money.toString().indexOf(".");
		if (idx > 0) {
			result = money.toString().substring(0, idx);
		} else {
			result = money.toString();
		}
		return Long.parseLong(result);
	}
	
}
