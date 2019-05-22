package com.bokecc.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * Servlet implementation class CCVideoUtil
 */
public class CCVideoUtil {
	public static void main(String[] args) {
		File mfile = new File("d:/testdata/qq/2.mp4");
//		upload(mfile,1024,"","http://127.0.0.1:8080/classroom2/a/cradmin/video/crVideo/addVideoImg");
//		upload(mfile,1024,"","http://spark.bokecc.com/api/video?userid=B934DEE18E58D275&videoid=28795C6F41EB7C709C33DC5901307461&time=1467997102&hash=D87F2A9286992EA83F66139E4EE6FF6C");
		upload(mfile,1024,"","http://www.baidu.com");
		
	//	String url="categoryid=8017C8B1CE1A2569&description=&tag=111&title=111&userid=FE88CD5EA46CB850&time=1467993327&hash=1a3aeacc981d01ae1da894bf69c73bf2";
	//	String abc="categoryid=3C5D9C8E9BD88E52&description=&tag=111&title=111&userid=FE88CD5EA46CB850&time=1467994236&hash=b50fc75d02c0714402dc7a1952dc32de";
	}
	private static String key="0zqmn9IRDbJK6B8UUSrp9eAtt5xI7dPK";
	private static String userid="B934DEE18E58D275";
    public static String upload(File mfile,int chunkSize,String filename,String notifyUrl){
    	//1  创建视频上传信息，返回数据，并提取vid,metaurl,chunkurl等返回数据。
		//2 上传视频META信息（metaurl）将文件描述等信息发送到服务器
		//3 用户反复调用 上传视频文件块CHUNK（chunkurl）将文件实际数据发送到服务器直到全部发完
		//4 如果调用 上传视频文件块CHUNK（chunkurl）中间出现中断，可使用断电续传方式。
		//上传视频META信息（metaurl）获取文件长度后继续调用上传视频文件块CHUNK（chunkurl）将文件发送完成。
		if(filename==null||"".equals(filename)){
			filename=mfile.getName();
		}
		long filesize=mfile.length();
		System.out.println("filename="+filename+" , filesize="+filesize);
		Map<String, String> treeMap = new TreeMap<String, String>();
		String qs,hash,address,responseStr;
		long time;
		Document doc;
		//查询参数输入		 
		treeMap.put("userid", userid);
		//第一步
		treeMap.put("title", filename);
		treeMap.put("description", filename);		
		treeMap.put("filename", filename);
		treeMap.put("filesize", filesize+"");
		treeMap.put("notify_url", notifyUrl);//视频处理完毕的通知地址	
		qs = createQueryString(treeMap);		
		//生成时间片
		time = new Date().getTime() / 1000;	
		//生成HASH码值			
		hash =md5(String.format("%s&time=%s&salt=%s", qs, time, key));
		//生成地址	
		address = "http://spark.bokecc.com/api/video/create"+"?"+qs+"&time="+time+"&hash="+hash;
		System.out.println("第一步请求-address="+address);
		// get方式发送请求
		responseStr = APIServiceFunction.HttpRetrieve(address);
		System.out.println("第一步返回-responseStr="+responseStr);
		doc = DemoUtil.build(responseStr);
		// 得到视频信息XML
		Element videoElement = doc.getRootElement();
		String videoid=videoElement.elementText("videoid");
		String servicetype=videoElement.elementText("servicetype");
		System.out.println("videoid="+videoid);
		System.out.println("servicetype="+servicetype);

		
		//第二步
		String md5val=getMd5ByFile(mfile);
		System.out.println("md5val="+md5val);
		treeMap = new TreeMap<String, String>();
		//查询参数输入		 
		treeMap.put("uid", userid);
		treeMap.put("ccvid", videoid);
		treeMap.put("servicetype", servicetype);
		treeMap.put("first", "1");		
		treeMap.put("filename", filename);
		treeMap.put("filesize", filesize+"");
		treeMap.put("md5", md5val);
		//
		qs = createQueryString(treeMap);		
		time = new Date().getTime() / 1000;	
		//生成HASH码值			
		hash =md5(String.format("%s&time=%s&salt=%s", qs, time, key));
		//生成地址	
		address = "http://2.15.vacombiner.bokecc.com/servlet/uploadmeta"+"?"+qs+"&time="+time+"&hash="+hash;
		System.out.println("第二步请求-address="+address);
		// get方式发送请求
		responseStr = APIServiceFunction.HttpRetrieve(address);
		System.out.println("第二步返回-responseStr="+responseStr);
		
		//第三步：分块上传
		if(chunkSize<=1024){
			chunkSize=1024*1024*2;
		}
		int idx=1;
		while(idx*chunkSize<filesize){
			String result =uploadchunk("http://2.15.vacombiner.bokecc.com/servlet/uploadchunk?ccvid="+videoid+"&format=json", chunkSize*(idx-1), chunkSize*idx, mfile);
			idx++;
			System.out.println("result="+result);	
		}
		String result =uploadchunk("http://2.15.vacombiner.bokecc.com/servlet/uploadchunk?ccvid="+videoid+"&format=json", chunkSize*(idx-1), ((int)filesize - 1), mfile);	
		System.out.println("第三步返回-result="+result);
		
		//第四步：返回imageURL
		String videoImg="";
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("userid", userid);
		paramsMap.put("videoid", videoid);
		qs = createQueryString(paramsMap);		
		time = new Date().getTime() / 1000;	
		//生成HASH码值			
		hash =md5(String.format("%s&time=%s&salt=%s", qs, time, key));
		//生成地址	
		address = "http://spark.bokecc.com/api/video"+"?"+qs+"&time="+time+"&hash="+hash;
		System.out.println("第四步请求-address="+address);
//		// get方式发送请求
//		responseStr = APIServiceFunction.HttpRetrieve(address);
//		System.out.println("第四步返回-responseStr="+responseStr);
//		doc = DemoUtil.build(responseStr);
//		// 得到视频信息XML
//		Element rootElement = doc.getRootElement();
//		videoImg=rootElement.elementText("image");
//		System.out.println("videoImg="+videoImg);
		return videoImg;
    }
    public static String getMd5ByFile(File file) {
		String value = null;
		FileInputStream in=null;
		try {
			in = new FileInputStream(file);
			MappedByteBuffer byteBuffer = in.getChannel().map(
					FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
	
	
	/** 以下为THQS算法的相关函数  */	
	
	/**
	 * 功能：用一个Map生成一个QueryString，参数的顺序不可预知。
	 * 
	 * @param queryString
	 * @return
	 */
	public static String createQueryString(Map<String, String> queryMap) {
		if (queryMap == null) {
			return null;
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : queryMap.entrySet()) {
				if (entry.getValue() == null) {
					continue;
				}
				String key = entry.getKey().trim();
				String value = URLEncoder.encode(entry.getValue().trim(),
						"utf-8");
				sb.append(String.format("%s=%s&", key, value));
			}
			return sb.substring(0, sb.length() - 1);
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 功能：计算字符串的md5值
	 * 
	 * @param src
	 * @return
	 */
	public static String md5(String src) {			
		return digest(src, "MD5");			
	}
	/**
	 * 功能：根据指定的散列算法名，得到字符串的散列结果。
	 * 
	 * @param src
	 * @param name
	 * @return
	 */
	private static String digest(String src, String name){
		try {
			MessageDigest alg = MessageDigest.getInstance(name);
			byte[] result = alg.digest(src.getBytes());
			return byte2hex(result);
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}	
	}
	/**
	 * 功能：将byte数组转换成十六进制可读字符串。
	 * @param b 需要转换的byte数组
	 * @return 如果输入的数组为null，则返回null；否则返回转换后的字符串。
	 */
	public static String byte2hex(byte[] b) {		
		if (b == null){
			return null;
		}		
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0" + stmp);				
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}	
	
	
	public static byte[] readChunk(File file, int chunkStart, int chunkEnd) throws IOException{
		if(file == null) {
			throw new IOException("The file does not exist");
		}
		long fileLength = file.length();
		if(chunkStart >= fileLength) {
			throw new IOException("Start position > file length");
		}
		RandomAccessFile accessFile = null;
		try {
			int chunksLen = chunkEnd - chunkStart + 1;
			byte[] chunks = new byte[chunksLen];
			accessFile = new RandomAccessFile(file, "r");
			accessFile.seek(chunkStart);
			int readLength = accessFile.read(chunks, 0, chunksLen);
			System.out.println("read Length: " + readLength);
			accessFile.close();
			return chunks;
		} finally {
			if(accessFile != null) {
				try {
					accessFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/* url为/servlet/uploadchunk?ccvid=&format= */
    /* chunkStart为chunk起始位置*/
    /* chunkEnd为chunk结束位置*/
    /* file为文件*/
    /* bufferOut为实际文件输出二进制内容*/
 	public static String uploadchunk(String url, int chunkStart, int chunkEnd, File file) {
 		byte[] bufferOut = null;
 		try {
			bufferOut = readChunk(file, chunkStart, chunkEnd);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(bufferOut == null) {
			System.out.println("---------------read file chunk error-----------------");
			return "read file error";
		}
		HttpURLConnection conn = null;
		try {
			String BOUNDARY = "---------CCHTTPAPIFormBoundaryEEXX" + new Random().nextInt(65536); // 定义数据分隔线
			URL openUrl = new URL(url);
			conn = (HttpURLConnection)openUrl.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4)");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			// content-range
			conn.setRequestProperty("Content-Range", "bytes " + chunkStart + "-" + chunkEnd + "/" + file.length());
			System.out.println("bytes " + chunkStart + "-" + chunkEnd + "/" + file.length());

			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			StringBuilder sb = new StringBuilder();
			sb.append("--").append(BOUNDARY).append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"file" + file.getName() + "\";filename=\"" + file.getName()
					+ "\"\r\n");
			sb.append("Content-Type: application/octet-stream\r\n");
			sb.append("\r\n");
			byte[] data = sb.toString().getBytes();
			out.write(data);
			out.write(bufferOut);
			out.write("\r\n".getBytes());
			// 定义最后数据分隔线
			byte[] end_data = ("--" + BOUNDARY + "--\r\n").getBytes();
			out.write(end_data);
			out.flush();
			out.close();

			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer resultBuf = new StringBuffer("");
			String line = null;
			while ((line = reader.readLine()) != null) {
				resultBuf.append(line);
			}
			reader.close();
			conn.disconnect();
			return resultBuf.toString();
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return null;
	}
}
