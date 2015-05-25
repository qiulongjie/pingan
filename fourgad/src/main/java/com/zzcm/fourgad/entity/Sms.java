package com.zzcm.fourgad.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
/*
 * sms varchar(70),
	mobile varchar(14) not null,
	vtime varchar(20),
	flag int(1),
 */

@Entity
@Table(name = "ad_sms_log")
public class Sms extends IdEntity{
	private String c;
	private String m;
	private String t;
	private int flag;
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
