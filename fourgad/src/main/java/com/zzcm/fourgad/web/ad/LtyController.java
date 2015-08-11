package com.zzcm.fourgad.web.ad;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.service.ad.DdhService;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.WebUtil;

@Controller
@RequestMapping(value = "/lty")
public class LtyController {
	@Autowired
	private AdService adService;
	@Autowired
	private DdhService ddhService;
	
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
	
	//  获取九宫格抽奖页面初始化信息
	@RequestMapping(value="getLtyInitInfoNew",method = RequestMethod.POST)
	@ResponseBody
	public String getLtyInitInfoNew(String a,String ltyType) {
		if( a != null ){
			if(ltyType == null || ltyType.trim().equals("")){
				ltyType = "1";
			}
			String info = adService.getLtyInitInfoNew(a,ltyType);
			return info;
		}
		return null;
	}
	
	/**
	 * 获取抽奖结果 
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getLotteryNew",method = RequestMethod.POST)
	@ResponseBody
	public String getLotteryNew(HttpServletRequest request) {
		String channel = request.getParameter("a");
		String z = request.getParameter("z");
		String p = request.getParameter("p");
		String c = request.getParameter("c");
		String ip = WebUtil.getIpAddr(request);
		String time = DateUtil.getDateTime();
		String data = adService.getLotteryNew(channel,z,p,c,ip,time);
		return data;
	}
	
	/**
	 * 获取掌阅的中奖  1000月饼 就这个 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getLtyPrizeZy",method = RequestMethod.POST)
	@ResponseBody
	public String getLtyPrizeZy(HttpServletRequest request) {
		String channel = request.getParameter("a");
		String ltyPrize = request.getParameter("ltyPrize");
		String ip = WebUtil.getIpAddr(request);
		String time = DateUtil.getDateTime();
		String data = adService.getLtyPrizeZy(channel,ltyPrize,ip,time);
		return data;
	}
	
	@RequestMapping(value="downloadZyApk",method = RequestMethod.GET)
	@ResponseBody
	public String downloadZyApk(HttpServletRequest request,HttpServletResponse response) throws Exception {
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("apk"+File.separator+"iReader_4200_gxb_free_zhangzhong36_109622_201507101805.apk");

		// 下载
		if (in != null) {
			// response.setCharacterEncoding("UTF-8");
			// 写数据到客户端 "application/octet-stream" application/vnd.ms-excel
			response.setContentType("application/octet-stream");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			// 设置超时时间
			response.setDateHeader("Expires", (System.currentTimeMillis() + 5000));
			response.addHeader("Content-Disposition",
					"attachment;filename=\"iReader_4200_gxb_free_zhangzhong36_109622_201507101805.apk\"");

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
	
	/**
	 * 获取中保险后的跳转地址-大都会注册页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getPingUrlDdh",method = RequestMethod.POST)
	@ResponseBody
	public String getPingUrlDdh(HttpServletRequest request) {
		String channel = request.getParameter("a");
		String ip = WebUtil.getIpAddr(request);
		String data = ddhService.getPingUrlDdh(channel,ip);
		return data;
	}
}
