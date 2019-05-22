package com.education.classroom.core.sync.data.compare;

import java.util.Comparator;

import com.education.classroom.core.sync.data.entity.SyncDataBatch;

/**
 * SyncDataBatch类比较器
 * @Class Name DataBatchComparator
 * @author 张永生
 * @Create In 2015年12月7日
 */
public class DataBatchComparator implements Comparator<SyncDataBatch> {
    
	/**
	 * 按照批次号由小到大排序
	 */
	@Override
    public int compare(SyncDataBatch d1, SyncDataBatch d2) {
		int batchNo1 = d1.getBatchNo();
		int batchNo2 = d2.getBatchNo();
		if (batchNo1 == batchNo2) {
			return 0;
		} else if (batchNo1 > batchNo2) {
			return -1;
		} else {
			return 1;
		}
    }
	
}
