package com.education.classroom.utils.OssUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.education.classroom.utils.IdGen;
import com.education.classroom.utils.StringUtils;
import com.google.common.collect.Maps;


/**
 * 阿里云 OSS文件类
 * @Class Name OSSClientUtil
 * @author 路志友
 * @Create In 2016年8月11日
 */
public class OSSClientUtil {

	private static Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);
	// endpoint以杭州为例，其它region请按实际情况填写
	private String endpoint = ConfOss.get("endpoint"); 
	// accessKey
	private String accessKeyId = ConfOss.get("accessKeyId"); 
	private String accessKeySecret = ConfOss.get("accessKeySecret"); 
	// 空间
	private String bucketName =  ConfOss.get("bucketName"); 
	// 文件存储目录
	private String filesdir = "files/";
	// 图片存储目录
	private String imagesdir = "images/";
	
	//
	private static String imageStratWith = "image";
	private static String fileStratWith = "file";
	
	private OSSClient ossClient;

	public OSSClientUtil() {
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

	/**
	 * 初始化
	 */
	public void init() {
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

	/**
	 * 销毁
	 */
	public void destory() {
		ossClient.shutdown();
	}


	/**
	 * 上传图片
	 * 2016年8月11日
	 * By 路志友
	 * @param url
	 */
/*	public void uploadImg2Oss(String url) {
		File fileOnServer = new File(url);
		FileInputStream fin;
		try {
			fin = new FileInputStream(fileOnServer);
			String[] split = url.split("/");
			this.uploadImgOrFile2OSS(fin, split[split.length - 1]);
		} catch (FileNotFoundException e) {
			//throw new ImgException("图片上传失败");
		}
	}*/
       
	/**
	 * 已MultipartFile类型上传 上传图片
	 * 2016年8月11日
	 * By 路志友
	 * @param 
	 * @return
	 */
	public Map<String,String> uploadImg2Oss(MultipartFile file) {
		System.out.println(file.getOriginalFilename()+"文件--开始上传");
		Map<String,String> map = new HashMap<String, String>();
		if (file.getSize() > 1024  * 1024 * 10) {
			//throw new ImgException("上传图片大小不能超过1M！");
			map.put("SUCCESS", "0"); //上传
			map.put("ERRMSG", "上传图片大小不能超过10M！");
			return map;
		}
		String originalFilename = file.getOriginalFilename();
		String substring = originalFilename.substring(
				originalFilename.lastIndexOf(".")).toLowerCase();
		
		String name = imageStratWith+IdGen.uuid() + substring;
		 System.out.println(file.getOriginalFilename()+"--文件更名为--"+name);
		try {
			InputStream inputStream = file.getInputStream();
		//	byte[] data = IOUtils.toByteArray(inputStream);
			//InputStream thumb = ImageUtil.compressWithSize(new ByteArrayInputStream(data), 400, 400, 1f);
			this.uploadImgOrFile2OSS(inputStream, name);
			map.put("SUCCESS", "1");
			map.put("NAMEKEY", name);
			System.out.println(name+"文件--上传成功");
		} catch (Exception e) {
			map.put("SUCCESS", "0");
			map.put("ERRMSG", "上传图片错误！");
		}
		return map;
	}
	
	/**
	 * 上传MultipartFile类型的文件
	 * 2016年8月11日
	 * By 路志友
	 * @param file
	 * @return
	 */
	public Map<String,String> uploadFile2Oss(MultipartFile file) {
		System.out.println(file.getOriginalFilename()+"文件--开始上传");
		Map<String,String> map = new HashMap<String, String>();
		if (file.getSize() > 1024 * 1024 * 10) {
			map.put("SUCCESS", "0"); //上传
			map.put("ERRMSG", "上传图片大小不能超过10M！");
			return map;
			//throw new ImgException("上传图片大小不能超过1M！");
		}
		String originalFilename = file.getOriginalFilename();
		String substring = originalFilename.substring(
				originalFilename.lastIndexOf(".")).toLowerCase();
		
		String name = fileStratWith+IdGen.uuid() + substring;
		System.out.println(file.getOriginalFilename()+"--文件更名为--"+name);
		try {
			InputStream inputStream = file.getInputStream();
			this.uploadImgOrFile2OSS(inputStream, name);
			map.put("SUCCESS", "1");
			map.put("NAMEKEY", name);
			  System.out.println(name+"文件--上传成功");
		} catch (Exception e) {
			map.put("SUCCESS", "0");
			map.put("ERRMSG", "上传图片错误！");
			//throw new ImgException("图片上传失败");
		}
		return map;
	}
	
	/**
	 * 文件上传
	 * 2016年9月7日
	 * By zhangyongsheng
	 * @param file
	 * @return
	 */
	public Map<String, Object> uploadFile2Oss(File file) {
		logger.info(file.getName() + "文件--开始上传");
		Map<String, Object> map = Maps.newHashMap();
		String originalFilename = file.getName();
		String substring = originalFilename.substring(
				originalFilename.lastIndexOf(".")).toLowerCase();
		String name = fileStratWith + IdGen.uuid() + substring;
		logger.info(file.getName() + "--文件更名为:" + name);
		try {
			InputStream inputStream = new FileInputStream(file);
			this.uploadImgOrFile2OSS(inputStream, name);
			map.put("SUCCESS", true);
			map.put("NAMEKEY", name);
			logger.info(file.getName() + "--文件上传成功");
		} catch (Exception e) {
			logger.error(file.getName() + "--文件上传发生异常--" + e);
			map.put("SUCCESS", false);
			map.put("ERRMSG", "上传图片错误！");
		}
		return map;
	}
	
	/**
	 * 获得图片路径
	 * 2016年8月11日
	 * By 路志友
	 * @param fileUrl
	 * @return String
	 */
	public String getImgOrFileUrl(String fileUrl) {
		if (!StringUtils.isEmpty(fileUrl)) {
			String[] split = fileUrl.split("/");
			   if(fileUrl.startsWith(fileStratWith)){
			      return this.getUrl(this.filesdir + split[split.length - 1]);
			   }else if(fileUrl.startsWith(imageStratWith)){
				   return this.getUrl(this.imagesdir + split[split.length - 1]);
			   }
		}
		return null;
	}
	
	/**
	 * 上传到OSS服务器 如果同名文件会覆盖服务器上的
	 * 2016年8月11日
	 * By 路志友
	 * @param instream 文件流
	 * @param fileName  文件名称 包括后缀名
	 * @return 出错返回"" ,唯一MD5数字签名
	 */
	public String uploadImgOrFile2OSS(InputStream instream, String fileName) {
		String ret = "";
		try {
			// 创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(fileName
					.substring(fileName.lastIndexOf(".")+1)));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			
			String filePath = "other";
			 if(fileName.startsWith(fileStratWith)){
				 filePath = filesdir;
			   }else if(fileName.startsWith(imageStratWith)){
				   filePath = imagesdir;
				//   byte[] data = IOUtils.toByteArray(instream);
				//  instream = ImageUtil.compressWithSize(new ByteArrayInputStream(data), 640, 480, 1f);
					
			   }
			// 上传文件
			PutObjectResult putResult = ossClient.putObject(bucketName, filePath
					+ fileName, instream, objectMetadata);
			ret = putResult.getETag();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return ret;
	}

	
	/**
	 *  判断OSS服务文件上传时文件的contentType
	 * 2016年8月11日
	 * By 路志友
	 * @param FilenameExtension  文件后缀
	 * @return String
	 */
	public static String getcontentType(String FilenameExtension) {
		if (FilenameExtension.equalsIgnoreCase("mp3")) {
			return "audio/mpeg";
		}
		if (FilenameExtension.equalsIgnoreCase("bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equalsIgnoreCase("jpeg")
				|| FilenameExtension.equalsIgnoreCase("jpg")
				|| FilenameExtension.equalsIgnoreCase("png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equalsIgnoreCase("html")) {
			return "text/html";
		}
		
		if (FilenameExtension.equalsIgnoreCase("vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equalsIgnoreCase("txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equalsIgnoreCase("pptx")
				|| FilenameExtension.equalsIgnoreCase("ppt")||FilenameExtension.equalsIgnoreCase("pdf")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equalsIgnoreCase("docx")
				|| FilenameExtension.equalsIgnoreCase("doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equalsIgnoreCase("xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}

	/**
	 * 获得url链接
	 * 2016年8月11日
	 * By 路志友
	 * @param key
	 * @return String
	 */
	public String getUrl(String key) {
		// 设置URL过期时间为10年 3600l* 1000*24*365*10
		Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24
				* 365 * 10);
		/*Date expiration = new Date(new Date().getTime() + 30l * 1000);*/
		// System.out.println(expiration);
		// System.out.println(expiration.getTime());
		// 生成URL
		URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
		if (url != null) {
			return url.toString();
		}
		return null;
	}
	
	 /**
     * @Description:删除文件
     * @param key  OSS文件服务器上文件的唯一标识
     * @ReturnType:void
    */
    public void deleteFile(String key){
    	  System.out.println(key+"文件--开始删除");
        init();
        //String filePath = "other";
        if(key.startsWith(fileStratWith)){
        	key = filesdir+key;
		   }else if(key.startsWith(imageStratWith)){
			   key = imagesdir+key;
		   }  //	images/image893dc435c1ae425a9a00e34f82f7a883.jpg
        System.out.println(key+"文件--已被删除");
        ossClient.deleteObject(bucketName, key);
    }
    
    /**
     * 根据url删除
     * 2016年8月15日
     * By 路志友
     * @param key
     */
    public void deleteFileByUrl(String url){
    	if(!StringUtils.isEmpty(url)){
    		String key="";
    		String regEx="/([a-zA-Z_0-9\\.]*)\\?";
    		Pattern pat = Pattern.compile(regEx);
    		Matcher mat = pat.matcher(url);
    		if(mat.find()){
    		key=mat.group(1);
    		}
  	  System.out.println(url+"文件--开始删除");
      init();
      //String filePath = "other";
      if(key.startsWith(fileStratWith)){
      	key = filesdir+key;
		   }else if(key.startsWith(imageStratWith)){
			   key = imagesdir+key;
		   }  //	images/image893dc435c1ae425a9a00e34f82f7a883.jpg
      System.out.println(key+"文件--已被删除");
      ossClient.deleteObject(bucketName, key);
  }
    	}
    
    /**
     * 获取文件名称无后缀需要自己加上后缀
     * 2016年8月11日
     * By 路志友
     * @return String
     */
    public static String getFileName() {
     return	fileStratWith+IdGen.uuid();
    }
    
    /**
     * 获取图片名称无后缀需要自己加上后缀
     * 2016年8月11日
     * By 路志友
     * @return String
     */
    public static String getImageName() {
     return	imageStratWith+IdGen.uuid();
    }
}