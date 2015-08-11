package com.zzcm.fourgad.test;

import cn.com.metlife.icare.webservice.YSW2ICareSaveServiceLocator;
import cn.com.metlife.icare.webservice.YSW2ICareSave_PortType;

import com.zzcm.fourgad.entity.HolderIdentify;
import com.zzcm.fourgad.entity.Record;
import com.zzcm.fourgad.util.RecordXmlUtil;

public class YSWtest {

	public static void send(String data) throws Exception{
		System.out.println(data);
		YSW2ICareSaveServiceLocator loc = new YSW2ICareSaveServiceLocator();
		//loc.setYSW2ICareSaveEndpointAddress("http://icare-uat.metlife.com.cn/services/YSW2ICareSave");
		loc.setYSW2ICareSaveEndpointAddress("http://icare.metlife.com.cn/services/YSW2ICareSave");
		
		YSW2ICareSave_PortType service = loc.getYSW2ICareSave();
		String res = service.doRequest(data);
		System.out.println("Response: ");
		System.out.println(res);
		HolderIdentify holder = RecordXmlUtil.parserXmlResult(res);
		System.out.println(holder);
	}
	
	public static String createData(){
		Record record = new Record("ZhangYue");
	    record.Name = "鑫鑫";
	    record.Sex = "Female";
	    record.Birthday = "1982-08-08";
	    record.Mobile = "13117225497";
	    record.ContactStateName = "襄阳市";
	    record.ContactCityName = "襄阳市";
	    record.ContactAddress = "湖北省襄阳市";
	    record.PresentCode = "PC0000000123";
		return RecordXmlUtil.getXmlData(record);
	}
	
	public static void main(String[] args) throws Exception {
		send(createData());
	}
}
