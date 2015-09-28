package com.zzcm.fourgad.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebUtil {
	private static Logger logger = LoggerFactory.getLogger(WebUtil.class);
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
	 * HttpURLConnection 发送请求
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
			urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
			urlConnection.setReadTimeout(SOCKET_TIMEOUT);
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
	 * HttpURLConnection 发送请求
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
	
	// *********************org.apache.http.impl.client.HttpClients ******* start -----
	
	/**
	 * 利用org.apache.http.impl.client.HttpClients发送post请求
	 * @author qiulongjie
	 * @param uri
	 * @param method
	 * @param params
	 * @return
	 */
	public static String postData(String uri,Map<String,Object> params){
		StringBuffer buffer = new StringBuffer();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				buffer.append(entry.getKey()).append("=")
				.append(String.valueOf(entry.getValue()))
				.append("&");// 请求的参数之间使用&分割。
			}
			buffer.deleteCharAt(buffer.length() - 1);
		}
		return postData(uri,buffer.toString());
	}
	
	/**
	 * 利用org.apache.http.impl.client.HttpClients发送post请求
	 * @author qiulongjie
	 * @param uri
	 * @param method
	 * @param params
	 * @return
	 */
	public static String postData(String uri,String params){
		StringEntity myEntity = new StringEntity(params,ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8));// 构造请求数据
        return postInfo(uri,myEntity);
	}
	
	/**
	 * 利用org.apache.http.impl.client.HttpClients发送post请求  发送json数据
	 * @author qiulongjie
	 * @param uri
	 * @param method
	 * @param params
	 * @return
	 */
	public static String postJOSN(String uri,String json){
        StringEntity myEntity = new StringEntity(json,ContentType.APPLICATION_JSON);// 构造请求数据
        return postInfo(uri,myEntity);
	}
	
	/**
	 * 利用org.apache.http.impl.client.HttpClients发送post请求
	 * @author qiulongjie
	 * @param uri
	 * @param myEntity
	 * @return
	 */
	public static String postInfo(String uri,StringEntity myEntity){
		CloseableHttpClient client = HttpClients.custom().setRetryHandler(retryHandler).build();
        //链接配置
        RequestConfig config = RequestConfig.custom().
				                setSocketTimeout(SOCKET_TIMEOUT).
				                setConnectTimeout(CONNECT_TIMEOUT).
				                build();
        HttpPost post = new HttpPost(uri);
        post.setConfig(config);
        post.setEntity(myEntity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            if (null != response && response.getStatusLine().getStatusCode() == 200) {
            	HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "UTF-8");
            }
            return null;
        } catch (ClientProtocolException e) {
        	logger.error(e.toString());
        	e.printStackTrace();
        } catch (IOException e) {
        	logger.error(e.toString());
        	e.printStackTrace();
        }finally{
			closeHttp(client, response);
        }
		return null;
	}
	
	/**
	 * 以GET方式发送数据
	 * @author qiulongjie
	 * @param uri
	 * @return
	 */
	public static String sendInfoForGET(String uri){
		CloseableHttpClient client = HttpClients.custom().setRetryHandler(retryHandler).build();
        //链接配置
        RequestConfig config = RequestConfig.custom().
				                setSocketTimeout(SOCKET_TIMEOUT).
				                setConnectTimeout(CONNECT_TIMEOUT).
				                build();
        HttpGet get = new HttpGet(uri);
        get.setConfig(config);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
            if (null != response && response.getStatusLine().getStatusCode() == 200) {
            	HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "UTF-8");
            }
            return null;
        } catch (ClientProtocolException e) {
        	logger.error(e.toString());
        	e.printStackTrace();
        } catch (IOException e) {
        	logger.error(e.toString());
        	e.printStackTrace();
        }finally{
			closeHttp(client, response);
        }
		return null;
	}

	/** 关闭 */
	private static void closeHttp(CloseableHttpClient client, CloseableHttpResponse response) {
		try {
			if (response != null){
				response.close();
			}
		} catch (IOException e) {
			logger.error(e.toString());
		} finally {
			try {
				if (client != null){
					client.close();
				}
			} catch (IOException e) {
				logger.error(e.toString());
			}
		}
	}
	
	/**
	 * 重连次数
	 */
	private static final int RETRY_CONNECTION_COUNT = 3;
	/**
	 * 响应超时时间
	 */
	private static final int SOCKET_TIMEOUT = 8000;
	/**
	 * 链接超时时间
	 */
	private static final int CONNECT_TIMEOUT = 5000;
	/**
	 * 重连处理器
	 */
	private static HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
        
        /**
         * exception异常信息；
         * executionCount：重连次数；
         * context：上下文
         */
        @Override
        public boolean retryRequest(IOException exception, int executionCount,HttpContext context) {

        	logger.warn("重连接次数："+executionCount+"--导致重连接的异常："+exception+"--导致重连接的http.request："+((HttpClientContext) context).getAttribute("http.request").toString());
            if (executionCount >= RETRY_CONNECTION_COUNT) {//如果连接次数超过RETRY_CONNECTION_COUNT次，就不进行重复连接
                return false;
            }
            if (exception instanceof InterruptedIOException) {//io操作中断
                return false;
            }
            if (exception instanceof UnknownHostException) {//未找到主机
                // Unknown host
                return true;
            }
            if (exception instanceof ConnectTimeoutException) {//连接超时
                return true;
            }
            if (exception instanceof SSLException) {
                // SSL handshake exception
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                // Retry if the request is considered idempotent
                return true;
            }
            return false;
        }
    };
	
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
		//String result = sendData("http://dainar.com/hkout/page/pingan/wap/ywx/submit_ywx","POST", map,"UTF-8",false);
		//String result = postData("http://dainar.com/hkout/page/pingan/wap/ywx/submit_ywx", map);
		//String result = sendInfoForGET("http://123dainar.com/hkout/page/pingan/wap/ywx/submit_ywx");
	    String result = postData("http://192.168.0.53:9292/mad/upload/test", map);
		System.out.println(result);
	}
}
