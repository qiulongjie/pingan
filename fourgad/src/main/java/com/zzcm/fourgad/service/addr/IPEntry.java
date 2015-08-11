package com.zzcm.fourgad.service.addr;
public class IPEntry {
	public String beginIp;
	public String endIp;
	public String country;
	public String area;

	public IPEntry() {
		beginIp = endIp = country = area = "";
	}

	@Override
	public String toString() {
		return this.area + "  " + this.country + "  IP:" + this.beginIp + "-"
				+ this.endIp;
	}
}
