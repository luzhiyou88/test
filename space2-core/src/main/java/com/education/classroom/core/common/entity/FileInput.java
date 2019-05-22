/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.common.entity;


/**
 * 使用FileInput上传的文件Entity
 * @Class Name FileInput
 * @author zhujie
 * @Create In 2016年8月15日
 */
public class FileInput  {
	
	public FileInput(){}
	
	public FileInput(String id,String fileName, String url){
		this.id=id;
		this.fileName=fileName;
		this.url=url;
	}
	
	private String id;//资源id
	private String url;//文件获取路径
	private String fileName;//文件名
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}