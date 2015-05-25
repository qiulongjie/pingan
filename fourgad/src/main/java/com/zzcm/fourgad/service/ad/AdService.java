package com.zzcm.fourgad.service.ad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzcm.fourgad.util.DateUtil;

@Component
@Transactional
public class AdService {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void AddReqLogs(String channel,String ipaddr,String prov,String vtime,String ua ){
		String sql = "insert into ad_req_log (channel,ipaddr,prov,vtime,ua) values (?,?,?,?,?)";
		Object o [] = {channel,ipaddr,prov,vtime,ua};
		jdbcTemplate.update(sql,o);
	}
	
	public void AddOrdLogs(String uname,String birthday,String ddlSex,String phone,String ipaddr,String prov,String vtime){
		String sql = "insert into ad_ord_log (uname,birthday,ddlSex,phone,ipaddr,prov,vtime) values (?,?,?,?,?,?,?)";
		Object o [] = {uname,birthday,ddlSex,phone,ipaddr,prov,vtime};
		jdbcTemplate.update(sql,o);
	}
	
	public void AddOrdLogs(String uname,String birthday,String ddlSex,String phone,String ipaddr,String prov,String vtime,String pcontent){
		String sql = "insert into ad_ord_log (uname,birthday,ddlSex,phone,ipaddr,prov,vtime,pcontent) values (?,?,?,?,?,?,?,?)";
		Object o [] = {uname,birthday,ddlSex,phone,ipaddr,prov,vtime,pcontent};
		jdbcTemplate.update(sql,o);
	}
	
	public void AddOrdLogs(String uname,String birthday,String ddlSex,String phone,String ipaddr,String prov,String vtime,String pubcode,String pcontent){
		String sql = "insert into ad_ord_log (uname,birthday,ddlSex,phone,ipaddr,prov,vtime,pubcode,pcontent) values (?,?,?,?,?,?,?,?,?)";
		Object o [] = {uname,birthday,ddlSex,phone,ipaddr,prov,vtime,pubcode,pcontent};
		jdbcTemplate.update(sql,o);
	}
	
	public void updOrdLogs(Long id,int flag,String msg){
		String sql = "update ad_ord_log set flag = ?,vstr1 = ? where id = ?";
		Object o [] = {flag,msg,id};
		jdbcTemplate.update(sql,o);
	}
	
	public List<Map<String,Object>> getOrdsByFlag(int flag,int num){
		String sql = "select * from ad_ord_log where flag = ? limit 0,?";
		Object o [] = {flag,num};
		return jdbcTemplate.queryForList(sql,o);
	}

