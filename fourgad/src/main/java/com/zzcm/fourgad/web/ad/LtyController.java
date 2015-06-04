package com.zzcm.fourgad.web.ad;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.WebUtil;

@Controller
@RequestMapping(value = "/lty")
public class LtyController {
	@Autowired
	private AdService adService;
	
	@RequestMapping(value="ok")
	public String ok(String path) {
		return "pingan/"+path;
	}
	
	//  获取转盘初始化信息
	@RequestMapping(value="getLtyIniyInfo",method = RequestMethod.POST)
	@ResponseBody
	public String getLtyIniyInfo(String a,String ltyType) {
		if( a != null ){
			if(ltyType == null || ltyType.trim().equals("")){
				ltyType = "1";
			}
			String info = adService.getLtyInitInfo(a,ltyType);
			return info;
		}
		return null;
	}
	
	@RequestMapping(value="getTheFuckingyxddan")
	@ResponseBody
	public String getTheFuckingyxddan(String a) {
		if( a != null ){
			String info = adService.getEftCntNum(a);
			return info;
		}
		return null;
	}
	
	
	/**
	 * 添加中奖信息
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="addRewardInfo")
	public String addRewardInfo(HttpServletRequest request ,@RequestHeader("user-agent") String ua) throws Exception {
		String channel = request.getParameter("a");
		String uname = request.getParameter("uname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String ok_title = request.getParameter("ok_title");
		//ok_title = java.net.URLDecoder.decode(ok_title, "UTF-8");//一次解码
		
		String lty_angle = request.getParameter("lty_angle");
		//ok_info = java.net.URLDecoder.decode(ok_info, "UTF-8");//一次解码
		
		String ip = WebUtil.getIpAddr(request);
		String time = DateUtil.getDateTime();
		
		if(ua!=null&&ua.length()>40){
			ua = ua.substring(0,40);
		}
		
		String path = request.getParameter("path");
		
		adService.addRewardInfo(uname,phone,address,ip,channel,time, ua,ok_title,lty_angle);
		return "redirect:/lty/ok?path="+path;
	}
	
}
