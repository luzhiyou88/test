package com.education.classroom.core.security.entity;

/**
 * 页面权限控制Entity
 * @author 周亮
 * @version 2015-11-02
 */
public class PageAuth {
	
	private String pageId;		// 页面ID
	private String roleId;	    // 角色ID
	private String itemId;		// 项目ID
	private String displayMode;	// 显示模式:1-显示;0-不显示
	
	
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getDisplayMode() {
		return displayMode;
	}
	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}
	
	
}
