package com.zzcm.fourgad.job;

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
		String req = "feeData=[{\"date\":\"20150613\",\"data\":[{\"channel\":\"aaa\",\"fee_count\":\"567\",\"fee\":\"1040.00\"},{\"channel\":\"vvv\",\"fee_count\":\"1100\",\"fee\":\"1066.00\"}]},{\"date\":\"20150605\",\"data\":[{\"channel\":\"qwe\",\"fee_count\":\"444\",\"fee\":\"555.12\"},{\"channel\":\"wee\",\"fee_count\":\"233\",\"fee\":\"677.66\"}]}]";
		byte[] entity = req.getBytes();
		
		
		String path = "http://m.iadcn.com/rsd/feeData";
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
	
	private static void test2() {
		String req = "mcc=460&mnc=0&lac=42342&cid=45993927&hex=10&__RequestVerificationToken=CPurgADMUyoCz8C2NBMTAh440aQsuB3fTMGXESHgs8RgQlbul256FfO749KCi3lx1HL53UEXPXuI+hqcu+yfPIpBDBoDuOKz7wYGRQxQ8ZLw2qQAklSBsj5rgYtYtUFZGfY5tLel98VvR7J53dutewujM6w=";
		String path = "http://www.haoservice.com/home/cellapi";
		String re = sendPostMessage(path,req,"utf-8");
		System.out.println(re);
	}
	public static void main(String[] args) throws Exception {
		testRsd();
	}
}