	/**
	 * 获取 爱思助手 抽奖计算结果
	 * @param channel
	 * @param z
	 * @return
	 */
	public String getLtyInfo2As(String channel, String z,String p ,String c) {
		Map<String,Object> resultMap = new HashMap<String, Object>();//结果信息
		// 没有渠道
		if(channel == null || channel.trim().equals("")){
			resultMap.put("error", "无渠道,不合法");
			return getJson(resultMap);
		}
		// 获取渠道的活动时间
		String currTime = DateUtil.getDateTime();
		String sql = "select * from ad_lty_info where channel = ? and start_time <= ? and end_time >= ? ";
		List<Map<String,Object>> condition = jdbcTemplate.queryForList(sql,new Object[]{channel,currTime,currTime});
		if( condition == null || condition.size() <= 0 ){
			resultMap.put("error", "此渠道还未设置奖项信息或者活动未开始或已结束");
			return getJson(resultMap);
		}
		// 获取开始与结束时间
		String startTime = String.valueOf(condition.get(0).get("start_time"));
		String endTime = String.valueOf(condition.get(0).get("end_time"));
		
		/*// 活动时间未到
		if( startTime.compareTo(currTime) > 0 ){
			resultMap.put("error", "sorry!动还未开始");
			return getJson(resultMap);
		}
		// 当前时间大于活动结束时间
		if( currTime.compareTo(endTime) > 0 ){
			resultMap.put("error", "sorry!活动时间已过");
			return getJson(resultMap);
		}*/
		// 判断当前是否符合条件
		// 查询该时间范围的有效订单数flag=1
		String eft_cnt_sql = " select pubcode a,sum(case when flag=1 then 1 else 0 end) eft_cnt "+
							" from ad_ord_log "+
							" where 1=1 "+
							" and vtime >= ? and vtime <= ? "+
							" and pubcode = ? ";
		List<Map<String,Object>> eftCnt = jdbcTemplate.queryForList(eft_cnt_sql,new Object[]{startTime,endTime,channel});
		//有效订单数
		int eftCntNum = 0;
		if(eftCnt != null && eftCnt.size() > 0 ){
			if( eftCnt.get(0).get("eft_cnt") != null ){
				eftCntNum = Integer.valueOf(String.valueOf(eftCnt.get(0).get("eft_cnt")));
			}
		}
		eftCntNum++;
		
		String sql2 = "select * from ad_lty_info where channel = ? and start_time <= ? and end_time >= ? and v_count = ? and allow_num > 0 ";
		List<Map<String,Object>> condition2 = jdbcTemplate.queryForList(sql2,new Object[]{channel,currTime,currTime,eftCntNum});
		if( condition2 != null && condition2.size() > 0 ){
			
			// 有中奖信息
			resultMap = condition2.get(0);
			String updateSql = " update ad_lty_info set allow_num = allow_num-1 where id = ? ";
			jdbcTemplate.update(updateSql, new Object[]{resultMap.get("id")});
			
			// 返回中奖信息
			return getJson(resultMap);
		}
		
		condition = jdbcTemplate.queryForList(sql,new Object[]{channel,currTime,currTime});
		
		resultMap = null;//中奖信息
		Map<String,Object> mrzjMap = null;//默认的中奖信息
		
		if( z != null && !z.trim().equals("") 
		    && p != null && p.trim().equals("fake") 
		    && c != null && c.trim().equals("jia")){
			
			for(Map<String,Object> map : condition){
				if( z.trim().equals(String.valueOf(map.get("lty_angle")).trim())){
					// 判断剩余名额
					if( Integer.valueOf(String.valueOf(map.get("allow_num"))) > 0 ){
						resultMap = map;
						String updateSql = " update ad_lty_info set allow_num = allow_num-1 where id = ? ";
						jdbcTemplate.update(updateSql, new Object[]{resultMap.get("id")});
						// 返回中奖信息
						return getJson(resultMap);
					}
				}
			}
		}
		
		if( resultMap == null ){
			for(Map<String,Object> map : condition){
				int v_count = Integer.valueOf(String.valueOf(map.get("v_count")));
				
				/*if( eftCntNum == v_count ){
					// 中奖了 oh wa
					// 判断剩余名额
					if( Integer.valueOf(String.valueOf(map.get("allow_num"))) > 0 ){
						resultMap = map;
						break;
					}
				}*/
				
				// v_count==0 是默认抽中的奖项
				if( v_count==0 ){
					mrzjMap = map;
					break;
				}
			}
		}
		// 如果有中奖信息 修改中奖还剩名额
//		if( resultMap != null ){
//			String updateSql = " update ad_lty_info set allow_num = allow_num-1 where id = ? ";
//			jdbcTemplate.update(updateSql, new Object[]{resultMap.get("id")});
//		}
		// 如果没有中奖
		if( resultMap == null && mrzjMap != null ){
			resultMap = mrzjMap;
		}
		
		// 返回中奖信息
		return getJson(resultMap);
	}
	
	private String getJson(Map<String,Object> data){
		// 封装json
		ObjectMapper mapper = new ObjectMapper();  
	    String json = null;
		try {
			json = mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}  
		return json;
	}

	/**
	 * 添加中奖信息
	 * @param uname
	 * @param phone
	 * @param address
	 * @param ip
	 * @param channel
	 * @param time
	 */
	public void addRewardInfo(String uname, String phone, String address, String ip, String channel, String time,String ua) {
		String sql = " insert into ad_zj_info(uname,phone,address,ip,channel,vtime,if_send,ua) values(?,?,?,?,?,?,?,?) ";
		jdbcTemplate.update(sql,new Object[]{uname, phone, address, ip, channel, time,0,ua});
	}

	/**
	 * 添加车车安车险计算提交信息
	 * @param channel
	 * @param uname
	 * @param phone
	 * @param province
	 * @param city
	 * @param string
	 * @param newCar
	 * @param carprice
	 * @param ywx
	 * @param ipaddr
	 * @param vtime
	 */
	public void addCcaInfo(String channel, String uname, String phone,String birthday, String province, String city, String carNum,
			String newCar, String carprice, String ywx, String ipaddr, String vtime) {
		String sql = " insert into ad_cca_log(        "+
					" channel,                       "+
					" uname,                         "+
					" phone,                         "+
					" birthday,                      "+
					" province,                      "+
					" city,                          "+
					" car_num,                       "+
					" if_new_car,                    "+
					" car_price,                     "+
					" if_ywx,                        "+
					" ip_addr,                       "+
					" vtime                          "+
					" ) values(?,?,?,?,?,?,?,?,?,?,?,?)";	
		jdbcTemplate.update(sql, new Object[]{channel , 
												uname   ,
												phone   ,
												birthday,
												province,
												city    ,
												carNum  ,
												Integer.parseInt(newCar)  ,
												carprice,
												Integer.parseInt(ywx)     ,
												ipaddr  ,
												vtime});
	}
}
