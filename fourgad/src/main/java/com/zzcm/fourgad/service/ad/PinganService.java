package com.zzcm.fourgad.service.ad;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zzcm.fourgad.entity.AddrBean;
import com.zzcm.fourgad.util.MD5Util;
import com.zzcm.fourgad.util.ValidUtil;
import com.zzcm.fourgad.util.WebUtil;

@Component
@Transactional
public class PinganService {
	//private Logger logger = Logger.getLogger(PinganService.class);
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private IPService iPService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** 平安意外险推广城市列表 */
	private static String CITIES = null;
	/** 推给平安的渠道列表 */
	private static String CHANNELS = null;
	
	/** 页面ID：4 */
	private static String HKPAGEINFO_ID = null;
	/** 广告位ID：283 */
	private static String ADVPOSITION_ID = null;
	/** 签名Key */
	private static String PINGAN_KEY = null;
	/** 同步接口 */
	private static String PINGAN_URL = null;

	private static String CHAR_SET = "utf-8";
	
	//private static Type listType = new TypeToken<Map<String,String>>() { }.getType(); 
	
	/**
	 * 刷新互娱接口同步数据接口配置信息
	 * @author qiulongjie
	 */
	public void refreshPinganConfig(){
		logger.warn("刷新互娱接口配置信息");
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from ad_pinan_config where flag=1", new Object[]{});
		if( list != null && list.size() > 0 ){
			Map<String, Object> map = list.get(0);
			HKPAGEINFO_ID = (String) map.get("hkPageInfo_id");
			ADVPOSITION_ID = (String) map.get("advposition_id");
			PINGAN_KEY = (String) map.get("pingan_key");
			PINGAN_URL = (String) map.get("pingan_url");
			CHANNELS = (String) map.get("channels");
			CITIES = (String) map.get("cities");
			logger.warn("刷新互娱接口配置信息--成功 map="+map);
		}else{
			logger.warn("刷新互娱接口配置信息--失败--无配置信息");
		}
	}
	
	/**
	 * 同步数据给pingan
	 * @param recordCount 同步总数
	 * @throws Exception 
	 */
	public void synData(int recordCount) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select id ,uname name ,");
		sb.append("       date_format(str_to_date(birthday,'%Y%m%d'),'%Y-%m-%d') birth,");
		sb.append("		 phone ,");
		sb.append("		 case when ddlsex='男' then 1 else 0 end sex, ");
		sb.append("		 ipaddr,province,city ");
		sb.append("from ad_ord_log_push ");// 测试
		//sb.append("from ad_ord_log ");
		sb.append("where flag = 8 and ads = 2 ");
		sb.append(" and pubcode not in (select channel from ad_channel_info a where a.disable_flag = 1) ");
		sb.append("limit ?");
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString(),new Object[]{recordCount});
		Long id = 0L;
		if(HKPAGEINFO_ID == null){
			refreshPinganConfig();
		}
		for( Map<String, Object> map : list ){
			id = Long.valueOf(String.valueOf(map.get("id")));
			//map.remove("id");
			
			map.put("hkPageInfo.id", HKPAGEINFO_ID);
			map.put("advPosition.id", ADVPOSITION_ID);
			
			String city = (String) map.get("city");
			String province = (String) map.get("province");
			if(city == null || city.trim().equals("")){
				String ip = String.valueOf(map.get("ipaddr"));
				AddrBean bean = iPService.getIPAddr2(ip);
				city = bean.getCity();
				if(city == null || city.trim().equals("") || city.trim().equals("null")){
					// 测试
					jdbcTemplate.update("update ad_ord_log_push set flag=? where id=?", new Object[]{0,id});
					// 上线
					//jdbcTemplate.update("update ad_ord_log set flag=? where id=?", new Object[]{0,id});
					continue;
				}
				province = bean.getProvinceCode();
			}
			map.remove("ipaddr");
			
			map.put("country", "中国");
			map.put("province", province.replace("省", ""));
			map.put("city", city.replace("市", ""));
			
			// 是否车主
			map.put("insType", getInsType());
			
			// sign = MD5(广告位ID + 签名Key + 被保人手机号);广告位ID：283， 签名Key：HYTX_ZZ_@!h1va#
			String text = ADVPOSITION_ID + PINGAN_KEY + String.valueOf(map.get("phone"));
			String sign = MD5Util.sign(text, CHAR_SET);
			map.put("sign", sign);
			
			logger.info("pingan 同步 data="+map);
			//String isSuccess = WebUtil.sendData(PINGAN_URL, "POST", map, CHAR_SET,false);
			String isSuccess = WebUtil.postData(PINGAN_URL, map);
			logger.info("pingan 同步 结果 resultJosn="+isSuccess);
			
			//Map<String,String> resultMap = JsonUtil.fromJson(resultJosn, listType);
			//String isSuccess = resultMap.get("isSuccess");
			//int flag = 0;
			//String sql = "";
			//Object[] obj = new Object[]{flag,id};
			if( null != isSuccess && isSuccess.equals("true")){
				// 如果flag=9则说明互娱那边同步给平安成功并返回，所以不需要修改flag了
				// 测试
				jdbcTemplate.update("update ad_ord_log_push set flag=? where id=? and flag!=9", new Object[]{7,id});
				// 正式
				//jdbcTemplate.update("update ad_ord_log set flag=? where id=? and flag!=9", new Object[]{7,id});
			}
		}
	}
	
	/** 是否车主（0：否；1：是） */
	private int getInsType(){
		Random random = new Random();
		int i = random.nextInt(20);
		if(i>1){
			return 0;
		}
		return 1;
	}

	/**
	 * 判断IP对应城市是否在平安活动城市范围内
	 * @author qiulongjie
	 * @param ipaddr
	 * @return 包含返回true
	 */
	public boolean checkIP(String channel , String city,String birthday) {
		// 年龄范围是25-45周岁
		if(!ValidUtil.isValidBirthday(birthday, 25, 45)){
			return false;
		}
		if(CHANNELS == null){
			refreshPinganConfig();
		}
		if(!CHANNELS.contains(channel)){
			return false;
		}
		city = city.replace("市", "");
		if(CITIES.contains(city)){
			return true;
		}
		return false;
	}

}
