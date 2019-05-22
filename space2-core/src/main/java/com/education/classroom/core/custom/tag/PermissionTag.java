package com.education.classroom.core.custom.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;


/**
 * 权限标签
 * @Class Name PermissionTag
 * @author 张永生
 * @Create In 2016年1月19日
 */
public class PermissionTag extends BodyTagSupport {

	private static final long serialVersionUID = -4229214871614309228L;
	
	
	private String id;
	private String key;
	
	
	public boolean hasPermission(String resourceKey) {
		return true;
	}
	
	public int doStartTag() throws JspException {
		if(hasPermission(key)){
			return Tag.EVAL_BODY_INCLUDE;
		}else{
			return Tag.SKIP_BODY;
		}
	}
	
	public int doEndTag() throws JspException {
		
		return Tag.EVAL_PAGE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
}
