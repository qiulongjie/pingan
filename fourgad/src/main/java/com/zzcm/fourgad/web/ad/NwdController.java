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
@RequestMapping(value = "/nwd")
public class NwdController {
	@Autowired
	private AdService adService;
	// ==========  你我贷  start 
	@RequestMapping(value="niwodai",method = RequestMethod.POST)
	@ResponseBody
	public String niwodai(HttpServletRequest request) {
		String cid = request.getParameter("a");
		String uname = request.getParameter("uname");
		String phone = request.getParameter("phone");
		String birthday = request.getParameter("birthday");
		if( birthday == null ){
			birthday = "";
		}
		String isCheck = request.getParameter("isCheck");
		isCheck = isCheck == null ? "0" : isCheck ;
		String ipaddr = WebUtil.getIpAddr(request);
		String vtime = DateUtil.getDateTime();
		String channel = adService.addNiwodaiOrdLog(cid,uname,phone,birthday,isCheck,ipaddr,vtime);
		return channel;
	}
	// ==========  你我贷  END 
}
