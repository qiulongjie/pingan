package com.zzcm.fourgad.web.ad;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.WebUtil;

@Controller
@RequestMapping(value = "/ping")
public class pingController {
	@Autowired
	private AdService adService;
	
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
		String ipaddr = WebUtil.getIpAddr(request);
		
		String prov = "";
		prov = request.getParameter("code");
		if(prov==null){
			prov="";		
		}else{
			request.setAttribute("code", prov);
		}
		String vtime = DateUtil.getDateTime();
		
		adService.AddReqLogs(channel, ipaddr, prov, vtime,ua);

		return "pingan/ping";
	}
	
	@RequestMapping(value="ok",method = RequestMethod.GET)
	public String ok() {
		return "pingan/ok3";
	}
	
	@RequestMapping(value="submitGet")
	public String list(HttpServletRequest request) {
		String uname = request.getParameter("uname");
		String birthday =request.getParameter("birthday");
		String ddlSex = request.getParameter("ddlSex") == null ? "男" : request.getParameter("ddlSex");
		String phone = request.getParameter("phone");
		String ipaddr = WebUtil.getIpAddr(request);
		String prov = request.getParameter("code") == null ? "" : request.getParameter("code");
		String pubcode = request.getParameter("a");
		
		String vtime = DateUtil.getDateTime();
		
		String whois = request.getParameter("whois");
		String country = request.getParameter("country");
		String fee = request.getParameter("fee");
		String isneed = request.getParameter("isneed");
		String pcontent = whois+","+country+","+fee+","+isneed;
		
		
		adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,pcontent);	
		
		return "redirect:/ping/ok";
	}
}
