package com.zzcm.fourgad.web.count;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "getAntiCheatingData",method = RequestMethod.POST)
    @ResponseBody
    public String getAntiCheatingData(HttpServletRequest request) throws Exception {
        String pageSize = getParameter(request,"length","10");
        String pageNum = getParameter(request,"start","0");
        String begin_vtime = getParameter(request,"begin_vtime","");
        String end_vtime = getParameter(request,"end_vtime","");
        String channel = getParameter(request,"channel","");
        String draw = getParameter(request,"draw","1");
        String data = countService.queryAntiCheatingData(pageNum,pageSize,begin_vtime.trim(),end_vtime.trim(),channel,draw);
        return data;
    }
	
	/**
	 * 注册渠道的时候ajax请求检查渠道是否唯一
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "checkChannel")
    @ResponseBody
    public String checkChannel(@RequestParam("channel") String channel) throws Exception {
		List<Map<String,Object>> list = countService.queryChannelInfo(channel);
		if( list != null && list.size() > 0 ){
			return "false";
		}
		return "true";
    }
	
	/**
	 * 注册渠道
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addChannel",method = RequestMethod.POST)
	public String addChannel(HttpServletRequest request) throws Exception {
		String channel = getParameter(request,"channel","");
        String busyName = getParameter(request,"busy_name","");
        String customer = getParameter(request,"customer","");
        String startTime = getParameter(request,"start_time",null);
        String endTime = getParameter(request,"end_time",null);
        if(endTime != null && endTime.equals("")){
        	endTime = null;
        }
        if( endTime != null && startTime.compareTo(endTime) > 0){
        	request.setAttribute("channel", channel);
			request.setAttribute("busy_name", busyName);
			request.setAttribute("customer", customer);
			request.setAttribute("start_time", startTime);
			request.setAttribute("end_time", endTime);
			request.setAttribute("message", "渠道注册失败。生效时间不能大于终止时间！");
			return "manage/addChannel";
		}
        boolean isExist = countService.checkChannel(null,channel,startTime,endTime);
        if(isExist){
        	request.setAttribute("channel", channel);
			request.setAttribute("busy_name", busyName);
			request.setAttribute("customer", customer);
			request.setAttribute("start_time", startTime);
			request.setAttribute("end_time", endTime);
			request.setAttribute("message", "渠道注册失败。与已有渠道时间冲突！");
        	return "manage/addChannel";
        }
		countService.addChannel(channel.trim(),busyName.trim(),customer.trim(),startTime,endTime);
		request.setAttribute("message", "渠道添加成功!");
		return "manage/channelList";
	}
	
	/**
	 * 设置渠道状态
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateChannelSt")
	public String updateChannelSt(HttpServletRequest request) throws Exception {
		String id = getParameter(request,"i","");
		String flag = getParameter(request,"f","");
		countService.updateChannelSt(id.trim(),flag.trim());
		return "manage/channelList";
	}
	
	/**
	 * 获取渠道信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getChannelData",method = RequestMethod.POST)
	@ResponseBody
	public String getChannelData(HttpServletRequest request) throws Exception {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String channel = getParameter(request,"channel","");
		String draw = getParameter(request,"draw","1");
		String data = countService.getChannelData(pageNum,pageSize,channel,draw);
		return data;
	}
	
	/**
	 * 更新界面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateChannelForm")
	public String updateChannelForm(HttpServletRequest request) throws Exception {
		String id = getParameter(request,"i","");
		List<Map<String,Object>> list = countService.queryChannelInfo(Integer.valueOf(id));
		if( list != null && list.size() > 0){
			request.setAttribute("id", list.get(0).get("id"));
			request.setAttribute("channel", list.get(0).get("channel"));
			request.setAttribute("busy_name", list.get(0).get("busy_name"));
			request.setAttribute("customer", list.get(0).get("customer"));
			request.setAttribute("start_time", list.get(0).get("start_time"));
			request.setAttribute("end_time", list.get(0).get("end_time"));
		}
		return "manage/updateChannel";
	}
	
	/**
	 * 更新渠道
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateChannel",method = RequestMethod.POST)
	public String updateChannel(HttpServletRequest request) throws Exception {
		String id = getParameter(request,"id","");
		String channel = getParameter(request,"channel","");
        String busyName = getParameter(request,"busy_name","");
        String customer = getParameter(request,"customer","");
        String startTime = getParameter(request,"start_time",null);
        String endTime = getParameter(request,"end_time",null);
        if(endTime != null && endTime.equals("")){
        	endTime = null;
        }
        if( endTime != null && startTime.compareTo(endTime) > 0){
        	request.setAttribute("id", id);
        	request.setAttribute("channel", channel);
			request.setAttribute("busy_name", busyName);
			request.setAttribute("customer", customer);
			request.setAttribute("start_time", startTime);
			request.setAttribute("end_time", endTime);
			request.setAttribute("message", "渠道修改失败。生效时间不能大于终止时间！");
			return "manage/updateChannel";
		}
        boolean isExist = countService.checkChannel(id,channel,startTime,endTime);
        if(isExist){
        	request.setAttribute("id", id);
        	request.setAttribute("channel", channel);
			request.setAttribute("busy_name", busyName);
			request.setAttribute("customer", customer);
			request.setAttribute("start_time", startTime);
			request.setAttribute("end_time", endTime);
			request.setAttribute("message", "渠道修改失败。与已有渠道时间冲突！");
        	return "manage/updateChannel";
        }
		countService.updateChannel(id,channel,busyName,customer,startTime,endTime);
		request.setAttribute("message", "渠道更新成功!");
		return "manage/channelList";
	}
	
	/**
	 * 删除渠道
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteChannel")
	public String deleteChannel(HttpServletRequest request) throws Exception {
		String id = getParameter(request,"i","");
		countService.deleteChannel(id);
		return "manage/channelList";
	}
	
	/**
	 * 刷新渠道信息缓存
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "refreshChannel",method = RequestMethod.POST)
	@ResponseBody
	public String refreshChannel(HttpServletRequest request) throws Exception {
		countService.refreshChannel();
		return "";
	}
	
	/**
	 * 获取渠道结算数据信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getChannelFeeData",method = RequestMethod.POST)
	@ResponseBody
	public String getChannelFeeData(HttpServletRequest request) throws Exception {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String channel = getParameter(request,"channel","");
		String c_date = getParameter(request,"c_date","");
		String draw = getParameter(request,"draw","1");
		String data = countService.getChannelFeeData(pageNum,pageSize,channel,c_date,draw);
		return data;
	}
	
	/**
	 * 获取结算总计
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getChannelTotalFeeData",method = RequestMethod.POST)
	@ResponseBody
	public String getChannelTotalFeeData(HttpServletRequest request) throws Exception {
		String channel = getParameter(request,"channel","");
		String c_date = getParameter(request,"c_date","");
		String data = countService.getChannelTotalFeeData(channel,c_date);
		return data;
	}
	
	/**
	 * 获取大都会订单数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDdhData",method = RequestMethod.POST)
	@ResponseBody
	public String getDdhData(HttpServletRequest request) throws Exception {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String draw = getParameter(request,"draw","1");
		String channel = getParameter(request,"channel","");
		String c_date = getParameter(request,"c_date","");
		String data = countService.getDdhData(channel,c_date,pageSize,pageNum,draw);
		return data;
	}
	
	/**
	 * 获取订单详细信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getOrdDetailData",method = RequestMethod.POST)
	@ResponseBody
	public String getOrdDetailData(HttpServletRequest request) throws Exception {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String draw = getParameter(request,"draw","1");
		String channel = getParameter(request,"channel","");
		String begin_vtime = getParameter(request,"begin_vtime","");
		String end_vtime = getParameter(request,"end_vtime","");
		String data = countService.getOrdDetailData(channel,begin_vtime,end_vtime,pageSize,pageNum,draw);
		return data;
	}
	
	/**
	 * 统计ok点击
	 * @author qiulongjie
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getOKClickCountData",method = RequestMethod.POST)
	@ResponseBody
	public String getOKClickCountData(HttpServletRequest request) throws Exception {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String draw = getParameter(request,"draw","1");
		String channel = getParameter(request,"channel","");
		String begin_vtime = getParameter(request,"begin_vtime","");
		String end_vtime = getParameter(request,"end_vtime","");
		String data = countService.getOKClickCountData(channel,begin_vtime,end_vtime,pageSize,pageNum,draw);
		return data;
	}
	
	/**
	 * 下载订单数据 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="downloadOrdDetailData",method = RequestMethod.GET)
	@ResponseBody
	public String downloadOrdDetailData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String channel = getParameter(request,"chn","");
		String begin_vtime = getParameter(request,"vt","");
		String end_vtime = getParameter(request,"vt2","");
		String fileType = getParameter(request,"t","");
		// 生成文件
		String filePath = null;
		if( "csv".equals(fileType) ){
			filePath = countService.createCSVforOrd(channel,begin_vtime,end_vtime);
		}else{
			filePath = countService.createExcelforOrd(channel,begin_vtime,end_vtime);
		}
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);

		String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
		// 下载
		if (in != null) {
			response.setCharacterEncoding("UTF-8");
			// 写数据到客户端 "application/octet-stream" application/vnd.ms-excel
			response.setContentType("application/octet-stream");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			// 设置超时时间
			response.setDateHeader("Expires", (System.currentTimeMillis() + 5000));
			response.addHeader("Content-Disposition",
					"attachment;filename=\""+fileName+"\"");

			OutputStream out = response.getOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
				out.flush();
			}
			out.close();
			in.close();
		}
		
		//删除文件 
		file.delete();
		return null;
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
