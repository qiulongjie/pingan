package com.zzcm.fourgad.web.ad;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzcm.fourgad.entity.AddrBean;
import com.zzcm.fourgad.entity.User;
import com.zzcm.fourgad.repository.UserDao;
import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.service.ad.DdhService;
import com.zzcm.fourgad.service.ad.IPService;
import com.zzcm.fourgad.service.ad.PinganService;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.WebUtil;

@Controller
@RequestMapping(value = "/ping")
public class pingController {
	@Autowired
	private AdService adService;
	@Autowired
	private DdhService ddhService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PinganService pinganService;
	@Autowired
    private IPService iPService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String input(Model model,
			HttpServletRequest request,@RequestHeader("user-agent") String ua) {
		String channel = request.getParameter("a");
		if(channel==null){
			channel="1000";		
		}else{
			request.setAttribute("a", channel);
		}
			
		
		if(ua!=null&&ua.length()>40){
			ua = ua.substring(0,40);
		}
		String ipaddr = WebUtil.getIpAddr(request);
		
		String prov = "";
		prov = request.getParameter("code");
		if(prov==null){
			prov="";		
		}else{
			request.setAttribute("code", prov);
		}
		String vtime = DateUtil.getDateTime();
		
		adService.AddReqLogs(channel, ipaddr, prov, vtime,ua,"1");

		return "pingan/ping";
	}
	
	@RequestMapping(value="ok",method = RequestMethod.GET)
	public String ok(HttpServletRequest request) {
		String pubcode = request.getParameter("a");
		request.setAttribute("a", pubcode);
		return "pingan/ok3";
	}
	
	@RequestMapping(value = "go/{path}",method = RequestMethod.GET)
	public String input(@PathVariable("path") String path,HttpServletRequest request) {
		String pubcode = request.getParameter("a");
		request.setAttribute("a", pubcode);
		return "pingan/"+path;
	}
	
	@RequestMapping(value="submitGet")
	public String list(HttpServletRequest request) {
		String uname = request.getParameter("uname");
		String birthday =request.getParameter("birthday");
		String ddlSex = request.getParameter("ddlSex") == null ? "男" : request.getParameter("ddlSex");
		String phone = request.getParameter("phone");
		String ipaddr = WebUtil.getIpAddr(request);
		String prov = request.getParameter("code") == null ? "" : request.getParameter("code");
		String pubcode = request.getParameter("a");
		String ok_p = request.getParameter("ok_p");
		
		String verifyCode = (String) request.getSession().getAttribute("verifyCode");
		String realCode = request.getParameter("vryCode");
		// 判断验证码是否正确
		if (verifyCode != null && realCode != null && realCode.equalsIgnoreCase(verifyCode)) {
			String vtime = DateUtil.getDateTime();
			
			String whois = request.getParameter("whois");
			String country = request.getParameter("country");
			String fee = request.getParameter("fee");
			String isneed = request.getParameter("isneed");
			String pcontent = whois+","+country+","+fee+","+isneed;
			
			AddrBean addrBean = iPService.getIPAddr2(ipaddr);
			String province = addrBean.getProvinceCode();
			String city = addrBean.getCity();

			int ads = 1;
			// 判断是否把这个数据同步给深圳互娱
			/*boolean isPingan = pinganService.checkIP(pubcode,city,birthday);
			if(isPingan){
				// 如果是深圳互娱则把 flag设置为8
				ads = 2;
				adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,pcontent,8,ads,province,city);	
			}else{
				adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,pcontent,0,ads,province,city);	
			}*/
			adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,pcontent,0,ads,province,city);
			if( ok_p != null && !ok_p.trim().equals("")){
				return "redirect:/ping/go/"+ok_p+"?a="+pubcode;
			}
		}else{
			// 判断验证码错误
			String failure_path = request.getParameter("failure_path");
			request.setAttribute("a", pubcode);
			request.setAttribute("uname", uname);
			request.setAttribute("birthday", birthday);
			request.setAttribute("ddlSex", ddlSex);
			request.setAttribute("phone", phone);
			request.setAttribute("code", prov);
			request.setAttribute("ok_p", ok_p);
			
			request.setAttribute("vry", "failure_vry");
			if(failure_path != null){
				return "pingan/"+failure_path;
			}
			return "pingan/ping";
		}
		
