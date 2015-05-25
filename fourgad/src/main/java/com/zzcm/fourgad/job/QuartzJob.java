package com.zzcm.fourgad.job;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzcm.fourgad.service.ad.AdService;


public class QuartzJob {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private AdService adService;
	public void work()
    {
		System.out.println("====Send====");
		//billService.SendData();
		//String url = "http://rongtimes.com/api/data.ashx";
		String url = "http://jixianghutong.com/api/data.ashx";
		SendData(url);
		System.out.println("====End====");
    }
	
	/*
	 * private String Name;
	private String Sex;
	private String Mobile;
	private String Birthday;
	private String Ip;
	private String PubCode;
	 */
	
	public void SendData(String url){
		List<Map<String,Object>> list = adService.getOrdsByFlag(0, 100);
		for(Map l:list){
			Long id = new Long(Integer.parseInt(l.get("id").toString()));
			PostBean bean = new PostBean();
			bean.setName(l.get("uname").toString());
			bean.setSex(l.get("ddlSex").toString());
			bean.setMobile(l.get("phone").toString());
			bean.setBirthday(l.get("birthday").toString());
			bean.setIp(l.get("ipaddr").toString());
			
			String pubcode = "",pcontent="";
			if(l.get("pubcode")==null){
				pubcode = "A7375893";
			}else{
				pubcode = l.get("pubcode").toString();
			}
			if(l.get("pcontent")==null){
				pcontent = "";
			}else{
				pcontent = l.get("pcontent").toString();
			}
			bean.setPubCode(pubcode);
			bean.setRemark(pcontent); 
			Gson gson = new Gson();
	    	String json = gson.toJson(bean); 
	    	System.out.println(json);
	    	String str = postInfo(url,json);
	    	System.out.println(str);
	    	Type listType = new TypeToken<List<RetBean>>() {  
	        }.getType(); 
	        List<RetBean> rets = JsonUtil.fromJson(str, listType);
	        if(rets.size()>0){
	        	RetBean ret = new RetBean();
	        	ret = rets.get(0);
		    	if(ret!=null){
		    		String code = ret.getRetCode();
		    		String msg = ret.getRetMsg();
		    		System.out.println("code="+code);
		    		System.out.println("msg="+msg);
		    		if(code==null)code = "0";
		    		if(code.equals("1")){
		    			adService.updOrdLogs(id,1,msg);
		    		}else{
		    			adService.updOrdLogs(id,2,msg);
		    		}
		    	}
	        }
		}
	}
	
	public String postInfo(String sendurl, String data) {
		
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(sendurl);
        StringEntity myEntity = new StringEntity(data,
                ContentType.APPLICATION_JSON);// 构造请求数据
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
		         return responseContent;
		     }
}
