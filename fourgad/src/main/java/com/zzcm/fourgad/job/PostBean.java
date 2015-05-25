package com.zzcm.fourgad.job;

public class PostBean {
	private String Name;
	private String Sex;
	private String Mobile;
	private String Birthday;
	private String Ip;
	private String PubCode;
	private String Remark;
	
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	public String getIp() {
		return Ip;
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	public String getPubCode() {
		return PubCode;
	}
	public void setPubCode(String pubCode) {
		PubCode = pubCode;
	}
	
}
