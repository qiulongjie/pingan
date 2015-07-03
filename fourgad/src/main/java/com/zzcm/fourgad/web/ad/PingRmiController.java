package com.zzcm.fourgad.web.ad;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.WebUtil;

@Controller
@RequestMapping(value = "/ping/rmi")
public class PingRmiController {
	@Autowired
	private AdService adService;
	
	@RequestMapping(value="addOrd",method = RequestMethod.POST)
	@ResponseBody
	public String addOrd(HttpServletRequest request,String data) {
		String uname = request.getParameter("uname");
		String birthday =request.getParameter("birthday");
		String ddlSex = request.getParameter("ddlSex") == null ? "男" : request.getParameter("ddlSex");
		String phone = request.getParameter("phone");
		String ipaddr = WebUtil.getIpAddr(request);
		String prov = request.getParameter("code") == null ? "" : request.getParameter("code");
		String pubcode = request.getParameter("mcc");
		
		String vtime = DateUtil.getDateTime();
		
		String whois = request.getParameter("whois");
		String country = request.getParameter("country");
		String fee = request.getParameter("fee");
		String isneed = request.getParameter("isneed");
		String pcontent = whois+","+country+","+fee+","+isneed;
		
		
		//adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,pcontent);	
		
		return "";
	}
}
