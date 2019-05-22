package com.education.classroom.core.sync.data.util;

/**
 * 同步数据时t_gl_sync_data_batch字段groupKey的生成策略工具类
 * @Class Name GroupKeyUtil
 * @author 张永生
 * @Create In 2015年12月5日
 */
public class GroupKeyUtil {

	/**
	 * 分隔符
	 */
	public static final String SPLIT_MARK = "@";
	
	/**
	 * 拼接分组字段的数值
	 * 2015年12月7日
	 * By 张永生
	 * @param bizId
	 * @param moduleName
	 * @param caseValue
	 * @return
	 */
	public static String getGroupKey(String bizId, String moduleName, int caseValue){
		String key = null;
		switch(caseValue){
			case GroupByCaseType.CASE_USER:
			    key = bizId + SPLIT_MARK + moduleName;
			    break;
			case GroupByCaseType.CASE_ORG:
			    key = bizId + SPLIT_MARK + moduleName;
			    break;
			case GroupByCaseType.CASE_ROLE:
				key = bizId + SPLIT_MARK + moduleName;
				break;
			default:
				key = bizId + SPLIT_MARK + moduleName;
			    break;
		}
		return key;
	}
}
