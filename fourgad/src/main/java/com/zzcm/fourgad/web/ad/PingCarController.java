package com.zzcm.fourgad.web.ad;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.WebUtil;

@Controller
@RequestMapping(value = "/pingCar")
public class PingCarController {
	@Autowired
	private AdService adService;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String survey(HttpServletRequest request) {
		String carNumber = request.getParameter("carNumber");
		String uname = request.getParameter("uname");
		String birthday =request.getParameter("birthday");
		String ddlSex = request.getParameter("ddlSex") == null ? "男" : request.getParameter("ddlSex");
		String phone = request.getParameter("phone");
		String pubcode = request.getParameter("a");
		String ipaddr = WebUtil.getIpAddr(request);
		String prov = request.getParameter("code") == null ? "" : request.getParameter("code");;
		String vtime = DateUtil.getDateTime();
				
		adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,carNumber);	
		return "redirect:/pa/go/ok";
	}
	
	@RequestMapping(value="surveyGet")
	public String surveyGet(HttpServletRequest request) throws Exception {
		String carNumber = request.getParameter("carNumber");
		String uname = request.getParameter("uname");
		String birthday =request.getParameter("birthday");
		String ddlSex = request.getParameter("ddlSex") == null ? "男" : request.getParameter("ddlSex");
		String phone = request.getParameter("phone");
		String pubcode = request.getParameter("a");
		String ipaddr = WebUtil.getIpAddr(request);
		String prov = request.getParameter("code") == null ? "" : request.getParameter("code");
		String vtime = DateUtil.getDateTime();
		
		adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,carNumber);	
		return "redirect:/pa/go/ok";
	}
	
}
