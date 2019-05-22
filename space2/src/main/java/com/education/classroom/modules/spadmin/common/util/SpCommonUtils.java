package com.education.classroom.modules.spadmin.common.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;


import com.education.classroom.utils.OssUtils.OSSClientUtil;


/**
 * 通用工具类
 * @Class Name CommonUtil
 * @author zhujie
 * @Create In 2016年9月18日
 */
public class SpCommonUtils {

	/**
	 * 删除发布到阿里云上的切割文件
	 * 2016年9月18日
	 * By zhujie
	 * @param doc
	 */
	/*
	 * public static void deleteDocResult(DocResult doc){ if(doc==null){ return; }
	 * OSSClientUtil oss = null; try{ oss = new OSSClientUtil(); //删除swf文件
	 * List<SwfFile> swfFiles = doc.getSwfFiles(); for (SwfFile swf:swfFiles) {
	 * String ossUrl =swf.getOssUrl(); if (StringUtils.isNotEmpty(ossUrl)) {
	 * oss.deleteFileByUrl(ossUrl); } }
	 * 
	 * //删除txt文件 List<TextFile> textFiles = doc.getTextFiles(); for (TextFile
	 * txt:textFiles) { String ossUrl =txt.getOssUrl(); if
	 * (StringUtils.isNotEmpty(ossUrl)) { oss.deleteFileByUrl(ossUrl); } }
	 * 
	 * //删除缩略图文件 List<ThumbnailsFile> thumbnailsFiles = doc.getThumbnailsFiles();
	 * for (ThumbnailsFile thu:thumbnailsFiles) { String ossUrl =thu.getOssUrl(); if
	 * (StringUtils.isNotEmpty(ossUrl)) { oss.deleteFileByUrl(ossUrl); } } }
	 * catch(Exception e){ throw e;
	 * 
	 * } finally { if(oss!=null){ oss.destory(); } } }
	 */
}
