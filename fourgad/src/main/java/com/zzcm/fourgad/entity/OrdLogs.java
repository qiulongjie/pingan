package com.zzcm.fourgad.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdLogs extends IdEntity{
	private String uname;
	private String birthday ;
	private String ddlSex ;
	private String phone ;
	private String ipaddr;
	private String prov = "";
	private String vtime ="";
	private int flag;
	private String pubcode;
	private String vstr1;
	private String pcontent;
	
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getPubcode() {
		return pubcode;
	}
	public void setPubcode(String pubcode) {
		this.pubcode = pubcode;
	}
	public String getVstr1() {
		return vstr1;
	}
	public void setVstr1(String vstr1) {
		this.vstr1 = vstr1;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDdlSex() {
		return ddlSex;
	}
	public void setDdlSex(String ddlSex) {
		this.ddlSex = ddlSex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getVtime() {
		Date date=new Date();//取时间				
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		 
		return formatter.format(date);
	}
	public void setVtime(String vtime) {
		this.vtime = vtime;
	}
	
}
