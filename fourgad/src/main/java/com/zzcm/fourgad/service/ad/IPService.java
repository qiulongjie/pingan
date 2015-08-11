package com.zzcm.fourgad.service.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zzcm.fourgad.entity.AddrBean;
import com.zzcm.fourgad.service.addr.IPSeeker;

@Component
@Transactional
public class IPService {
	
	@Autowired
	private IPSeeker ipSeeker;
	
	public AddrBean getIPAddr2(String ip){
		String[] str=ipSeeker.getAddress2(ip);
		AddrBean IPAddr = new AddrBean();
		if(str[0].indexOf("局域网") != -1){
			IPAddr.setCountry("中国");
			IPAddr.setProvinceCode("广东省");
			IPAddr.setCity("深圳市");
		}else{
			IPAddr.setCountry(str[0]);
			IPAddr.setProvinceCode(str[1]);
			IPAddr.setCity(str[2]);
		}
		return IPAddr;
	}
	
}
