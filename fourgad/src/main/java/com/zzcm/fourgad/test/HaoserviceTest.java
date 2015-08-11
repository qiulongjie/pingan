package com.zzcm.fourgad.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HaoserviceTest {

	private static String parseSOAP(InputStream xml)throws Exception{
		byte[] data = StreamTool.read(xml);
		return new String(data);
	}
	private static String changeInputStream(InputStream inputStream, String encode) {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public static String sendPostMessage(String uri, String params, String encode) {
		//StringBuffer buffer = new StringBuffer();
		if (params != null && !params.isEmpty()) {
			/*for (Map.Entry<String, String> entry : params.entrySet()) {
				try {
					buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), encode))
							.append("&");// 请求的参数之间使用&分割。
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			}
			buffer.deleteCharAt(buffer.length() - 1);*/
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
				urlConnection.setRequestMethod("POST");
				OutputStream outputStream = urlConnection.getOutputStream();
				outputStream.write(mydata);
				outputStream.flush();
				outputStream.close();
				int responseCode = urlConnection.getResponseCode();
				System.out.println("responseCode="+responseCode);
				if (responseCode == 200) {
					return changeInputStream(urlConnection.getInputStream(), encode);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	private static void test1() throws Exception{
		String req = "mcc=460&mnc=0&lac=42342&cid=45993927&hex=10&__RequestVerificationToken=CPurgADMUyoCz8C2NBMTAh440aQsuB3fTMGXESHgs8RgQlbul256FfO749KCi3lx1HL53UEXPXuI+hqcu+yfPIpBDBoDuOKz7wYGRQxQ8ZLw2qQAklSBsj5rgYtYtUFZGfY5tLel98VvR7J53dutewujM6w=";
		byte[] entity = req.getBytes();
		
		
		String path = "http://www.haoservice.com/home/cellapi";
		//String path = "http://192.168.0.53:8989/fourgad/ping/rmi/addOrd";
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		//conn.setRequestProperty("Content-Type", "text/HTML; charset=utf-8");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		conn.getOutputStream().write(entity);
		int code = conn.getResponseCode();
		System.out.println(code);
		if(code == 200){
			String result = parseSOAP(conn.getInputStream());
			System.out.println(result);
		}
	}
	
	private static void testRsd() throws Exception{
		String req = "feeData=[{\"date\":\"20150608\",\"data\":[{\"channel\": \"A7375511\",\"fee_count\": \"4\",\"fee\": \"28\"},{\"channel\": \"A7897113\",\"fee_count\": \"63\",\"fee\": \"441\"},{\"channel\": \"A8092089\",\"fee_count\": \"3\",\"fee\": \"21\"},{\"channel\": \"A8462119\",\"fee_count\": \"2\",\"fee\": \"14\"},{\"channel\": \"A8523094\",\"fee_count\": \"152\",\"fee\": \"1064\"},{\"channel\": \"A8810109\",\"fee_count\": \"11\",\"fee\": \"77\"},{\"channel\": \"A8835115\",\"fee_count\": \"9\",\"fee\": \"63\"},{\"channel\": \"A2592026\",\"fee_count\": \"149\",\"fee\": \"1043\"},{\"channel\": \"A3800021\",\"fee_count\": \"3\",\"fee\": \"21\"},{\"channel\": \"A3908028\",\"fee_count\": \"7\",\"fee\": \"49\"},{\"channel\": \"A4312039\",\"fee_count\": \"22\",\"fee\": \"154\"},{\"channel\": \"A5164038\",\"fee_count\": \"3\",\"fee\": \"21\"},{\"channel\": \"A5294036\",\"fee_count\": \"4\",\"fee\": \"28\"},{\"channel\": \"A6131041\",\"fee_count\": \"45\",\"fee\": \"315\"},{\"channel\": \"A6256030\",\"fee_count\": \"64\",\"fee\": \"448\"},{\"channel\": \"A2458677\",\"fee_count\": \"15\",\"fee\": \"105\"},{\"channel\": \"A7374561\",\"fee_count\": \"0\",\"fee\": \"0\"}]}]";
		byte[] entity = req.getBytes();
		
		
		//String path = "http://m.iadcn.com/rsd/feeData";
		String path = "http://113.31.65.67:8081/rsd/feeData";
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		//conn.setRequestProperty("Content-Type", "text/HTML; charset=utf-8");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		conn.getOutputStream().write(entity);
		int code = conn.getResponseCode();
		System.out.println(code);
		if(code == 200){
			String result = parseSOAP(conn.getInputStream());
			System.out.println(result);
		}
	}
	
	private static void testPingAn() throws Exception{
		String req = "CUST_NAME=lishishi&CUST_MOBILETELEPHONE=15278416568&CUST_HOBBY=1985";
		byte[] entity = req.getBytes();
		
		
		//String path = "http://m.iadcn.com/rsd/feeData";
		String path = "http://m.pingan.com/c2/xinyidai/yuyue/action_adjust_001.jsp?v=2";
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		//conn.setRequestProperty("Content-Type", "text/HTML; charset=utf-8");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		conn.getOutputStream().write(entity);
		int code = conn.getResponseCode();
		System.out.println(code);
		if(code == 200){
			String result = parseSOAP(conn.getInputStream());
			System.out.println(result);
		}
	}
	
	private static void test2() {
		String req = "mcc=460&mnc=0&lac=42342&cid=45993927&hex=10&__RequestVerificationToken=CPurgADMUyoCz8C2NBMTAh440aQsuB3fTMGXESHgs8RgQlbul256FfO749KCi3lx1HL53UEXPXuI+hqcu+yfPIpBDBoDuOKz7wYGRQxQ8ZLw2qQAklSBsj5rgYtYtUFZGfY5tLel98VvR7J53dutewujM6w=";
		String path = "http://www.haoservice.com/home/cellapi";
		String re = sendPostMessage(path,req,"utf-8");
		System.out.println(re);
	}
	public static void main(String[] args) throws Exception {
		testPingAn();
	}
}
