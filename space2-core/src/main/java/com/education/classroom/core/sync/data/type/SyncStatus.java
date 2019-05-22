package com.education.classroom.core.sync.data.type;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * 数据同步状态
 * @Class Name SyncStatus
 * @author 张永生
 * @Create In 2015年12月3日
 */
public class SyncStatus implements Serializable{

	private static final long serialVersionUID = -8326759715107764717L;

	/**
	 * 未同步
	 */
	public static final int STATUS_UNDO = 0;
	/**
	 * 未同步的描述
	 */
	public static final String STATUS_UNDO_DESC = "未同步";
	/**
	 * 同步失败
	 */
	public static final int STATUS_FAILED = 1;
	/**
	 * 同步失败的描述
	 */
	public static final String STATUS_FAILED_DESC = "同步失败";
	/**
	 * 同步成功
	 */
	public static final int STATUS_SUCCESS = 2;
	/**
	 * 同步成功的描述
	 */
	public static final String STATUS_SUCCESS_DESC = "同步成功";
	
	/**
	 * 根据状态值获取状态描述
	 * 2015年12月3日
	 * By 张永生
	 * @param status
	 * @return
	 */
	public static String getStatusDesc(int status){
		String msg = null;
		switch(status){
			case STATUS_UNDO:
				msg = STATUS_UNDO_DESC;
				break;
			case STATUS_FAILED:
				msg = STATUS_FAILED_DESC;
				break;
			case STATUS_SUCCESS:
				msg = STATUS_SUCCESS_DESC;
				break;
			default:
				msg = STATUS_UNDO_DESC;
				break;
		}
		return msg;
	}
	
	public static Map<Integer, String> statusMap = new TreeMap<Integer, String>();

	public static Map<Integer, String> getStatusMap(){
		if(statusMap.isEmpty()){
			statusMap.put(STATUS_UNDO, "未同步");
			statusMap.put(STATUS_FAILED, "同步失败");
		}
		return statusMap;
	}
	
}
