package com.zzcm.fourgad.web.ad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping(value = "/pingLottery")
public class PingLotteryController {
	@Autowired
	private AdService adService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String input(Model model,
			HttpServletRequest request,@RequestHeader("user-agent") String ua) {
		String channel = request.getParameter("a");
		if(channel==null){
			channel="A7376102";
		}
		request.setAttribute("a", channel);
			
		if(ua!=null&&ua.length()>40){
			ua = ua.substring(0,40);
		}
		String ipaddr = WebUtil.getIpAddr(request);
		
		String prov = request.getParameter("code") == null ? "" : request.getParameter("code");
		request.setAttribute("code", prov);
		String vtime = DateUtil.getDateTime();
		adService.AddReqLogs(channel, ipaddr, prov, vtime,ua);

		return "pingan/pingLottery";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String list(HttpServletRequest request) {
		String uname = request.getParameter("uname");
		String birthday =request.getParameter("birthday");
		String ddlSex = request.getParameter("ddlSex") == null ? "男" : request.getParameter("ddlSex");
		String phone = request.getParameter("phone");
		String pubcode = request.getParameter("a");
		String ipaddr = WebUtil.getIpAddr(request);
		String prov = request.getParameter("code") == null ? "" : request.getParameter("code");;
		String vtime = DateUtil.getDateTime();
				
		adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,"");	
		return "redirect:/pingLottery/ok";
	}
	
	@RequestMapping(value="ok")
	public String ok() {
		return "pingan/ok";
	}
	
	/**
	 * 获取抽奖结果 -- 留着以后用 目前就是只有三等奖  返回转盘的旋转的角度 120 若要中其他奖项需要可以自己计算
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getLottery",method = RequestMethod.POST)
	public String getLottery(HttpServletResponse response) {
		WebUtil.sendData(response, "120");
		return null;
	}
	
	/**
	 * 获取抽奖结果 -- 360 / 10 * 8 = 288
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getLottery2",method = RequestMethod.POST)
	public String getLottery2(HttpServletResponse response) {
		WebUtil.sendData(response, "288");
		return null;
	}
	
}
