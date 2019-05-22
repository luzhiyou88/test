package com.education.classroom.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;


/**
 * @类功能描述：本地文件操作处理类
 * 文件及目录检查和创建
 * @创建者：张旭
 * @创建时间：2012-2-22 下午12:36:50
 */
public class FileHelper {
	
	public static String[] illegalChars=new String[]{" ","[","]","#"};
	
	/**
	 * 判断指定的路径是否是一个文件的路径
	 * @param filePath(路径)
	 * @return 是否是一个文件的路径
	 * @transfer：
	 * @creator：张旭
	 * @date_created：2012-2-22 下午12:37:09
	 * @modificator：张旭
	 * @date_modified：2012-2-22 下午12:37:09
	 * @Description：
	 */
	public static boolean isExistsFile(String filePath){
		File file = new File(filePath);
		return file.exists() && file.isFile();
	}
	
	/**
	 * 判断指定的路径是否是一个文件夹的路径
	 * @param folderPath(路径)
	 * @return 是否是一个文件夹的路径
	 * @transfer：
	 * @creator：张旭
	 * @date_created：2012-2-22 下午12:38:25
	 * @modificator：张旭
	 * @date_modified：2012-2-22 下午12:38:25
	 * @Description：
	 */
	public static boolean isExistsFolder(String folderPath){
		File file = new File(folderPath);
		return file.exists() && file.isDirectory();
	}
	
	/**
	 * 根据指定的文件夹路径，创建这个文件夹
	 * @param folderPath(文件夹路径)
	 * @return 是否创建成功
	 * @transfer：
	 * @creator：张旭
	 * @date_created：2012-2-22 下午12:39:05
	 * @modificator：张旭
	 * @date_modified：2012-2-22 下午12:39:05
	 * @Description：
	 */
	public static boolean createFolder(String folderPath){
		File file = new File(folderPath);
		return file.mkdirs();
	}
	
	/**
	 * 将文件从一个指定的位置移动到另一个位置，如果已经存在，将自动覆盖!
	 * @param soruceFilePath(文件源位置)
	 * @param targetFilePath(文件目标位置)
	 * @return 是否移动成功
	 * @transfer：
	 * @creator：张旭
	 * @date_created：2012-2-22 下午12:40:09
	 * @modificator：张旭
	 * @date_modified：2012-2-22 下午12:40:09
	 * @Description：
	 */
	public static boolean moveFile(String soruceFilePath, String targetFilePath){
		boolean isSuccess = false;
		if(isExistsFile(soruceFilePath)){
			File soruceFile = new File(soruceFilePath);
			File targetFile = new File(targetFilePath);
			if(targetFile.exists()){
				targetFile.delete();
			}
			isSuccess = soruceFile.renameTo(targetFile);
		}
		return isSuccess;
	}
	
	/**
	 * 将文件从一个位置复制到另一个位置
	 * @param fromFilePath(从哪里复制)
	 * @param toFilePath(复制到哪里去)
	 * @return 是否复制成功
	 * @transfer：
	 * @creator：张旭
	 * @date_created：2012-2-22 下午12:41:17
	 * @modificator：张旭
	 * @date_modified：2012-2-22 下午12:41:17
	 * @Description：
	 */
	public static boolean copyFile(String fromFilePath, String toFilePath) {
		boolean isSuccess = false;
        try { 
            if (isExistsFile(fromFilePath)) {
            	File fromFile = new File(fromFilePath); 
                InputStream in = new FileInputStream(fromFilePath);
                OutputStream out = new FileOutputStream(toFilePath); 
                byte[] bytes = new byte[(int)fromFile.length()]; 
                int len = 0;
                while((len = in.read(bytes)) != -1){ 
                	out.write(bytes, 0, len); 
                } 
                in.close();
                out.close();
                isSuccess = true;
            }
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return isSuccess;
    } 
	
	/**
	 * 删除文件或文件夹
	 * @param path(文件或文件夹的路径)
	 * @transfer：
	 * @creator：张旭
	 * @date_created：2012-2-22 下午12:42:38
	 * @modificator：张旭
	 * @date_modified：2012-2-22 下午12:42:38
	 * @Description：
	 */
	public static void delete(String path){
		if(isExistsFile(path)){
			new File(path).delete();
		}else if(isExistsFolder(path)){
			if(!isEmptyFolder(path)){
				cleanFolder(path);
			}
			new File(path).delete();
		}
	}
	
	/**
	 * 判断一个文件夹是否是空的
	 * @param folderPath(文件夹路径)
	 * @return 是否是空的
	 * @transfer：
	 * @creator：张旭
	 * @date_created：2012-2-22 下午12:43:05
	 * @modificator：张旭
	 * @date_modified：2012-2-22 下午12:43:05
	 * @Description：
	 */
	public static boolean isEmptyFolder(String folderPath){
		boolean isEmpty = false;
		if(isExistsFolder(folderPath)){
			File file = new File(folderPath);
			isEmpty = (file.list()).length == 0;
		}
		return isEmpty;
    }
	
	/**
	 * 将一个非空的文件夹清空
	 * @param folderPath(文件夹路径)
	 * @transfer：
	 * @creator：张旭
	 * @date_created：2012-2-22 下午12:43:27
	 * @modificator：张旭
	 * @date_modified：2012-2-22 下午12:43:27
	 * @Description：
	 */
	public static void cleanFolder(String folderPath) { 
        if(isExistsFolder(folderPath)){
        	File folder = new File(folderPath);
            String[] files = folder.list(); 
            folderPath = folderPath.endsWith(File.separator) ? folderPath : folderPath + File.separator;
            for (int i = 0; i < files.length; i ++) {
            	String filePath = folderPath + files[i];
            	delete(filePath);
            } 
        }
    } 
	
	/**
	 * 初始化本地文档交换文件夹结构
	 * @param header(头文件夹。例: C:\\test\\QS)
	 * 注：header 取 ClientConf 中的 siteDocPath 值
	 * @transfer：
	 * @creator：张旭
	 * @date_created：2012-3-21 上午10:55:42
	 * @modificator：张旭
	 * @date_modified：2012-3-21 上午10:55:42
	 * @Description：
	 */
	public static boolean compareFiles(File srcFile,File checkFile) throws Exception {
		return MessageDigest.isEqual(hash(srcFile), hash(checkFile));
	}
	
	@SuppressWarnings("unused")
	public static byte[] hash(File file) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		InputStream is = new FileInputStream(file);
		DigestInputStream dis = new DigestInputStream(is, md);
		byte[] buffer = new byte[8192 * 4];
		int bytesRead = 1;
		while ((bytesRead = dis.read(buffer)) != -1) {

		}
		byte[] result = md.digest();
		dis.close();
		return result;
	}
	
