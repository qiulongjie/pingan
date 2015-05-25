package com.zzcm.fourgad.web.ad;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzcm.fourgad.entity.OrdLogs;
import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.util.CakeUtil;

@Controller
@RequestMapping(value = "/pingan2")
public class pinganController {
	@Autowired
	private AdService adService;
	@Autowired
	private CakeUtil cakeUtil;
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
	public String input(Model model,
			HttpServletRequest request,@RequestHeader("user-agent") String ua) {
		
		String channel = request.getParameter("a");
		if(channel==null){
			channel="1000";		
		}else{
			request.setAttribute("a", channel);
		}	
		
		if(ua!=null&&ua.length()>40){
			ua = ua.substring(0,40);
		}
		String ipaddr = getIpAddr(request);
		
		String prov = "";
		prov = request.getParameter("code");
		if(prov==null){
			prov="";		
		}else{
			request.setAttribute("code", prov);
		}
		String vtime = getDateTime();
		
		adService.AddReqLogs(channel, ipaddr, prov, vtime,ua);

		return "pingan/pingan2";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String list(@Valid OrdLogs ordLogs,
			HttpServletRequest request) {
		String uname = ordLogs.getUname();//request.getParameter("uname");
		String birthday =request.getParameter("birthday");
		String ddlSex = ordLogs.getDdlSex(); //request.getParameter("ddlSex");
		String phone = request.getParameter("phone");
		String ipaddr = getIpAddr(request);
		String prov = request.getParameter("code");
		System.out.println("===00000==="+prov);
		String vtime = getDateTime();
		
		String whois = request.getParameter("whois");
		String country = request.getParameter("country");
		String fee = request.getParameter("fee");
		String isneed = request.getParameter("isneed");
		String pcontent = whois+","+country+","+fee+","+isneed;
		String pubcode = request.getParameter("a");
		System.out.println("pingan2="+pcontent);
		adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,pcontent);	
		if(prov!=null&&(!prov.equals(""))){
			String sendurl = "http://m.ad.zhangyue.com/api/trade.do";
			String data = cakeUtil.getData(prov);
			cakeUtil.postInfo2(sendurl, data);
		}
		return "pingan/ok2";
	}
}
