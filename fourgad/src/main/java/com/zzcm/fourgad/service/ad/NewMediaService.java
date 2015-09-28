package com.zzcm.fourgad.service.ad;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzcm.fourgad.job.JsonUtil;
import com.zzcm.fourgad.job.NewMediaRetBean;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.MD5Util;
import com.zzcm.fourgad.util.PropertiesUtil;
import com.zzcm.fourgad.util.WebUtil;

/**
 * 360新媒体 服务类
 * @author wancan
 *
 */
@Component
public class NewMediaService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** 360新媒体综合意外投保推广城市列表 */
	private static String CITIES = null;
	/** 推给360新媒体的渠道列表 */
	private static String CHANNELS = null;
	
	private static String CHANNELS_ID = null;
	/** 新媒体访问接口 */
	private static String NEWMEDIA_URL = null;
	
	private static String NEWMEDIA_KEY = null;
	
	private static String ADVPOSITION_ID = null;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 刷新综合意外险投保接口同步数据接口配置信息
	 * @author qiulongjie
	 */
	public void refreshNewMediaConfig(){
		logger.warn("刷新综合意外投保接口配置信息");
		Properties prop = PropertiesUtil.getProperty("interfaceParams.propertis");
		if(prop != null) {
			CHANNELS = prop.getProperty("CHANNELS",null);
			CHANNELS_ID = prop.getProperty("CHANNELS_ID","3919");
			CITIES = prop.get("CITIES").toString();
			NEWMEDIA_URL = prop.get("NEWMEDIA_URL").toString();
			NEWMEDIA_KEY = prop.get("NEWMEDIA_KEY").toString();
			ADVPOSITION_ID = prop.get("ADVPOSITION_ID").toString();
			logger.info("刷新综合意外投保接口配置信息--成功 ");
		}else{
			logger.warn("刷新综合意外投保接口配置信息--失败--无配置信息");
		}
	}
	
	/**
	 * 同步数据
	 * @param url
	 */
	public void sendData(int count){
		try {
			if(CHANNELS_ID == null){
				refreshNewMediaConfig();
			}
			StringBuffer sb = new StringBuffer();
			sb.append("select id, uname name, birthday, phone, case when ddlSex='男' then 1 else 0 end sex from ad_ord_log ");
			sb.append("where flag = 8 and ads = 4 ");
			sb.append("limit ?");
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString(),new Object[]{count});
			logger.info("360新媒体同步总数："+list.size());
			Long id = null;
			StringBuffer buffer = new StringBuffer();
			for(Map<String,Object> l:list){
				id = Long.valueOf(l.get("id").toString());
				l.put("p", ADVPOSITION_ID);
				l.put("c", CHANNELS_ID);
				l.put("bxtype", getSafeType());
				
				//sign = MD5(被保人生日+被保人手机+ key +渠道ID+广告位ID);
				String sign = l.get("birthday").toString() + l.get("phone").toString() 
						+ NEWMEDIA_KEY + CHANNELS_ID + ADVPOSITION_ID;
				l.put("v", MD5Util.sign(sign,"utf-8"));
				
				buffer.setLength(0);
				for (Map.Entry<String, Object> entry : l.entrySet()) {
					buffer.append(entry.getKey()).append("=");
					buffer.append(entry.getValue());
					buffer.append("&");// 请求的参数之间使用&分割。
				}
				buffer.deleteCharAt(buffer.length() - 1);
				logger.info(buffer.toString());
				String str = WebUtil.sendInfoForGET(NEWMEDIA_URL+"?"+buffer.toString());//get方式发送数据
				logger.info(str);
				if( null != str){
					NewMediaRetBean ret = JsonUtil.fromJson(str, NewMediaRetBean.class);
					if(ret != null){
						String isSuccess = ret.getSuccess();
						if("true".equals(isSuccess.trim())){
							updateRecord(id,7,ret.getErrMsg(),ret.getProdId());
						}else{
							updateRecord(id,0,ret.getErrMsg(),ret.getProdId());
						}
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新状态
	 * @param holder 
	 * @param id
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public void updateRecord(Long id,Integer flag,String msg,String prov) {
		jdbcTemplate.update("update ad_ord_log set flag=?,vstr1=?,prov=?,updtime=? where id=?", new Object[]{flag,msg,prov,DateUtil.getDateTime(),id});
	}
	
	/**
	 * 判断保险类型
	 * @return
	 */
	private int getSafeType(){
		Random random = new Random();
		int i = random.nextInt(20);
		if(i>1){
			return 1;
		}
		return 2;
	}
	
	/**
	 * 判断IP对应城市是否在360新媒体意外险地市列表范围内
	 * @author wancan
	 * @return 包含返回true
	 */
	public boolean checkOrd(String channel , String city) {
		if(CITIES == null){
			refreshNewMediaConfig();
		}
		if( CHANNELS!=null && !CHANNELS.contains(channel.trim())){
			return false;
		}
		if(city != null && !city.trim().equals("") && CITIES.contains(city.trim().replace("市", ""))){
			return true;
		}
		return false;
	}

}
