package com.zzcm.fourgad.web.count;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzcm.fourgad.service.count.CountService;

@Controller
@RequestMapping(value = "/count")
public class CountController {
	
	@Resource
	private CountService countService;
	
	@RequestMapping(value="reqOrdList",method = RequestMethod.GET)
	public String input(Model model,
			HttpServletRequest request,@RequestHeader("user-agent") String ua) {

		return "manage/reqOrdList";
	}
	
	@RequestMapping(value = "getData",method = RequestMethod.POST)
	@ResponseBody
	public String getData(HttpServletRequest request) throws Exception {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String vtime = getParameter(request,"vtime","");
		String channel = getParameter(request,"channel","");
		String draw = getParameter(request,"draw","1");
	    String data = countService.queryCountData(pageNum,pageSize,vtime,channel,draw);
		return data;
	}
	
	@RequestMapping(value = "getPV",method = RequestMethod.POST)
	@ResponseBody
	public String getPV(HttpServletRequest request) throws Exception {
		String vtime = getParameter(request,"vtime","");
		String channel = getParameter(request,"a","");
		String data = countService.queryPV(vtime,channel);
		return data;
	}
	
	/**
	 * 从request中取出name参数的值
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public String getParameter(HttpServletRequest request, String name, String defaultValue) {
		return request.getParameter(name) == null ? defaultValue : request.getParameter(name);
	}
}
