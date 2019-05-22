package com.education.classroom.core.sync.data.util;

import java.util.Date;

import com.education.classroom.core.dict.entity.Dict;
import com.education.classroom.core.sync.data.entity.SyncDataBatch;
import com.education.classroom.core.sync.data.entity.SyncDict;
import com.education.classroom.core.sync.data.entity.SyncRole;
import com.education.classroom.core.sync.data.entity.SyncUser;
import com.education.classroom.core.sync.data.entity.SyncUserRoleOrg;
import com.education.classroom.core.sync.data.type.SyncStatus;
import com.education.classroom.core.users.entity.Role;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.utils.IdGen;


/**
 * 同步数据组装帮助类
 * @Class Name SyncAssembleHelper
 * @author 张永生
 * @Create In 2015年12月4日
 */
public class SyncAssembleHelper {

	/**
	 * 组装同步用户角色组织对象
	 * 2015年12月4日
	 * By 张永生
	 * @param userId
	 * @param orgId
	 * @param syncType
	 * @param moduleName
	 * @return
	 */
	public static SyncUserRoleOrg assembleSyncUserRoleOrg(String userId, String orgId,String roleId,
			String syncType, String moduleName, int batchNo) {
		SyncUserRoleOrg syncUserRoleOrg = new SyncUserRoleOrg();
		String syncUserOrgId = IdGen.uuid();
		syncUserRoleOrg.setId(syncUserOrgId);
		syncUserRoleOrg.setUserId(userId);
		syncUserRoleOrg.setOrgId(orgId);
		syncUserRoleOrg.setRoleId(roleId);
		syncUserRoleOrg.setSyncType(syncType);
		syncUserRoleOrg.setSyncModuleName(moduleName);
		syncUserRoleOrg.setSyncStatus(SyncStatus.STATUS_UNDO);
		syncUserRoleOrg.setBatchNo(batchNo);
		syncUserRoleOrg.setCreateTime(new Date());
		syncUserRoleOrg.setCreateBy(UserUtils.getUser().getId());
		return syncUserRoleOrg;
	}
	
	/**
	 * 组装同步角色对象
	 * 2015年12月25日
	 * By 陈伟东
	 * @param role
	 * @param syncType
	 * @param moduleName
	 * @param batchNo
	 * @return
	 */
	public static SyncRole assembleSyncRole(Role role, String syncType, String moduleName, int batchNo){
		SyncRole syncRole = new SyncRole();
		syncRole.setId(IdGen.uuid());
		syncRole.setRoleId(role.getId());
		syncRole.setSyncModuleName(moduleName);
		syncRole.setSyncType(syncType);
		syncRole.setSyncStatus(SyncStatus.STATUS_UNDO);
		syncRole.setBatchNo(batchNo);
		syncRole.setVersionTime(new Date());
		syncRole.setDelFlag(role.getDelFlag());
		syncRole.setName(role.getName());
		syncRole.setRemarks(role.getRemarks());
		syncRole.setRoleId(role.getId());
		syncRole.setRoleType(role.getRoleType());
		syncRole.setSysData(role.getSysData());
		syncRole.setUseable(role.getUseable());
		syncRole.setUpdateBy(role.getUpdateBy());
		syncRole.setUpdateDate(role.getUpdateDate());
		return syncRole;
	}
	
	
	/**
	 * 组装同步数据字典对象
	 * 2015年12月28日
	 * By 陈伟东
	 * @param dict
	 * @param syncType
	 * @param moduleName
	 * @param batchNo
	 * @return
	 */
	public static SyncDict assembleSyncDict(Dict dict, String syncType, String moduleName, int batchNo){
		SyncDict syncDict = new SyncDict();
		syncDict.setId(IdGen.uuid());
		syncDict.setDictId(dict.getId());
		syncDict.setSyncModuleName(moduleName);
		syncDict.setSyncType(syncType);
		syncDict.setSyncStatus(SyncStatus.STATUS_UNDO);
		syncDict.setBatchNo(batchNo);
		syncDict.setVersionTime(new Date());
		syncDict.setDelFlag(dict.getDelFlag());
		syncDict.setDescription(dict.getDescription());
		syncDict.setLabel(dict.getLabel());
		syncDict.setUpdateBy(dict.getUpdateBy());
		syncDict.setUpdateDate(dict.getUpdateDate());
		syncDict.setParentId(dict.getParentId());
		syncDict.setRemarks(dict.getRemarks());
		syncDict.setSort(dict.getSort());
		syncDict.setType(dict.getType());
		syncDict.setValue(dict.getValue());
		return syncDict;
	}
	
	/**
	 * 组装同步用户对象
	 * 2015年12月7日
	 * By 张永生
	 * @param user
	 * @param syncType
	 * @param moduleName
	 * @param batchNo
	 * @return
	 */
	public static SyncUser assembleSyncUser(User user, String syncType, String moduleName, int batchNo){
		SyncUser syncUser = new SyncUser();
		
		return syncUser;
	}
	
	
	/**
	 * 组装同步数据批次对象
	 * 2015年12月17日
	 * By 张永生
	 * @param bizId
	 * @param batchNo
	 * @param moduleName
	 * @param tableName
	 * @param groupKeyDesc
	 * @param groupByCase
	 * @return
	 */
	public static SyncDataBatch assembleSyncDataBatch(String bizId,
			int batchNo, String moduleName, String tableName,
			String groupKeyDesc, int groupByCase) {
		SyncDataBatch syncBatch = new SyncDataBatch();
		
		return syncBatch;
	}
}
