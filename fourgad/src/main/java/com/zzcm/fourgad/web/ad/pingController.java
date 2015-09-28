package com.zzcm.fourgad.web.ad;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzcm.fourgad.entity.User;
import com.zzcm.fourgad.repository.UserDao;
import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.service.ad.DdhService;
import com.zzcm.fourgad.service.ad.IPService;
import com.zzcm.fourgad.service.ad.LiqiService;
import com.zzcm.fourgad.service.ad.NewMediaService;
import com.zzcm.fourgad.service.ad.PinganService;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.ValidUtil;
import com.zzcm.fourgad.util.WebUtil;

@Controller
@RequestMapping(value = "/ping")
public class pingController {
	private static Logger logger = LoggerFactory.getLogger(pingController.class);
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
	@Autowired
	private LiqiService liqiService;
	@Autowired
	private NewMediaService newMediaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String input(Model model,
			HttpServletRequest request,@RequestHeader("user-agent") String ua) {
		String channel = request.getParameter("a");
		if(channel==null){
			channel="1000";		
		}else{
			request.setAttribute("a", channel);
		}
		if( channel.length() > 20 ){
			logger.warn("渠道长度超过20字符 对此渠道进行截取channel="+channel+",ua="+ua+",url="+request.getRequestURL().toString()+"?"+request.getQueryString());
			channel = channel.substring(0,20);
		}	
		channel = channel.replace("a", "A");
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
		path = path.toLowerCase();
		String pubcode = request.getParameter("a");
		request.setAttribute("a", pubcode);
		return "pingan/"+path;
	}
	
	/**
	 * 普通订单提交  同步给荣时代
	 * @author qiulongjie
	 * @param request
	 * @return
	 */
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
		// 检验 start
		if(!ValidUtil.isValidName(uname)){
			return setAttributes(request, uname, birthday, ddlSex, phone, prov, pubcode, ok_p);
		}
		if(!ValidUtil.isValidSex(ddlSex)){
			return setAttributes(request, uname, birthday, ddlSex, phone, prov, pubcode, ok_p);
		}
		if(!ValidUtil.isBirthday(birthday)){
			return setAttributes(request, uname, birthday, ddlSex, phone, prov, pubcode, ok_p);
		}
		if(!ValidUtil.isPhoneNumber(phone)){
			return setAttributes(request, uname, birthday, ddlSex, phone, prov, pubcode, ok_p);
		}
		// 检验 END
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
			
//			AddrBean addrBean = iPService.getIPAddr2(ipaddr);
//			String province = addrBean.getProvinceCode();
//			String city = addrBean.getCity();

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
			// 判断是否要传给立其的数据
			/*boolean isLiqi = liqiService.check(ipaddr,province,city);
			if(isLiqi){
				// 如果是立其娱则把 flag设置为8
				ads = 5;
				adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,pcontent,8,ads,province,city);	
			}else{
				// 判断是否要传给360新媒体的数据
				boolean isNewMedia = newMediaService.checkOrd(pubcode,city);
				if(isNewMedia){
					// 如果是360新媒体则把 flag设置为8
					ads = 4;
					adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,pcontent,8,ads,province,city);	
				}else{
					adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,pcontent,0,ads,province,city);
				}
			}*/
			
			//订单提交是flag置为3，后台任务更新省份和城市后再恢复值
			adService.AddOrdLogs(uname, birthday, ddlSex, phone, ipaddr, prov, vtime,pubcode,pcontent,3,ads,null,null);
			