		return "redirect:/ping/ok?a="+pubcode;
	}
	
	/**
	 * 大都会提交数据 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="ddhSumbit")
	public String ddhSumbit(HttpServletRequest request) {
		String uname = request.getParameter("uname");
		String birthday =request.getParameter("birthday");
		String ddlSex = request.getParameter("ddlSex") == null ? "Male" : request.getParameter("ddlSex");
		String phone = request.getParameter("phone");
		
		//String prov = request.getParameter("code") == null ? "" : request.getParameter("code");
		String channel = request.getParameter("a");
		
		String[] checkArr= request.getParameterValues("checkBaoxian");
		String isCheck = null;
		if(checkArr == null || checkArr.length == 0){
			isCheck = "0";
		}else{
			isCheck = checkArr[0];
		}
		String verifyCode = (String) request.getSession().getAttribute("verifyCode");
		String realCode = request.getParameter("vryCode");
		// 判断验证码是否正确
		if (verifyCode != null && realCode != null && realCode.equalsIgnoreCase(verifyCode)) {
			// 检查参数 
			String ipaddr = WebUtil.getIpAddr(request);
			Object[] result = ddhService.checkDdhSumbit(uname,birthday,ddlSex,phone,ipaddr);
			if( Integer.valueOf(String.valueOf(result[0])) == 0 ){
				String vtime = DateUtil.getDateTime();
				ddhService.addRecordDdh(channel,uname, birthday, ddlSex, phone, ipaddr, vtime,isCheck,result);	
			}else{
				return "redirect:/ping/go/pingDdh_fail?result="+String.valueOf(result[0]);
			}
		}else{
			// 判断验证码错误
			request.setAttribute("a", channel);
			request.setAttribute("uname", uname);
			request.setAttribute("birthday", birthday);
			request.setAttribute("ddlSex", ddlSex);
			request.setAttribute("phone", phone);
			request.setAttribute("isCheck", isCheck);
			
			request.setAttribute("vry", "failure_vry");
			return "pingan/pingDdh";
		}
		
		return "redirect:/ping/go/pingDdh_ok";
	}
	
	/**
	 * 推送订单数据 AddOrdLogsPush 测试时用
	 * @param request
	 * @return
	 */
	@RequestMapping(value="pushOrd",method = RequestMethod.POST)
	@ResponseBody
	public String pushOrd(HttpServletRequest request) {
		String channel = request.getParameter("channel");
		String uname = request.getParameter("uname");
		String birthday =request.getParameter("birthday");
		String ddlSex = request.getParameter("ddlSex") == null ? "男" : request.getParameter("ddlSex");
		String phone = request.getParameter("phone");
		String ipaddr = WebUtil.getIpAddr(request);
		String vtime = DateUtil.getDateTime();
		boolean pass = adService.isPassForPushOrd(channel,ipaddr);
		System.out.println("**推送订单数据**ip="+ipaddr+"***channel="+channel+"***pass="+pass);
		if(pass){
			adService.AddOrdLogsPush(uname, birthday, ddlSex, phone, ipaddr, "push", vtime,channel,"");
			return "1";
		}
		return "0";
	}
	
	/**
	 * 推送订单数据 AddOrdLogs
	 * @param request
	 * @return
	 */
	@RequestMapping(value="pushOrdLog",method = RequestMethod.POST)
	@ResponseBody
	public String pushOrdLog(HttpServletRequest request) {
		String channel = request.getParameter("channel");
		String uname = request.getParameter("uname");
		String birthday =request.getParameter("birthday");
		String ddlSex = request.getParameter("ddlSex") == null ? "男" : request.getParameter("ddlSex");
		String phone = request.getParameter("phone");
		String ipaddr = WebUtil.getIpAddr(request);
		String vtime = DateUtil.getDateTime();
		boolean pass = adService.isPassForPushOrd(channel,ipaddr);
		System.out.println("**推送订单数据**ip="+ipaddr+"***channel="+channel+"***pass="+pass);
		if(pass){
			adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, "push", vtime,channel,"");
			return "1";
		}
		return "0";
	}
	
	/**
	 * 添加在ok页面中点击红包或者下载apk包的记录
	 * @author qiulongjie
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addOKClickLog",method = RequestMethod.POST)
	@ResponseBody
	public String addOKClickLog(HttpServletRequest request) {
		String channel = request.getParameter("a");
		String clickType = request.getParameter("clickType");
		String ipaddr = WebUtil.getIpAddr(request);
		String vtime = DateUtil.getDateTime();
		adService.addOKClickLog(channel,clickType,ipaddr,vtime);
		return "1";
	}
	
	/**
	 * 获取用户 以检查是否数据库断开连接
	 * @author qiulongjie
	 * @param request
	 * @return
	 */
	@RequestMapping(value="checkStatus",method = RequestMethod.GET)
	public String checkStatus(HttpServletRequest request) {
		String loginName = request.getParameter("loginName");
		if( loginName == null ){
			loginName = "admin";
		}
		User user = userDao.findByLoginName(loginName);
		request.setAttribute("u", user);
		return "check/checkStatus";
	}
	
	/**
	 * 刷新平安同步数据接口配置信息
	 * @author qiulongjie
	 * @param request
	 * @return
	 */
	@RequestMapping(value="refreshPinganConfig")
	@ResponseBody
	public String refreshPinganConfig(HttpServletRequest request) {
		pinganService.refreshPinganConfig();
		return "ok";
	}
}
