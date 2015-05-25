package com.zzcm.fourgad.job;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.zzcm.fourgad.entity.Record;


public class HttpclientTest {
	
	// **************** 手机归属地查询 *************************
	public static String getAddress(String mobile) throws Exception{
		String soap = readSoap("soap12.xml");
		soap = soap.replaceAll("\\$mobile", mobile);
		byte[] entity = soap.getBytes();
		
		String path = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		conn.getOutputStream().write(entity);
		if(conn.getResponseCode() == 200){
			return parseSOAP(conn.getInputStream());
		}
		return null;
	}
	
	private static String parseSOAP(InputStream xml)throws Exception{
		byte[] data = StreamTool.read(xml);
		return new String(data);
	}

	private static String readSoap(String fileName) throws Exception{
		InputStream inStream = HttpclientTest.class.getClassLoader().getResourceAsStream(fileName);
		byte[] data = StreamTool.read(inStream);
		return new String(data);
	}
	
	// ******************************* metlife ****************
	public static String pingAn() throws Exception{
		String soap = readSoap("soapRecords.xml");
		byte[] entity = soap.getBytes();
		
		String path = "http://icare-uat.metlife.com.cn/services/YSW2ICareSave?wsdl";
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/soap+xml; charset=GBK");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		conn.getOutputStream().write(entity);
		int code = conn.getResponseCode();
		System.out.println("code="+code);
		if(code == 200){
			return parseSOAP(conn.getInputStream());
		}
		return null;
	}
	
	// ******************************* metlife2 ****************
	public static String pingAn2() throws Exception{
		
        DefaultHttpClient httpClient = new DefaultHttpClient();  
//        String soapRequestData = readSoap("soap12.xml") ;  
//        soapRequestData = soapRequestData.replaceAll("\\$mobile", "13752274675");
        
        String soapRequestData = readSoap("soapRecords.xml") ;  
          
        //HttpPost httppost = new HttpPost("http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");  
        HttpPost httppost = new HttpPost("http://icare-uat.metlife.com.cn/services/YSW2ICareSave?wsdl");  
        
        /*把Soap请求数据添加到PostMethod*/  
        //byte[] b = soapRequestData.getBytes("utf-8");  
        //InputStream is = new ByteArrayInputStream(b,0,b.length);  
        try {  
            //HttpEntity re = new StringEntity(soapRequestData,HTTP.UTF_8); 
        	
        	
            //HttpEntity re = new StringEntity(soapRequestData,HTTP.UTF_8);  
        	HttpEntity re = new StringEntity(soapRequestData,"GBK");  
            //soapRequestData = EntityUtils.toString(re , "GBK").trim();   
            
            httppost.setHeader("Content-Type","application/soap+xml; charset=GBK");  
            //httppost.setHeader("Content-Type","application/soap+xml; charset=utf-8"); 
            //httppost.setHeader("Content-Length", String.valueOf(soapRequestData.length()));  
            httppost.setEntity(re);           
            HttpResponse response = httpClient.execute(httppost);  
            System.out.println("1:"+EntityUtils.toString(httppost.getEntity()));  
            System.out.println("2:"+response.getStatusLine());  
            System.out.println("3:"+EntityUtils.toString(response.getEntity()));           
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (ClientProtocolException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
            httpClient.getConnectionManager().shutdown();  
        }
        return null;
	}
	
	// test
	public static void main(String[] args) throws Exception {
		String addr = getAddress("15278416564");
		System.out.println(addr);
		System.out.println("=-=-=-=-");
		String result = pingAn2();
		System.out.println(result);
	}
}
