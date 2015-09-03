package com.zzcm.fourgad.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zzcm.fourgad.web.ad.pingController;

public class WebUtil {
	private static Logger logger = LoggerFactory.getLogger(pingController.class);
	/**
	 * 获取IP
	 * @param request
	 * @return
	 * 修改获取ip方法，对于10.内网段重新获取远程ip地址
	 */
	public static String getIpAddr(HttpServletRequest request) { 		
	    String ip = request.getHeader("x-forwarded-for");  
	    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    } 
	    
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    	ip = request.getHeader("HTTP_X_FORWARDED_FOR");	    	
	    }
	    	    
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)||ip.startsWith("10.")) {
	    	ip = request.getRemoteAddr();	    	
	    } 	   
	    return ip;  
	}
	
	/**
	 * 向客户端发送信息
	 * @param response
	 * @param data
	 */
	public static void sendData(HttpServletResponse response, String data) {
		PrintWriter printWriter = null;
		try {
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			printWriter.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}
	
	/**
	 * 发送请求
	 * @author qiulongjie
	 * @param uri
	 * @param method
	 * @param params
	 * @param encode
	 * @return
	 */
	public static String sendData(String uri,String method,String params,String encode){
		try {
			URL url = new URL(uri);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(5000);
			// 设置允许输入输出
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			byte[] mydata = params.getBytes();
			// 设置请求报文头，设定请求数据类型
			urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 设置请求数据长度
			urlConnection.setRequestProperty("Content-Length", String.valueOf(mydata.length));
			// 设置POST方式请求数据
			urlConnection.setRequestMethod(method);
			OutputStream outputStream = urlConnection.getOutputStream();
			outputStream.write(mydata);
			outputStream.flush();
			outputStream.close();
			int responseCode = urlConnection.getResponseCode();
			if (responseCode == 200) {
				return changeInputStream(urlConnection.getInputStream(), encode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 发送请求
	 * @author qiulongjie
	 * @param uri
	 * @param method
	 * @param params
	 * @param encode
	 * @return
	 */
	public static String sendData(String uri,String method,Map<String,Object> params,String encode,boolean isUrlencode){
		StringBuffer buffer = new StringBuffer();
		if (params != null && !params.isEmpty()) {
			if(isUrlencode){
				try {
					for (Map.Entry<String, Object> entry : params.entrySet()) {
						buffer.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(String.valueOf(entry.getValue()),encode))
						.append("&");// 请求的参数之间使用&分割。
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}else{
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					buffer.append(entry.getKey()).append("=")
					.append(String.valueOf(entry.getValue()))
					.append("&");// 请求的参数之间使用&分割。
				}
			}
			buffer.deleteCharAt(buffer.length() - 1);
		}
		logger.info(buffer.toString());
		logger.info(uri);
		return sendData(uri, method, buffer.toString(), encode);
	}
	
	/**
	 * 把输入流转为字符串
	 * @author qiulongjie
	 * @param inputStream
	 * @param encode
	 * @return
	 */
	public static String changeInputStream(InputStream inputStream, String encode) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String result = "";
		if (inputStream != null) {
			try {
				while ((len = inputStream.read(data)) != -1) {
					outputStream.write(data, 0, len);
				}
				result = new String(outputStream.toByteArray(), encode);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name","鄢佰华");
		map.put("birth","1978-06-12");
		map.put("phone","13767034122");
		map.put("sex","1");
		map.put("hkPageInfo.id","4");
		map.put("advPosition.id","283");
		map.put("country","中国");
		map.put("province","江西");
		map.put("city","赣州");
		map.put("insType","0");
		map.put("sign","64518f6de50b0ec7c21cd74bdaab47a1");
		String result = sendData("http://dainar.com/hkout/page/pingan/wap/ywx/submit_ywx", "POST", map, "utf-8", false);
		System.out.println(result);
	}
}