			if( ok_p != null && !ok_p.trim().equals("")){
				return "redirect:/ping/go/"+ok_p+"?a="+pubcode;
			}
		}else{
			// 判断验证码错误
			request.setAttribute("vry", "failure_vry");
			return setAttributes(request, uname, birthday, ddlSex, phone, prov, pubcode, ok_p);
		}
		
		return "redirect:/ping/ok?a="+pubcode;
	}

	private String setAttributes(HttpServletRequest request, String uname, String birthday, String ddlSex,
			String phone, String prov, String pubcode, String ok_p) {
		String failure_path = request.getParameter("failure_path");
		request.setAttribute("a", pubcode);
		request.setAttribute("uname", uname);
		request.setAttribute("birthday", birthday);
		request.setAttribute("ddlSex", ddlSex);
		request.setAttribute("phone", phone);
		request.setAttribute("code", prov);
		request.setAttribute("ok_p", ok_p);
		
		if(failure_path != null){
			return "pingan/"+failure_path;
		}
		return "pingan/ping";
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
		String channel = request.getParameter("a");
		String prov = request.getParameter("code") == null ? "" : request.getParameter("code");
		String ok_p = request.getParameter("ok_p");
		String failure_path = request.getParameter("failure_path");
		String failure_path2 = request.getParameter("failure_path2");
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
			String vtime = DateUtil.getDateTime();
			if( Integer.valueOf(String.valueOf(result[0])) == 0 ){
				ddhService.addRecordDdh(channel,uname, birthday, ddlSex, phone, ipaddr, vtime,isCheck,result);	
			}else{
				/*try {
					adService.AddOrdLogs(uname, birthday, ddlSex=="Male"?"男":"女", phone, ipaddr, prov, vtime,channel,"",3,1,null,null);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("大都会提交,数据校验失败，转为ad_ord_log。。异常："+e.getMessage());
				}*/
				if(failure_path2 != null && !failure_path2.trim().equals("")){
					return "redirect:/ping/go/"+failure_path2+"?result="+String.valueOf(result[0]);
				}
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
			if(failure_path != null && !failure_path.trim().equals("")){
				return "pingan/"+failure_path;
			}
			return "pingan/pingDdh";
		}
		if( ok_p != null && !ok_p.trim().equals("")){
			return "redirect:/ping/go/"+ok_p+"?a="+channel;
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
		if(!ValidUtil.isValidName(uname)){
			logger.warn("推送订单AddOrdLogsPush--姓名不合法,uname="+uname+",channel="+channel+",ip="+ipaddr+",vtime="+vtime);
			return "0";
		}
		if(!ValidUtil.isValidSex(ddlSex)){
			logger.warn("推送订单AddOrdLogsPush--性别不合法,ddlSex="+ddlSex+",channel="+channel+",ip="+ipaddr+",vtime="+vtime);
			return "0";
		}
		if(!ValidUtil.isBirthday(birthday)){
			logger.warn("推送订单AddOrdLogsPush--生日不合法,birthday="+birthday+",channel="+channel+",ip="+ipaddr+",vtime="+vtime);
			return "0";
		}
		if(!ValidUtil.isPhoneNumber(phone)){
			logger.warn("推送订单AddOrdLogsPush--手机号码不合法,phone="+phone+",channel="+channel+",ip="+ipaddr+",vtime="+vtime);
			return "0";
		}
		boolean pass = adService.isPassForPushOrd(channel,ipaddr);
		if(!pass){
			logger.warn("==推送订单被拦截 AddOrdLogsPush -- channel="+channel+",ip="+ipaddr+",vtime="+vtime);
		}
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
		if(!ValidUtil.isValidName(uname)){
			logger.warn("推送订单AddOrdLogsPush--姓名不合法,uname="+uname+",channel="+channel+",ip="+ipaddr+",vtime="+vtime);
			return "0";
		}
		if(!ValidUtil.isValidSex(ddlSex)){
			logger.warn("推送订单AddOrdLogsPush--性别不合法,ddlSex="+ddlSex+",channel="+channel+",ip="+ipaddr+",vtime="+vtime);
			return "0";
		}
		if(!ValidUtil.isBirthday(birthday)){
			logger.warn("推送订单AddOrdLogsPush--生日不合法,birthday="+birthday+",channel="+channel+",ip="+ipaddr+",vtime="+vtime);
			return "0";
		}
		if(!ValidUtil.isPhoneNumber(phone)){
			logger.warn("推送订单AddOrdLogsPush--手机号码不合法,phone="+phone+",channel="+channel+",ip="+ipaddr+",vtime="+vtime);
			return "0";
		}
		boolean pass = adService.isPassForPushOrd(channel,ipaddr);
		if(!pass){
			logger.warn("==推送订单被拦截  -- channel="+channel+",ip="+ipaddr+",vtime="+vtime);
		}
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
	
	/** 刷新360新媒体数据接口配置信息 */
	@RequestMapping(value="refreshNewMediaConfig")
	@ResponseBody
	public String refreshNewMediaConfig(HttpServletRequest request) {
		newMediaService.refreshNewMediaConfig();
		return "ok";
	}
	
	/**
	 * 提供互娱调用的接口 更新flag状态
	 * @author qiulongjie
	 * @param result
	 * @return
	 */
	@RequestMapping(value="updOrdStatus",method = RequestMethod.POST)
	@ResponseBody
	public String updOrdStatus(Long id ,
				            @RequestParam(value = "isSuccess",defaultValue="false") Boolean isSuccess,
				            String msg,
				            String resultObject) {
		logger.info("--互娱同步平安数据之后回调接口updOrdStatus--id="+id+",isSuccess="+isSuccess+",msg="+msg+",resultObject="+resultObject+",time="+DateUtil.getDateTime());
		/*if( null != isSuccess && isSuccess.equals("true")){
			adService.updOrdLogs(id, 9, msg,resultObject);
			return "true";
		}
		adService.updOrdLogs(id, 0, "");*/
		return "false";
	}
	/**
	 * 提供互娱调用的接口 更新flag状态  Test
	 * @author qiulongjie
	 * @param result
	 * @return
	 */
	@RequestMapping(value="updOrdStatusTest",method = RequestMethod.POST)
	@ResponseBody
	public String updOrdStatusTest(Long id ,
			                       @RequestParam(value = "isSuccess",defaultValue="false") Boolean isSuccess,
			                       String msg,
			                       String resultObject) {
		logger.info("--互娱同步平安数据之后回调接口updOrdStatusTest--id="+id+",isSuccess="+isSuccess+",msg="+msg+",resultObject="+resultObject+",time="+DateUtil.getDateTime());
		if( isSuccess){
			adService.updOrdLogsPush(id, 9, msg,resultObject);
			return "true";
		}
		adService.updOrdLogsPush(id, 0, "");
		return "false";
	}
}
