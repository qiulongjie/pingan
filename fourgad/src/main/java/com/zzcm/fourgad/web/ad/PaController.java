package com.zzcm.fourgad.web.ad;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.WebUtil;

@Controller
@RequestMapping(value = "/pa")
public class PaController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdService adService;
	
	@RequestMapping(value = "{path}",method = RequestMethod.GET)
	public String input(@PathVariable("path") String path,HttpServletRequest request,@RequestHeader("user-agent") String ua) {
		path = path.toLowerCase();
		String channel = request.getParameter("a");
		if(channel==null || channel.trim().equals("")){
			channel="A7376102";
		}
		if( channel.length() > 20 ){
			logger.warn("渠道长度超过20字符 对此渠道进行截取channel="+channel+",ua="+ua+",url="+request.getRequestURL().toString()+"?"+request.getQueryString());
			channel = channel.substring(0,20);
		}
		channel = channel.replace("a", "A");
		request.setAttribute("a", channel);
			
		if(ua!=null&&ua.length()>40){
			ua = ua.substring(0,40);
		}
		String ipaddr = WebUtil.getIpAddr(request);
		
		String p = request.getParameter("p");
		if(p==null || p.trim().equals("")){
			p="";
		}
		request.setAttribute("p", p);
		
		String p2 = request.getParameter("p2");
		if(p2==null || p2.trim().equals("")){
			p2="pingLottery";
		}
		request.setAttribute("p2", p2);
		
		request.setAttribute("path", path);
		
		String prov = request.getParameter("code") == null ? "" : request.getParameter("code");
		request.setAttribute("code", prov);
		String vtime = DateUtil.getDateTime();
		if(path.startsWith("lty") || 
		   path.startsWith("dainarLy") || 
		   path.startsWith("survey") || 
		   path.startsWith("lotteryDai") || 
		   path.startsWith("pingLottery")){
			adService.AddReqLogs(channel, ipaddr, prov, vtime,ua);
		}else if(path.startsWith("niwodai")){
			adService.AddReqLogs(channel, ipaddr, prov, vtime,ua,"2");
		}else if(path.equals("dai")){
			String cid = request.getParameter("cid");
			adService.addDaiLogs(cid ,ipaddr, vtime,ua);
		}else{
			adService.AddReqLogs(channel, ipaddr, prov, vtime,ua,"1");
		}

		return "pingan/"+path;
	}
	
	@RequestMapping(value="go/{path}")
	public String go(@PathVariable("path") String path,HttpServletRequest request) {
		path = path.toLowerCase();
		return "pingan/"+path;
	}
	
	@RequestMapping(value="/submit/survey")
	public String survey(String a,String p,String p2) {
		if( a == null ){
			return "redirect:/pa/"+p2+"?p="+p;
		}
		return "redirect:/pa/"+p2+"?a="+a+"&p="+p;
	}
	
	@RequestMapping(value="ok")
	public String ok() {
		return "redirect:/pa/go/ok";
	}
}
