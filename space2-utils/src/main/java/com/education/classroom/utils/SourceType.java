package com.education.classroom.utils;

/**
 * 
 * @Class Name SourceEnum
 * @author 路志友
 * @Create In 2016年8月15日
 */
 
public class SourceType {
	
	 public enum VideoEnum{
		 WOV,MP4;
	 }
	 public enum AudioEnum{
		 MP3,WMA;
	 }   
	 public enum EbookEnum{
		 GIF,JPG,PNG,JPEG,TXT,PPTX,PPT,PDF,DOCX,DOC,XML;
	 }
	/**
	 * 	
     * 视频：*.avi *.rmvb *.wmv *.mp4 *   
     * 音频：Mp3 wma rm wav  
     * 文档：gif，jpeg，jpg，png，html，txt，pptx，ppt，pdf，docx，doc，xml
     * 
     * 1视频2音频3电子书
	 */
	 
	 public static String getSourceType(String str){
		 String upp = str.toUpperCase();
		 for (VideoEnum c : VideoEnum.values())   
         {  
             if(upp.equals(c.toString())){
            	 return "1";
             }
         } 
		 for (AudioEnum c : AudioEnum.values())   
         {  
			 System.out.println(upp+"---"+c);
             if(upp.equals(c.toString())){
            	 return "2";
             }
         } 
		 for (EbookEnum c : EbookEnum.values())   
         {  
			 
             if(upp.equals(c.toString())){
            	 return "3";
             }
         }
		return "0"; 
		 
	 }
	
}
