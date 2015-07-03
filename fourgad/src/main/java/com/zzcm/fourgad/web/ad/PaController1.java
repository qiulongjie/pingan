package com.zzcm.fourgad.web.ad;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping(value = "/pa1")
public class PaController1 {
	@Autowired
	private AdService adService;
	
	@RequestMapping(value = "{path}",method = RequestMethod.GET)
	public String input(@PathVariable("path") String path,HttpServletRequest request,@RequestHeader("user-agent") String ua) {
		String channel = request.getParameter("a");
		if(channel==null || channel.trim().equals("")){
			channel="A7376102";
		}
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
		adService.AddReqLogs(channel, ipaddr, prov, vtime,ua,"1");

		return "pingan/"+path;
	}
	
	@RequestMapping(value="go/{path}")
	public String go(@PathVariable("path") String path,HttpServletRequest request) {
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
