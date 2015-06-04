package com.zzcm.fourgad.job;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import cn.com.metlife.icare.webservice.YSW2ICareSaveServiceLocator;
import cn.com.metlife.icare.webservice.YSW2ICareSave_PortType;

import com.zzcm.fourgad.entity.HolderIdentify;
import com.zzcm.fourgad.util.RecordXmlUtil;


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
		String soap = readSoap("soapRecords2.xml");
		byte[] entity = soap.getBytes();
		
		String path = "http://icare-uat.metlife.com.cn/services/YSW2ICareSave";
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
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
        String soapRequestData = readSoap("soapRecords2.xml") ;  
        //HttpPost httppost = new HttpPost("http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");  
        HttpPost httppost = new HttpPost("http://icare-uat.metlife.com.cn/services/YSW2ICareSave");  
        /*把Soap请求数据添加到PostMethod*/  
        //byte[] b = soapRequestData.getBytes("utf-8");  
        //InputStream is = new ByteArrayInputStream(b,0,b.length);  
        try {  
        	HttpEntity re = new StringEntity(soapRequestData,"GBK");  
            httppost.setHeader("Content-Type","application/soap+xml; charset=GBK");  
            httppost.setEntity(re); 
            HttpResponse response = httpClient.execute(httppost); 
            HttpParams params = httppost.getParams();
            System.out.println("1:"+EntityUtils.toString(httppost.getEntity()));  
            System.out.println("2:"+response.getStatusLine());  
            System.out.println("3:"+EntityUtils.toString(response.getEntity()));           
//            System.out.println("3:"+EntityUtils.toString(response.get));           
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            httpClient.getConnectionManager().shutdown();  
        }
        return null;
	}
	
	private static void axisTest() throws Exception{
		/*String xmlPath = "D:\\YSW2iCareSave.xml";
		Document doc = null;
		SAXBuilder builder = new SAXBuilder(); 
		doc = (Document) builder.build(new File(xmlPath)); 
		XMLOutputter xmlout = new XMLOutputter();
		Format tFormat =  Format.getCompactFormat();
		tFormat.setEncoding("GBK");
		xmlout.setFormat(tFormat);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		xmlout.output(doc,bo);
		String xmlStr = bo.toString();
		System.out.println("Request: ");
		System.out.println(xmlStr);*/
		
		YSW2ICareSaveServiceLocator loc = new YSW2ICareSaveServiceLocator();
		loc.setYSW2ICareSaveEndpointAddress("http://icare-uat.metlife.com.cn/services/YSW2ICareSave");
		
		YSW2ICareSave_PortType service = loc.getYSW2ICareSave();
		String res = service.doRequest(readSoap("YSW2iCareSave.xml"));
		System.out.println("Response: ");
		System.out.println(res);
		HolderIdentify holder = RecordXmlUtil.parserXmlResult(res);
		System.out.println(holder);
	}
	// test
	public static void main(String[] args) throws Exception {
//		String addr = getAddress("13752274675");
//		System.out.println(addr);
//		System.out.println("=-=-=-=-");
		//String result = pingAn();
		//System.out.println(result);
		axisTest();
	}
}
