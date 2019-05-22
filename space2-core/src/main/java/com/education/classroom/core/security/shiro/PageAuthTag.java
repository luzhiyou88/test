package com.education.classroom.core.security.shiro;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang3.StringUtils;

/**
 * 页面授权标签
 * @Class Name PageAuthTag
 * @author 周亮
 * @Create In 2016年1月8日
 */
public class PageAuthTag extends BodyTagSupport  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 页面权限控制信息
	 */
	private String authStr;
	
	/**
	 * 项目ID
	 */
	private String itemID;
	
	/**
	 * 显示模式
	 */
	private String dispMode = "";
		
	/**
	 * @return the authStr
	 */
	public String getAuthStr() {
		return authStr;
	}

	/**
	 * @param authStr the authStr to set
	 */
	public void setAuthStr(String authStr) {
		this.authStr = authStr;
	}

	/**
	 * @return the itemID
	 */
	public String getItemID() {
		return itemID;
	}

	/**
	 * @param itemID the itemID to set
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/**
	 * doStartTag
	 */
	@Override
	public int doStartTag() throws JspException {
		dispMode = "";
		
		String[] arrAuths;
		String[] arrAuth;
		if (StringUtils.isNotEmpty(authStr)) {
			arrAuths = authStr.split(",");
			for (int i = 0;i < arrAuths.length; i++) {
				arrAuth = arrAuths[i].split(":");
				if (arrAuth[0].equals(itemID)) {
					dispMode = arrAuth[1];
					
//					if (dispMode.equals("2")) {
//						// SKIP_BODY代表不执行自定义标签中的内容
//						return SKIP_BODY;
//					}
					break;
				}
			}
		}
 
		// EVAL_BODY_INCLUDE代表执行自定义标签中的内容
		return BodyTag.EVAL_BODY_BUFFERED;
	}

	/**
	 * doEndTag
	 */
	@Override
	public int doEndTag() throws JspException {
		// 获取标签体内容对象
		BodyContent bodyContent = this.getBodyContent();
		// 获取标签体里面的内容
		String content = "";
        if (bodyContent != null) {       	
        	content = bodyContent.getString();
        }
        // 修改标签体里面的内容
        String result = "";
		if (dispMode.equals("1")) {        
			result = content.replace("<input", "<input disabled=\"disabled\" "); 
        }
		if (dispMode.equals("2")) {        
			result = content.replace("<input", "<input style=\"display:none\" "); 
        }
		if (dispMode.equals("3")) {        
			result = ""; 
        }
        try {
            //输出修改后的内容
            this.pageContext.getOut().write(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return Tag.EVAL_PAGE;
	}

}
