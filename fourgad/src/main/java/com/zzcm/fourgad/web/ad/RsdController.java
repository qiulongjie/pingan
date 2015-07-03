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
@RequestMapping(value = "/rsd")
public class RsdController {
	@Autowired
	private AdService adService;
	
	@RequestMapping(value="feeData",method = RequestMethod.POST)
	@ResponseBody
	public String feeData(HttpServletRequest request) {
		String feeData = request.getParameter("feeData");
		String ipaddr = WebUtil.getIpAddr(request);
		String vtime = DateUtil.getDateTime();
		try {
			adService.saveFeeData(feeData,ipaddr,vtime);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}
	
}
