package com.zzcm.fourgad.web.count;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzcm.fourgad.service.count.CountService;

@Controller
@RequestMapping(value = "/count")
public class CountController {
	
	@Resource
	private CountService countService;
	
	@RequestMapping(value = "{path}",method = RequestMethod.GET)
	public String input(@PathVariable("path") String path,HttpServletRequest request) {

		return "manage/"+path;
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
	
	@RequestMapping(value = "getData2",method = RequestMethod.POST)
	@ResponseBody
	public String getData2(HttpServletRequest request) throws Exception {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String begin_vtime = getParameter(request,"begin_vtime","");
		String end_vtime = getParameter(request,"end_vtime","");
		String channel = getParameter(request,"channel","");
		String draw = getParameter(request,"draw","1");
	    String data = countService.queryCountData(pageNum,pageSize,begin_vtime,end_vtime,channel,draw);
		return data;
	}
	
	/**
	 * 获取当天进行折叠统计
	 * @param request
	 * @param vtime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "showDataForDay")
	public String showDataForDay(HttpServletRequest request,String vtime) throws Exception {
		request.setAttribute("vtime", vtime);
		return "manage/ordDayList";
	}
	
	/**
	 * 获取当天某渠道按小时进行折叠统计
	 * @param request
	 * @param vtime
	 * @param a
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "showDataForHour")
	public String showDataForHour(HttpServletRequest request,String vtime,String a) throws Exception {
		request.setAttribute("vtime", vtime);
		request.setAttribute("a", a);
		return "manage/ordHourList";
	}
	
	@RequestMapping(value = "getDataForHour",method = RequestMethod.POST)
	@ResponseBody
	public String getDataForHour(HttpServletRequest request) throws Exception {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String vtime = getParameter(request,"vtime","");
		String channel = getParameter(request,"channel","");
		String draw = getParameter(request,"draw","1");
	    String data = countService.queryCountDataForHour(pageNum,pageSize,vtime,channel,draw);
		return data;
	}
	
	// 获取中奖信息
	@RequestMapping(value = "getZJData",method = RequestMethod.POST)
	@ResponseBody
	public String getZJData(HttpServletRequest request) throws Exception {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String channel = getParameter(request,"channel","");
		String draw = getParameter(request,"draw","1");
		String data = countService.getZJData(pageNum,pageSize,channel,draw);
		return data;
	}
	
	// 更新中奖信息的状态
	@RequestMapping(value = "updateZJSendStatus",method = RequestMethod.POST)
	@ResponseBody
	public String updateZJSendStatus(HttpServletRequest request,Integer id,Integer if_send) throws Exception {
		String data = countService.updateZJSendStatus(id,if_send);
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
