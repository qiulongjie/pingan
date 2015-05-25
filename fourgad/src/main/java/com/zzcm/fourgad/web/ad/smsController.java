package com.zzcm.fourgad.web.ad;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzcm.fourgad.entity.Sms;
import com.zzcm.fourgad.service.ad.SmsService;

@Controller
@RequestMapping(value = "/api/sms")
public class smsController {
	@Autowired
	private SmsService smsService;
	public String getDateTime(){
		Date date=new Date();//取时间				
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);	 
		return dateString;
	}
	
	public String getIpAddr(HttpServletRequest request) { 		
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
	    	    
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    	ip = request.getRemoteAddr();	    	
	    } 	   
	    return ip;  
	}
	@RequestMapping(method = RequestMethod.GET)
	public String input(@Valid Sms entity,
			HttpServletRequest request) {		
		 String c =  entity.getC();
		 try{
			 c = new String(c.getBytes("ISO-8859-1"),"UTF-8");
			 entity.setC(c);
		 }catch(Exception e){			 
		 }		 
		 smsService.saveSms(entity);
		 System.out.println(entity.getC());
		return "api/sms";
	}
	
	
}
