package com.education.classroom.utils.classroom.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class FtpDownloadUtil {
	
	/**
	 * 文件下载
	 * 2016年9月12日
	 * By zhujie
	 * @param response
	 * @param url
	 */
	public static void fileDownload(HttpServletResponse response,String url,String filename) {
		try{
			if(StringUtils.isEmpty(url) || StringUtils.isEmpty(filename)){
				try {
					// 文字回写
					OutputStream outputStream = response.getOutputStream();
					outputStream.write("没有文件可下载".getBytes(Charset.forName("UTF-16")));
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            return;
			}
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			String contentType = uc.getContentType();
			int contentLength = uc.getContentLength();
			if (contentType.equals("application/xml") || contentLength == -1) {
				try {
					// 文字回写
					OutputStream outputStream = response.getOutputStream();
					outputStream.write("没有文件可下载".getBytes(Charset.forName("UTF-16")));
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            return;
			}	
			InputStream ins = uc.getInputStream();
			BufferedInputStream bins = new BufferedInputStream(ins);
			// 获取文件输出IO流
			OutputStream outs = response.getOutputStream();
			BufferedOutputStream bouts = new BufferedOutputStream(outs);
			// 设置response内容的类型
			response.setContentType("application/x-msdownload");
			// 设置头部信息
			response.setHeader("Content-disposition","attachment;filename="+ 
					new String( filename.getBytes("GBK"), "ISO8859-1" ));
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			// 开始向网络传输文件流
			while ((bytesRead = bins.read(buffer, 0, 1024)) != -1) {
				bouts.write(buffer, 0, bytesRead);
			}
			//调用flush()方法
			bouts.flush();
			ins.close();
			bins.close();
			outs.close();
			bouts.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
