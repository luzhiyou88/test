package com.education.classroom.utils;

import java.math.BigDecimal;

/**
 * BigDecimal工具类
 * @Class Name BigDecimalTools
 * @author 张永生
 * @Create In 2016年3月4日
 */
public class BigDecimalTools {


	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，再往后的数字四舍五入。
	 * 即表示需要精确到小数点以后几位。
	 */
	private static final int scale = 10;

	private BigDecimalTools() {
	}
	
	/*---------------------------------double 类型  start------------------------------------*/
	/**
	 * 两个double类型的数值相加
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(double v1, double v2) {
		BigDecimal a1 = new BigDecimal(Double.toString(v1));
		BigDecimal a2 = new BigDecimal(Double.toString(v2));
		return a1.add(a2).doubleValue();
	}

	/**
	 * 两个double类型的数值相除，当发生除不尽的情况时，默认精确到小数点后10位
	 * @param v1
	 * @param v2
	 * @return 两个数的商
	 */
	public static double div(double v1, double v2) {
		BigDecimal a1 = new BigDecimal(Double.toString(v1));
		BigDecimal a2 = new BigDecimal(Double.toString(v2));
		return divide(a1, a2);
	}
	
	/**
	 * 两个double类型的数值相除
	 * @param v1
	 * @param v2
	 * @param v3   小数点后保留几位
	 * @return 两个数的商
	 */
	public static double div(double v1, double v2, int v3) {
		BigDecimal a1 = new BigDecimal(Double.toString(v1));
		BigDecimal a2 = new BigDecimal(Double.toString(v2));
		return divide(a1, a2, v3);
	}

	/**
	 * 两个double类型的数值相减
	 * @param v1
	 * @param v2
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal a1 = new BigDecimal(Double.toString(v1));
		BigDecimal a2 = new BigDecimal(Double.toString(v2));
		return  a1.subtract(a2).doubleValue();
	}

	/**
	 * 两个double类型的数值相乘
	 * @param v1
	 * @param v2
	 * @return 两个参数的乘积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal a1 = new BigDecimal(Double.toString(v1));
		BigDecimal a2 = new BigDecimal(Double.toString(v2));
		return a1.multiply(a2).doubleValue();
	}
	/*---------------------------------double 类型  end------------------------------------*/
	/*---------------------------------String 类型  start------------------------------------*/
	/**
	 * 两个String类型的数值相加
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(String v1, String v2) {
		BigDecimal a1 = new BigDecimal(v1);
		BigDecimal a2 = new BigDecimal(v2);
		return a1.add(a2).doubleValue();
	}
	
	/**
	 * 两个String类型的数值相除，当发生除不尽的情况时，默认精确到小数点后10位
	 * @param v1
	 * @param v2
	 * @return 两个数的商
	 */
	public static double div(String v1, String v2) {
		BigDecimal a1 = new BigDecimal(v1);
		BigDecimal a2 = new BigDecimal(v2);
		return divide(a1, a2);
	}
	
	
	/**
	 * 两个String类型的数值相除
	 * @param v1
	 * @param v2
	 * @param v3  小数点后保留几位
	 * @return 两个数的商
	 */
	public static double div(String v1, String v2, int v3) {
		BigDecimal a1 = new BigDecimal(v1);
		BigDecimal a2 = new BigDecimal(v2);
		return divide(a1, a2, v3);
	}
	
	/**
	 * 两个String类型的数值相减
	 * @param v1
	 * @param v2
	 * @return 两个参数的差
	 */
	public static double sub(String v1, String v2) {
		BigDecimal a1 = new BigDecimal(v1);
		BigDecimal a2 = new BigDecimal(v2);
		return a1.subtract(a2).doubleValue();
	}
	
	/**
	 * 两个String类型的数值相乘
	 * @param v1
	 * @param v2
	 * @return 两个参数的乘积
	 */
	public static double mul(String v1, String v2) {
		BigDecimal a1 = new BigDecimal(v1);
		BigDecimal a2 = new BigDecimal(v2);
		return a1.multiply(a2).doubleValue();
	}
	
	/*---------------------------------String 类型  end------------------------------------*/
	/**
	 * 两个BigDecimal类型变量相除 ,该方法供div方法调用
	 * @param v1
	 * @param v2
	 * @return 两个数的商 double
	 */
	private static double divide(BigDecimal v1, BigDecimal v2) {
		return v1.divide(v2, scale,	BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 两个BigDecimal类型变量相除 ,该方法供div方法调用
	 * @param v1 
	 * @param v2 
	 * @param v3  精度
	 * @return
	 */
	private static double divide(BigDecimal v1, BigDecimal v2, int v3) {
		if (v3 < 0) {
			throw new IllegalArgumentException("精度指定错误,请指定一个>=0的精度");
		}
		return v1.divide(v2, v3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 两个BigDecimal类型变量相加,该方法供add方法调用
	 * @param v1 
	 * @param v2 
	 * @return
	 */
	public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
		return v1.add(v2);
	}
	
	/**
	 * 两个BigDecimal类型的数值相减
	 * @param v1
	 * @param v2
	 * @return 两个参数的差
	 */
	public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
		return  v1.subtract(v2);
	}
	
	/**
	 * 两个BigDecimal类型的数值相乘
	 * @param v1
	 * @param v2
	 * @return 两个参数的乘积
	 */
	public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
		return v1.multiply(v2);
	}
	
	/**
	 * 两个BigDecimal类型的数值相除
	 * @param v1
	 * @param v2
	 * @return 两个参数的除法
	 */
	public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
		return v1.divide(v2);
	}
	
	/**
	 * 两个BigDecimal类型的数值相除
	 * @param v1
	 * @param v2
	 * @param v3  小数点后保留几位
	 * @return 两个参数的除法
	 */
	public static BigDecimal div(BigDecimal v1, BigDecimal v2, int v3) {
		return v1.divide(v2, v3);
	}
	
	/**
	 * 四舍五入,小数点后保留两位
	 * 2016年4月21日
	 * By 张永生
	 * @param source
	 */
	public static BigDecimal setRoundHalfUp(BigDecimal source){
		return source.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

}