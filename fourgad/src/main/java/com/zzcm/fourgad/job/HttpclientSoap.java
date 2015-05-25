package com.zzcm.fourgad.job;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpclientSoap {
	
	public String getRequestData(){
		String data = "<?xml version=\"1.0\" encoding=\"GBK\"?>"
+"<Records>"
	+"<Record>"
		+"<Customer>"
			+"<Key>FromSystem000000000000000000001</Key>"						
			+"<FromSystem></FromSystem>"
			+"<Name>张三</Name>"
			+"<Sex>Male</Sex>"         			
			+"<Birthday>1974-11-15</Birthday>" 
			+"<Document></Document>"		 
			+"<DocumentType>IdentityCard</DocumentType>"
				
			+"<Email></Email>"				
			+"<Mobile>13800138000</Mobile>" 	         
			+"<ContactState>"
				+"<Name>北京市</Name>"			
			+"</ContactState>"
			+"<ContactCity>"
				+"<Name>北京市</Name>	"		
			+"</ContactCity>"
			+"<ContactAddress>昌平</ContactAddress>" 
			+"<Occupation>"
				+"<Code>0001001</Code>"
			+"</Occupation>"
			+"<Description />"
		+"</Customer>"
		+"<Task>"
			+"<CallList>"
				+"<Name></Name>"
				
			+"</CallList>"
			+"<Campaign>"
				+"<Name></Name>"				
			+"</Campaign>"
		+"</Task>"
		+"<Activity>"
			+"<Code>"				
			+"</Code>"    
			+"<Present>"
				+"<Code></Code>"				
			+"</Present>"
			+"<TSR>"
				+"<Code>805095</Code>"      
			+"</TSR>"
			+"<DonateTime></DonateTime>"    
			+"<SMS>1</SMS>"                 
      +"<FlghtNo />"									
      +"<ValidTime />"								 
		+"</Activity>"
	+"</Record>"
+"</Records>";
		return data;
	}
	 public static void main(String[] args){  
		    HttpclientSoap soap = new HttpclientSoap();
	        DefaultHttpClient httpClient = new DefaultHttpClient();  
	        String soapRequestData = soap.getRequestData() ;  
	          
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
	          
	    }  
}
