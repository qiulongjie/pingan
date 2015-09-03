package com.zzcm.fourgad.test;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.zzcm.fourgad.job.JsonUtil;
import com.zzcm.fourgad.util.MD5Util;
import com.zzcm.fourgad.util.WebUtil;

public class HttpUtilsTest {
	private static String parseSOAP(InputStream xml)throws Exception{
		byte[] data = StreamTool.read(xml);
		return new String(data);
	}
	private static void test1() throws Exception{
		String req = "channel=A1234&uname=张三&birthday=19890808&phone=13752274675&ddlSex=女";
		byte[] entity = req.getBytes();
		//String path = "http://192.168.0.53:8989/fourgad/ping/pushOrd";
		//String path = "http://113.31.65.67:8081/ping/pushOrdLog";
		String path = "http://m.iadcn.com/ping/pushOrdLog";
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
	
	private static void test2() throws Exception{
		String req = "name=张三&birth=1980-03-11&phone=15392519000&sex=1&hkPageInfo.id=4&advPosition.id=283&country=中国&province=广东&city=深圳&insType=0&sign="+MD5Util.sign("283HYTX_ZZ_@!h1va#15392519000", "utf-8");
		byte[] entity = req.getBytes();
		//String path = "http://192.168.0.53:8989/fourgad/ping/pushOrd";
		//String path = "http://113.31.65.67:8081/ping/pushOrdLog";
		String path = "http://dainar.com/hkout/page/pingan/wap/ywx/submit_ywx";
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
			Type listType = new TypeToken<Map<String,String>>() { }.getType(); 
			Map<String,String> m = JsonUtil.fromJson(result, listType);
			System.out.println(m);
		}
	}
	
	private static void test3() throws Exception{
		String req = "name=张三&birth=1980-03-11&phone=15392519000&sex=1&hkPageInfo.id=4&advPosition.id=283&country=中国&province=广东&city=深圳&insType=0&sign="+MD5Util.sign("283HYTX_ZZ_@!h1va#15392519000", "utf-8");
		String path = "http://dainar.com/hkout/page/pingan/wap/ywx/submit_ywx";
		String string = WebUtil.sendData(path, "POST", req, "utf-8");
		System.out.println(string);
	}
	
	public static void main(String[] args) throws Exception {
		test3();
	}
}
