package com.zzcm.fourgad.util;

/**
 * 获取地址服务
 * @author Administrator
 *
 */
public interface IAddrService {

	
	/**
	 * 获取地址信息
	 * @param gpsInfoBean
	 * @param ip
	 * @return JSON串
	 */
	public String getAddr(String gpsInfoBeanJson,String ip);
}