	public static boolean isValidZipFile(final File file) {
	    ZipFile zipfile = null;
	    try {
	        zipfile = new ZipFile(file);
	        return true;
	    } catch (ZipException e) {
	        return false;
	    } catch (IOException e) {
	        return false;
	    } finally {
	        try {
	            if (zipfile != null) {
	                zipfile.close();
	                zipfile = null;
	            }
	        } catch (IOException e) {
	        }
	    }
	}
	
	public static String replaceFileSuffix(String fileName,String replacedSuffix){
		int suffixLocation=fileName.lastIndexOf('.');
		int lastSlash = fileName.lastIndexOf(File.pathSeparatorChar);
		
		if(suffixLocation!=-1 && suffixLocation > lastSlash)
			return fileName.substring(0, suffixLocation)+"."+replacedSuffix;
		else
			return fileName+"."+replacedSuffix;
	}
	
	public static boolean isSWFConvertable(String fileName){
		String suffix=getFileSuffix(fileName);
		
		if(suffix.equalsIgnoreCase("xls")||suffix.equalsIgnoreCase("xlsx")||
		   suffix.equalsIgnoreCase("doc")||suffix.equalsIgnoreCase("docx")||
		   suffix.equalsIgnoreCase("ppt")||suffix.equalsIgnoreCase("pptx")||
		   suffix.equalsIgnoreCase("vsd")||
		   suffix.equalsIgnoreCase("pdf")||suffix.equalsIgnoreCase("txt"))
			return true;
		else
		   return false;
	}
	
	public static String getFileSuffix(String fileName) {
		int suffixLocation=fileName.lastIndexOf('.');
		int lastSlash = fileName.lastIndexOf('/');
		if(suffixLocation!=-1 && suffixLocation > lastSlash)
			return fileName.substring(suffixLocation+1);
		else
			return "tmp";
	}

	//From {22FB1EC9-6283-4B1D-B77C-135B47E878CA} to 22\FB\1E\ 三级目录
	public static String constructDirFromDocumentID(String documentID) {
		return documentID.substring(1,3)+"\\"+
			   documentID.substring(3,5)+"\\"+
			   documentID.substring(5,7)+"\\";
	}
	
	public static String processFileName(String fileName){
	    //处理特殊字符，这些字符会导致URI异常字符
	    for(String illegalChar:illegalChars){
	    	fileName = fileName.replace(illegalChar, "_");
	    }	
	    StringBuffer fileNameBuff = new StringBuffer();
	    fileNameBuff.append(fileName);
	    for (int i = 0; i < fileNameBuff.length(); i++) {
			if ((fileNameBuff.charAt(i) == '{') || (fileNameBuff.charAt(i) == '}')) {
				fileNameBuff.setCharAt(i, '$');
			}
	    }
	    String fileNameFirstPhase=fileNameBuff.toString();
	    int illegeIndex=-1;
	    while(true){
		    try {
		    	if(illegeIndex!=-1){
		    		fileNameFirstPhase=fileNameFirstPhase.replace(fileNameFirstPhase.charAt(illegeIndex), '_');
		    	}
		    	new URI(fileNameFirstPhase);
		    	break;
		    } catch (URISyntaxException e) {
		    	e.printStackTrace();
		    	String errMsg=e.getMessage();
		    	int foundIndex=errMsg.indexOf("at index ");
		    	if(foundIndex==-1){
		    		break;
		    	}
		    	illegeIndex=Integer.valueOf(errMsg.substring(foundIndex+"at index ".length(),errMsg.indexOf(":")));
		    }
	    }
	    return fileNameFirstPhase;
	}

	public static String getFileNameWithoutSuffix(String fileName) {
		int suffixLocation=fileName.lastIndexOf('.');
		int lastSlash = fileName.lastIndexOf(File.pathSeparatorChar);
		if (suffixLocation != -1 && suffixLocation > lastSlash) {
			return fileName.substring(0, suffixLocation);
		} else {
			return fileName;
		}
	}

}
