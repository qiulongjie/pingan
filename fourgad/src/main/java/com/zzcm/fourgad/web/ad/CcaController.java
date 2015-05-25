package com.zzcm.fourgad.web.ad;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.WebUtil;

@Controller
@RequestMapping(value = "/cca")
public class CcaController {
	@Autowired
	private AdService adService;
	
	@RequestMapping(value="ok",method = RequestMethod.GET)
	public String ok(HttpServletRequest request,String x,String d) {
		request.setAttribute("x",x);
		request.setAttribute("d",d);
		return "pingan/cca_jieguo";
	}
	
	@RequestMapping(value="submitGet")
	public String list(HttpServletRequest request) {
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String carT = request.getParameter("carT");
		String carNum = request.getParameter("carNum");
		carNum = carNum == null ? "" : carNum ;
		String newCar = request.getParameter("newCar");
		newCar = newCar == null ? "0" : newCar ;
		String carprice = request.getParameter("carprice");
		String uname = request.getParameter("uname");
		String phone = request.getParameter("phone");
		String birthday = request.getParameter("birthday");
		String ywx =request.getParameter("ywx");
		ywx = ywx == null ? "0" : ywx ;
		String ipaddr = WebUtil.getIpAddr(request);
		String channel = request.getParameter("a");
		
		String vtime = DateUtil.getDateTime();
		
		adService.addCcaInfo(channel,uname,phone,birthday,province,city,carT+carNum,newCar,carprice,ywx,ipaddr,vtime);
		
		double d = Double.parseDouble(carprice);
		d = d * 10000 * 0.021215;
		DecimalFormat df=new DecimalFormat("0.00");
		
		return "redirect:/cca/ok?x="+ywx+"&d="+df.format(d);
	}
}
