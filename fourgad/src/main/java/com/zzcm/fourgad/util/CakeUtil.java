package com.zzcm.fourgad.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class CakeUtil {
private Logger logger = Logger.getLogger(this.getClass());
	
	public String getSn(){
		return DateUtil.getDateTime2();
	}
	
	public String MD5Str(String str){
		return MD5Util.string2MD5(str);
	}
	
	public String getData(String sn){
		String time = DateUtil.getDateTime2();
		String agentid = "2",key="FgbC5V82hlZ7reeE",cmd="2001",timestamp=DateUtil.getDateTime2();
		String msg = "<activities sn=\""+sn+"\"/>";
		String str = agentid+key+timestamp+msg;
		StringBuffer sb = new StringBuffer();
		
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<msg v=\"1.0\" id=\""+time+"\">");
		sb.append("<head>");
		sb.append("<agentid>"+agentid+"</agentid><cmd>"+cmd+"</cmd><timestamp>"+time+"</timestamp><cipher>"+MD5Str(str)+"</cipher>");
		sb.append("</head>");
		sb.append("<body>"+msg+"</body></msg>");
		return sb.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sendurl = "http://m.ad.zhangyue.com/api/trade.do";
		CakeUtil t = new CakeUtil();
		String data = t.getData("YI49-2380-7557-4457");
		
		System.out.println(data);
		System.out.println(t.postInfo2(sendurl, data));
		
		
	}

	public String postInfo2(String sendurl,String data){
		 CloseableHttpClient client = HttpClients.createDefault();
	        HttpPost post = new HttpPost(sendurl);
	        logger.error("sendurl = "+sendurl);
	        StringEntity myEntity = new StringEntity(data,
	                ContentType.APPLICATION_XML );// 构造请求数据
	        post.setEntity(myEntity);// 设置请求体
	        String responseContent = null; // 响应内容
	        CloseableHttpResponse response = null;
			         try {
			             response = client.execute(post);
			             if (response.getStatusLine().getStatusCode() == 200) {
			                 HttpEntity entity = response.getEntity();
			                 responseContent = EntityUtils.toString(entity, "UTF-8");
			             }
			         } catch (ClientProtocolException e) {
			        	 logger.error(e.toString());
			             
			         } catch (IOException e) {
			        	 logger.error(e.toString());
			             
			         } finally {
			             try {
			                 if (response != null)
			                     response.close();
			 
			             } catch (IOException e) {
			            	 logger.error(e.toString());		                 
			             } finally {
			                 try {
			                     if (client != null)
			                         client.close();
			                 } catch (IOException e) {
			                	 logger.error(e.toString());		                     
			                 }
			             }
			         }
			         logger.error(responseContent);
		return responseContent;
	}
}
