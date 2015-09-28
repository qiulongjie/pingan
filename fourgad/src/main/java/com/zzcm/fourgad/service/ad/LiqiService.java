package com.zzcm.fourgad.service.ad;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.WebUtil;

@Component
public class LiqiService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** 活动包含省份  北京市*/
	private static final String[] INCLUD_PROVINCE = {"北京市"};
	/** 不包含的城市 */
	private static final String[] EXCLUD_CITY = {};
	/** 接口地址  */
	private static String baseUrl = "http://www.liqimedia.com:8080/Shunxinka/ZhangzhongDataSync.aspx";
	
	/**
	 * 检查是否给立其 
	 * @author qiulongjie
	 * @param ipaddr
	 * @param province
	 * @param city
	 * @return
	 */
	public boolean check(String ipaddr, String province, String city) {
		for(String c : EXCLUD_CITY){
			if(c.contains(city)){
				return false;
			}
		}
		
		for(String p : INCLUD_PROVINCE){
			if(province != null && (province.contains(p) || p.contains(province))){
				return true;
			}
		}
		return false;
	}
	/**
	 * 同步数据给立其
	 * @param recordCount 同步总数
	 * @throws Exception 
	 */
	public void synData(int recordCount) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select id ord_id,uname Name ,ddlsex Gender ,birthday Birthday,phone Phone,ipaddr Ip,'10008' channelid from ad_ord_log ");
		sb.append("where flag = 8 and ads = 5 ");
		//sb.append(" and pubcode not in (select channel from ad_channel_info a where a.disable_flag = 1) ");
		sb.append("limit ?");
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString(),new Object[]{recordCount});
		logger.info("同步总数："+list.size());
		StringBuffer buffer = new StringBuffer();
		Long id = null;
		String result = null;
		for (Map<String, Object> map : list) {
			id = Long.valueOf(map.get("ord_id").toString());
			buffer.setLength(0);
			try {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					buffer.append(entry.getKey()).append("=");
					//中文用utf-8编码urlencode
					if(entry.getKey().equals("Name") || entry.getKey().equals("Gender")){
						buffer.append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
					}else{
						buffer.append(entry.getValue());
					}
					buffer.append("&");// 请求的参数之间使用&分割。
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			buffer.deleteCharAt(buffer.length() - 1);
			//发送请求
			result = WebUtil.sendInfoForGET(baseUrl+"?"+buffer.toString());
			if(result != null && result.equals("ok")){
				updateRecord(id,7,"ok");
			}else{
				updateRecord(id,0,null);
			}
		}
		
	}
	
	/**
	 * 更新状态
	 * @param holder 
	 * @param id
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public void updateRecord(Long id,Integer flag,String msg) {
		jdbcTemplate.update("update ad_ord_log set flag=?,vstr1=?,updtime=? where id=?", new Object[]{flag,msg,DateUtil.getDateTime(),id});
	}

}
