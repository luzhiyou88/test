package com.education.classroom.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * List工具类
 * 
 * @Class Name ListUtils
 * @author 周亮
 * @Create In 2016年01月01日
 */
public class ListUtils {
	
	/**
	 * 判断List是否为空
	 * 
	 * @param list
	 * @return 空为true, 反之为false
	 */
	public static boolean isEmptyList(List<? extends Object> list) {
		if (list == null || list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断List是否不为空
	 * 
	 * @param list
	 * @return 空为false, 反之为true
	 */
	public static boolean isNotEmptyList(List<? extends Object> list) {
		return !isEmptyList(list);
	}	
	
	/**
	 * 将List按设定大小拆分并返回
	 * 
	 * @param list 被拆分list
	 * @param max 拆分基数
	 * @return List<List<T>> 结果
	 */
	public static <T> List<List<T>> getSplitedList(List<T> list, String max) {
		List<List<T>> retList = new ArrayList<List<T>>();
		if (isEmptyList(list)) {
			return retList;
		}
		if (StringUtils.isEmpty(max)) {
			return retList;
		}
		int m = Integer.parseInt(max);
		int listSize = list.size();
		if (listSize <= m) {
			retList.add(list);
			return retList;
		}
		int batchSize = listSize / m;
		int remain = listSize % m;
		for (int i = 0; i < batchSize; i++) {
			int fromIndex = i * m;
			int toIndex = fromIndex + m;
			retList.add(list.subList(fromIndex, toIndex));
		}
		if (remain > 0) {
			retList.add(list.subList(listSize - remain, listSize));
		}
		return retList;
	}
}
