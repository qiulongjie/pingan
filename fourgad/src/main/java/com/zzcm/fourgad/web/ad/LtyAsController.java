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
@RequestMapping(value = "/ltyAs")
public class LtyAsController {
	@Autowired
	private AdService adService;
	
	@RequestMapping(value="ok")
	public String ok() {
		return "pingan/ok_as_zj";
	}
	
	/**
	 * 获取抽奖结果 
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getLottery",method = RequestMethod.POST)
	@ResponseBody
	public String getLottery(HttpServletRequest request) {
		String channel = request.getParameter("a");
		String z = request.getParameter("z");
		String p = request.getParameter("p");
		String c = request.getParameter("c");
		String ip = WebUtil.getIpAddr(request);
		String time = DateUtil.getDateTime();
		String data = adService.getLtyInfo2As(channel,z,p,c,ip,time);
		return data;
	}
	
	/**
	 * 获取爱奇艺抽奖结果
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getLottery2aqy",method = RequestMethod.POST)
	@ResponseBody
	public String getLottery2aqy(HttpServletRequest request) {
		String channel = request.getParameter("a");
		String z = request.getParameter("z");
		String p = request.getParameter("p");
		String c = request.getParameter("c");
		String ip = WebUtil.getIpAddr(request);
		String time = DateUtil.getDateTime();
		String data = adService.getLtyInfo2aqy(channel,z,p,c,ip,time);
		return data;
	}
	
	/**
	 * 获取  掌阅 抽奖结果
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getLottery2zy",method = RequestMethod.POST)
	@ResponseBody
	public String getLottery2zy(HttpServletRequest request) {
		String channel = request.getParameter("a");
		String z = request.getParameter("z");
		String p = request.getParameter("p");
		String c = request.getParameter("c");
		String ip = WebUtil.getIpAddr(request);
		String time = DateUtil.getDateTime();
		String data = adService.getLottery2zy(channel,z,p,c,ip,time);
		return data;
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
		
		adService.addRewardInfo(uname,phone,address,ip,channel,time, ua,ok_title,lty_angle);
		
		return "redirect:/ltyAs/ok";
	}
	
}
