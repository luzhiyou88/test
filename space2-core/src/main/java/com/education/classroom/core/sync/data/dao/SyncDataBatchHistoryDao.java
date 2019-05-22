package com.education.classroom.core.sync.data.dao;

import com.education.classroom.core.persistence.CrudDao;
import com.education.classroom.core.persistence.annotation.MyBatisDao;
import com.education.classroom.core.sync.data.entity.SyncDataBatchHistory;

/**
 * 同步数据批次的历史dao
 * @Class Name SyncDataBatchHistoryDao
 * @author 张永生
 * @Create In 2015年12月2日
 */
@MyBatisDao
public interface SyncDataBatchHistoryDao extends CrudDao<SyncDataBatchHistory> {

}
