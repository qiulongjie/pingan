package com.zzcm.fourgad.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
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
}
