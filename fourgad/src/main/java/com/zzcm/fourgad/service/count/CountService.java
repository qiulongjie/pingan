package com.zzcm.fourgad.service.count;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzcm.fourgad.entity.DataBean;
import com.zzcm.fourgad.util.StringUtil;


@Component
@Transactional
public class CountService {
	
	/**
	 * 渠道信息
	 */
	private static List<Map<String,Object>> channelInfos = new ArrayList<Map<String,Object>>();
	private static String channelInfoSql = "select id,channel,busy_name,customer,start_time,end_time,disable_flag,case when disable_flag = 1 then '已屏蔽' else '正常' end st from ad_channel_info";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 刷新渠道信息
	 */
	public void refreshChannel(){
		channelInfos.clear();
		channelInfos = jdbcTemplate.queryForList(channelInfoSql.toString());
	}

	/**
	 * 查询统计信息
	 * @param pageNum
	 * @param pageSize
	 * @param vtime
	 * @param channel
	 * @param draw
	 * @return
	 */
	public String queryCountData(String pageNum, String pageSize, String vtime, String channel, String draw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select pubcode a,left(vtime,10) vtime , count(1) ord_cnt,sum(case when flag=1 then 1 else 0 end) eft_cnt ");
		sb.append(" from ad_ord_log ");
		sb.append(" where 1=1 ");
		
		//拼装查询条件
		if( vtime != null && vtime.trim().length() > 0){
			sb.append(" and vtime >= '" + vtime.trim() + " 00:00:00' and vtime <= '" + vtime.trim() + " 23:59:59' ");
		}
		if( channel != null && channel.trim().length() > 0){
			sb.append(" and pubcode like '%" + channel.trim() + "%' ");
		}
		
		sb.append(" group by pubcode ,left(vtime,10) ");
		sb.append(" order by sum(case when flag=1 then 1 else 0 end) desc ");
		sb.append(" limit ?,?  ");
		
		// 求总数sql
		StringBuffer sbCount = new StringBuffer();
		sbCount.append(" select count(1) cnt ");
		sbCount.append(" from ( ");
		sbCount.append(" select pubcode from ad_ord_log where 1=1 ");
		
		//拼装查询条件
		if( vtime != null && vtime.trim().length() > 0){
			sbCount.append(" and vtime >= '" + vtime.trim() + " 00:00:00' and vtime <= '" + vtime.trim() + " 23:59:59' ");
		}
		if( channel != null && channel.trim().length() > 0){
			sbCount.append(" and pubcode like '%" + channel.trim() + "%' ");
		}
				
		sbCount.append(" group by pubcode ,left(vtime,10) ");
		sbCount.append(" ) a ");
		
		return getJsonDataForChannel(pageNum, pageSize, draw, sb, sbCount);
	}

	// 统计PV
	public String queryPV(String vtime, String channel) {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(case when vstr1 is null then 1 else 0 end ) v_cnt1, sum(case when vstr1 = '1' then 1 else 0 end ) v_cnt2 from ad_req_log where 1=1 ");
		
		//拼装查询条件
		if( vtime != null && vtime.trim().length() > 0){
			sb.append(" and vtime >= '" + vtime.trim() + " 00:00:00' and vtime <= '" + vtime.trim() + " 23:59:59' ");
		}
		if( channel != null && channel.trim().length() > 0){
			sb.append(" and channel like '%" + channel.trim() + "%' ");
		}
		
		List<Map<String,Object>> resultCount = jdbcTemplate.queryForList(sb.toString(), new Object[]{});
		String v_cnt1 = String.valueOf(resultCount.get(0).get("v_cnt1"));
		String v_cnt2 = String.valueOf(resultCount.get(0).get("v_cnt2"));
		v_cnt1 = v_cnt1.equals("null") ? "0" : v_cnt1;
		v_cnt2 = v_cnt2.equals("null") ? "0" : v_cnt2;
		String pv = v_cnt1 + "&nbsp;&nbsp;&nbsp;" + v_cnt2;
		return pv;
	}

	/**
	 * 查询统计信息
	 * @param pageNum
	 * @param pageSize
	 * @param begin_vtime
	 * @param end_vtime
	 * @param channel
	 * @param draw
	 * @return
	 */
	public String queryCountData(String pageNum, String pageSize, String begin_vtime, String end_vtime, String channel,
			String draw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select left(vtime,10) vtime , count(1) ord_cnt,sum(case when flag=1 then 1 else 0 end) eft_cnt ");
		sb.append(" from ad_ord_log ");
		sb.append(" where 1=1 ");
		
		//拼装查询条件
		if( begin_vtime != null && begin_vtime.trim().length() > 0){
			sb.append(" and vtime >= '" + begin_vtime.trim() + " 00:00:00'");
		}
		if( end_vtime != null && end_vtime.trim().length() > 0){
			sb.append(" and vtime <= '" + end_vtime.trim() + " 23:59:59' ");
		}
		/*if( channel != null && channel.trim().length() > 0){
			sb.append(" and pubcode like '%" + channel.trim() + "%' ");
		}*/
		
		sb.append(" group by left(vtime,10) ");
		sb.append(" order by left(vtime,10) desc ");
		sb.append(" limit ?,?  ");
		
		// 求总数sql
		StringBuffer sbCount = new StringBuffer();
		sbCount.append(" select count(1) cnt ");
		sbCount.append(" from ( ");
		sbCount.append(" select pubcode from ad_ord_log where 1=1 ");
		
		//拼装查询条件
		if( begin_vtime != null && begin_vtime.trim().length() > 0){
			sbCount.append(" and vtime >= '" + begin_vtime.trim() + " 00:00:00'");
		}
		if( end_vtime != null && end_vtime.trim().length() > 0){
			sbCount.append(" and vtime <= '" + end_vtime.trim() + " 23:59:59' ");
		}
		/*if( channel != null && channel.trim().length() > 0){
			sbCount.append(" and pubcode like '%" + channel.trim() + "%' ");
		}*/
				
		sbCount.append(" group by left(vtime,10) ");
		sbCount.append(" ) a ");
		
		return getJsonData(pageNum, pageSize, draw, sb, sbCount);
	}

	/**
	 * 封装json
	 * @param pageNum
	 * @param pageSize
	 * @param draw
	 * @param sql
	 * @param sqlCount
	 * @return
	 */
	private String getJsonData(String pageNum, String pageSize, String draw, StringBuffer sql, StringBuffer sqlCount) {
		List<Map<String,Object>> result = jdbcTemplate.queryForList(sql.toString(),new Object[]{Integer.valueOf(pageNum),Integer.valueOf(pageSize)});
		List<Map<String,Object>> resultCount = jdbcTemplate.queryForList(sqlCount.toString(), new Object[]{});
		int total = Integer.valueOf(String.valueOf(resultCount.get(0).get("cnt")));
		DataBean<Map<String,Object>> data = new DataBean<Map<String,Object>>();
		data.setDraw(Integer.valueOf(draw));
		data.setRecordsTotal(total);
		data.setRecordsFiltered(total);
		data.setData(result);
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
	
	/** 封装json数据 包含渠道信息   */
	private String getJsonDataForChannel(String pageNum, String pageSize, String draw, StringBuffer sql, StringBuffer sqlCount) {
		List<Map<String,Object>> result = jdbcTemplate.queryForList(sql.toString(),new Object[]{Integer.valueOf(pageNum),Integer.valueOf(pageSize)});
		List<Map<String,Object>> resultCount = jdbcTemplate.queryForList(sqlCount.toString(), new Object[]{});
		if( channelInfos.isEmpty()){
			refreshChannel();
		}
		for(int i = 0 ; i < result.size() ; i++ ){
			Map<String,Object> map = result.get(i);
			map.put("busy_name", "");
			map.put("customer", "");
			map.put("st", "");
			String code = String.valueOf(map.get("a"));
			String vtime = String.valueOf(map.get("vtime"));
			for(Map<String,Object> m : channelInfos){
				if(m.get("channel").equals(code)){
					// 判断时间
					String st = String.valueOf(m.get("start_time"));
					String et = null;
					if(m.get("end_time") != null){
						et = String.valueOf(m.get("end_time"));
					}
					if( et == null ){
						if(vtime.compareTo(st) >= 0 ){
							map.put("busy_name", m.get("busy_name"));
							map.put("customer", m.get("customer"));
							map.put("st", m.get("st"));
						}
					}else{
						if(vtime.compareTo(st) >= 0 && vtime.compareTo(et) <= 0){
							map.put("busy_name", m.get("busy_name"));
							map.put("customer", m.get("customer"));
							map.put("st", m.get("st"));
						}
					}
				}
			}
		}
		int total = Integer.valueOf(String.valueOf(resultCount.get(0).get("cnt")));
		DataBean<Map<String,Object>> data = new DataBean<Map<String,Object>>();
		data.setDraw(Integer.valueOf(draw));
		data.setRecordsTotal(total);
		data.setRecordsFiltered(total);
		data.setData(result);
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
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param vtime
	 * @param channel
	 * @param draw
	 * @return
	 */
	public String queryCountDataForHour(String pageNum, String pageSize, String vtime, String channel, String draw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select pubcode a,                                                                        ");
		sb.append("       left(vtime,10) vtime ,                                                            ");
		sb.append("       case when right(vtime,8)>='00:00:00' and right(vtime,8)<='00:59:59' then 0");
		sb.append("		      when right(vtime,8)>='01:00:00' and right(vtime,8)<='01:59:59' then 1 ");
		sb.append("		      when right(vtime,8)>='02:00:00' and right(vtime,8)<='02:59:59' then 2 ");
		sb.append("		      when right(vtime,8)>='03:00:00' and right(vtime,8)<='03:59:59' then 3 ");
		sb.append("		      when right(vtime,8)>='04:00:00' and right(vtime,8)<='04:59:59' then 4 ");
		sb.append("		      when right(vtime,8)>='05:00:00' and right(vtime,8)<='05:59:59' then 5 ");
		sb.append("		      when right(vtime,8)>='06:00:00' and right(vtime,8)<='06:59:59' then 6 ");
		sb.append("		      when right(vtime,8)>='07:00:00' and right(vtime,8)<='07:59:59' then 7 ");
		sb.append("		      when right(vtime,8)>='08:00:00' and right(vtime,8)<='08:59:59' then 8 ");
		sb.append("		      when right(vtime,8)>='09:00:00' and right(vtime,8)<='09:59:59' then 9 ");
		sb.append("		      when right(vtime,8)>='10:00:00' and right(vtime,8)<='10:59:59' then 10 ");
		sb.append("		      when right(vtime,8)>='11:00:00' and right(vtime,8)<='11:59:59' then 11 ");
		sb.append("		      when right(vtime,8)>='12:00:00' and right(vtime,8)<='12:59:59' then 12 ");
		sb.append("		      when right(vtime,8)>='13:00:00' and right(vtime,8)<='13:59:59' then 13 ");
		sb.append("		      when right(vtime,8)>='14:00:00' and right(vtime,8)<='14:59:59' then 14 ");
		sb.append("		      when right(vtime,8)>='15:00:00' and right(vtime,8)<='15:59:59' then 15 ");
		sb.append("		      when right(vtime,8)>='16:00:00' and right(vtime,8)<='16:59:59' then 16 ");
		sb.append("		      when right(vtime,8)>='17:00:00' and right(vtime,8)<='17:59:59' then 17 ");
		sb.append("		      when right(vtime,8)>='18:00:00' and right(vtime,8)<='18:59:59' then 18 ");
		sb.append("		      when right(vtime,8)>='19:00:00' and right(vtime,8)<='19:59:59' then 19 ");
		sb.append("		      when right(vtime,8)>='20:00:00' and right(vtime,8)<='20:59:59' then 20 ");
		sb.append("		      when right(vtime,8)>='21:00:00' and right(vtime,8)<='21:59:59' then 21 ");
		sb.append("		      when right(vtime,8)>='22:00:00' and right(vtime,8)<='22:59:59' then 22 ");
		sb.append("		      when right(vtime,8)>='23:00:00' and right(vtime,8)<='23:59:59' then 23 ");
		sb.append("		 end vhour,                                                                           ");
		sb.append("		 count(1) ord_cnt,                                                                    ");
		sb.append("		 sum(case when flag=1 then 1 else 0 end) eft_cnt                                      ");
		sb.append("from ad_ord_log                                                                          ");
		sb.append("where 1=1                                                                                ");
		
		//拼装查询条件
		if( vtime != null && vtime.trim().length() > 0){
			sb.append(" and vtime >= '" + vtime.trim() + " 00:00:00' and vtime <= '" + vtime.trim() + " 23:59:59' ");
		}
		if( channel != null && channel.trim().length() > 0){
			sb.append(" and pubcode like '%" + channel.trim() + "%' ");
		}
		
		sb.append("group by pubcode ,                                                                       ");
		sb.append("       case when right(vtime,8)>='00:00:00' and right(vtime,8)<='00:59:59' then 0");
		sb.append("		      when right(vtime,8)>='01:00:00' and right(vtime,8)<='01:59:59' then 1 ");
		sb.append("		      when right(vtime,8)>='02:00:00' and right(vtime,8)<='02:59:59' then 2 ");
		sb.append("		      when right(vtime,8)>='03:00:00' and right(vtime,8)<='03:59:59' then 3 ");
		sb.append("		      when right(vtime,8)>='04:00:00' and right(vtime,8)<='04:59:59' then 4 ");
		sb.append("		      when right(vtime,8)>='05:00:00' and right(vtime,8)<='05:59:59' then 5 ");
		sb.append("		      when right(vtime,8)>='06:00:00' and right(vtime,8)<='06:59:59' then 6 ");
		sb.append("		      when right(vtime,8)>='07:00:00' and right(vtime,8)<='07:59:59' then 7 ");
		sb.append("		      when right(vtime,8)>='08:00:00' and right(vtime,8)<='08:59:59' then 8 ");
		sb.append("		      when right(vtime,8)>='09:00:00' and right(vtime,8)<='09:59:59' then 9 ");
		sb.append("		      when right(vtime,8)>='10:00:00' and right(vtime,8)<='10:59:59' then 10 ");
		sb.append("		      when right(vtime,8)>='11:00:00' and right(vtime,8)<='11:59:59' then 11 ");
		sb.append("		      when right(vtime,8)>='12:00:00' and right(vtime,8)<='12:59:59' then 12 ");
		sb.append("		      when right(vtime,8)>='13:00:00' and right(vtime,8)<='13:59:59' then 13 ");
		sb.append("		      when right(vtime,8)>='14:00:00' and right(vtime,8)<='14:59:59' then 14 ");
		sb.append("		      when right(vtime,8)>='15:00:00' and right(vtime,8)<='15:59:59' then 15 ");
		sb.append("		      when right(vtime,8)>='16:00:00' and right(vtime,8)<='16:59:59' then 16 ");
		sb.append("		      when right(vtime,8)>='17:00:00' and right(vtime,8)<='17:59:59' then 17 ");
		sb.append("		      when right(vtime,8)>='18:00:00' and right(vtime,8)<='18:59:59' then 18 ");
		sb.append("		      when right(vtime,8)>='19:00:00' and right(vtime,8)<='19:59:59' then 19 ");
		sb.append("		      when right(vtime,8)>='20:00:00' and right(vtime,8)<='20:59:59' then 20 ");
		sb.append("		      when right(vtime,8)>='21:00:00' and right(vtime,8)<='21:59:59' then 21 ");
		sb.append("		      when right(vtime,8)>='22:00:00' and right(vtime,8)<='22:59:59' then 22 ");
		sb.append("		      when right(vtime,8)>='23:00:00' and right(vtime,8)<='23:59:59' then 23 ");
		sb.append("		 end                                                                         ");
		sb.append(" limit ?,?  ");
		
		// 求总数sql
		StringBuffer sbCount = new StringBuffer();
		sbCount.append(" select count(1) cnt ");
		sbCount.append(" from ( ");
		sbCount.append(" select ");
		sbCount.append("       case when right(vtime,8)>='00:00:00' and right(vtime,8)<='00:59:59' then 0");
		sbCount.append("		      when right(vtime,8)>='01:00:00' and right(vtime,8)<='01:59:59' then 1 ");
		sbCount.append("		      when right(vtime,8)>='02:00:00' and right(vtime,8)<='02:59:59' then 2 ");
		sbCount.append("		      when right(vtime,8)>='03:00:00' and right(vtime,8)<='03:59:59' then 3 ");
		sbCount.append("		      when right(vtime,8)>='04:00:00' and right(vtime,8)<='04:59:59' then 4 ");
		sbCount.append("		      when right(vtime,8)>='05:00:00' and right(vtime,8)<='05:59:59' then 5 ");
		sbCount.append("		      when right(vtime,8)>='06:00:00' and right(vtime,8)<='06:59:59' then 6 ");
		sbCount.append("		      when right(vtime,8)>='07:00:00' and right(vtime,8)<='07:59:59' then 7 ");
		sbCount.append("		      when right(vtime,8)>='08:00:00' and right(vtime,8)<='08:59:59' then 8 ");
		sbCount.append("		      when right(vtime,8)>='09:00:00' and right(vtime,8)<='09:59:59' then 9 ");
		sbCount.append("		      when right(vtime,8)>='10:00:00' and right(vtime,8)<='10:59:59' then 10 ");
		sbCount.append("		      when right(vtime,8)>='11:00:00' and right(vtime,8)<='11:59:59' then 11 ");
		sbCount.append("		      when right(vtime,8)>='12:00:00' and right(vtime,8)<='12:59:59' then 12 ");
		sbCount.append("		      when right(vtime,8)>='13:00:00' and right(vtime,8)<='13:59:59' then 13 ");
		sbCount.append("		      when right(vtime,8)>='14:00:00' and right(vtime,8)<='14:59:59' then 14 ");
		sbCount.append("		      when right(vtime,8)>='15:00:00' and right(vtime,8)<='15:59:59' then 15 ");
		sbCount.append("		      when right(vtime,8)>='16:00:00' and right(vtime,8)<='16:59:59' then 16 ");
		sbCount.append("		      when right(vtime,8)>='17:00:00' and right(vtime,8)<='17:59:59' then 17 ");
		sbCount.append("		      when right(vtime,8)>='18:00:00' and right(vtime,8)<='18:59:59' then 18 ");
		sbCount.append("		      when right(vtime,8)>='19:00:00' and right(vtime,8)<='19:59:59' then 19 ");
		sbCount.append("		      when right(vtime,8)>='20:00:00' and right(vtime,8)<='20:59:59' then 20 ");
		sbCount.append("		      when right(vtime,8)>='21:00:00' and right(vtime,8)<='21:59:59' then 21 ");
		sbCount.append("		      when right(vtime,8)>='22:00:00' and right(vtime,8)<='22:59:59' then 22 ");
		sbCount.append("		      when right(vtime,8)>='23:00:00' and right(vtime,8)<='23:59:59' then 23 ");
		sbCount.append(" 		 end vhour                                                                           ");
		sbCount.append(" from ad_ord_log                                                                         ");
		sbCount.append(" where 1=1                                                                               ");
		
		//拼装查询条件
		if( vtime != null && vtime.trim().length() > 0){
			sbCount.append(" and vtime >= '" + vtime.trim() + " 00:00:00' and vtime <= '" + vtime.trim() + " 23:59:59' ");
		}
		if( channel != null && channel.trim().length() > 0){
			sbCount.append(" and pubcode like '%" + channel.trim() + "%' ");
		}
		
		sbCount.append(" group by                                                                                ");
		sbCount.append("       case when right(vtime,8)>='00:00:00' and right(vtime,8)<='00:59:59' then 0");
		sbCount.append("		      when right(vtime,8)>='01:00:00' and right(vtime,8)<='01:59:59' then 1 ");
		sbCount.append("		      when right(vtime,8)>='02:00:00' and right(vtime,8)<='02:59:59' then 2 ");
		sbCount.append("		      when right(vtime,8)>='03:00:00' and right(vtime,8)<='03:59:59' then 3 ");
		sbCount.append("		      when right(vtime,8)>='04:00:00' and right(vtime,8)<='04:59:59' then 4 ");
		sbCount.append("		      when right(vtime,8)>='05:00:00' and right(vtime,8)<='05:59:59' then 5 ");
		sbCount.append("		      when right(vtime,8)>='06:00:00' and right(vtime,8)<='06:59:59' then 6 ");
		sbCount.append("		      when right(vtime,8)>='07:00:00' and right(vtime,8)<='07:59:59' then 7 ");
		sbCount.append("		      when right(vtime,8)>='08:00:00' and right(vtime,8)<='08:59:59' then 8 ");
		sbCount.append("		      when right(vtime,8)>='09:00:00' and right(vtime,8)<='09:59:59' then 9 ");
		sbCount.append("		      when right(vtime,8)>='10:00:00' and right(vtime,8)<='10:59:59' then 10 ");
		sbCount.append("		      when right(vtime,8)>='11:00:00' and right(vtime,8)<='11:59:59' then 11 ");
		sbCount.append("		      when right(vtime,8)>='12:00:00' and right(vtime,8)<='12:59:59' then 12 ");
		sbCount.append("		      when right(vtime,8)>='13:00:00' and right(vtime,8)<='13:59:59' then 13 ");
		sbCount.append("		      when right(vtime,8)>='14:00:00' and right(vtime,8)<='14:59:59' then 14 ");
		sbCount.append("		      when right(vtime,8)>='15:00:00' and right(vtime,8)<='15:59:59' then 15 ");
		sbCount.append("		      when right(vtime,8)>='16:00:00' and right(vtime,8)<='16:59:59' then 16 ");
		sbCount.append("		      when right(vtime,8)>='17:00:00' and right(vtime,8)<='17:59:59' then 17 ");
		sbCount.append("		      when right(vtime,8)>='18:00:00' and right(vtime,8)<='18:59:59' then 18 ");
		sbCount.append("		      when right(vtime,8)>='19:00:00' and right(vtime,8)<='19:59:59' then 19 ");
		sbCount.append("		      when right(vtime,8)>='20:00:00' and right(vtime,8)<='20:59:59' then 20 ");
		sbCount.append("		      when right(vtime,8)>='21:00:00' and right(vtime,8)<='21:59:59' then 21 ");
		sbCount.append("		      when right(vtime,8)>='22:00:00' and right(vtime,8)<='22:59:59' then 22 ");
		sbCount.append("		      when right(vtime,8)>='23:00:00' and right(vtime,8)<='23:59:59' then 23 ");
		sbCount.append(" 		 end                                                                         ");
		sbCount.append(" ) a ");
		
		return getJsonData(pageNum, pageSize, draw, sb, sbCount);
	}
	
	/**
	 * 获取中奖信息
	 * @param pageNum
	 * @param pageSize
	 * @param channel
	 * @param draw
	 * @return
	 */
	public String getZJData(String pageNum, String pageSize, String channel, String draw) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select id,uname,phone,address,channel,vtime,ok_title,lty_angle,if_send,");
		sb.append("        case when if_send = 0 then '未确认'                  ");
		sb.append("             when if_send = 1 then '已确认'                  ");
		sb.append("             when if_send = 2 then '联系失败'                ");
		sb.append("				when if_send = 3 then '已发送'                ");
		sb.append("		  end send_status                               ");
		sb.append(" from ad_zj_info                              ");
		sb.append(" where 1=1       ");
		
		if( channel != null && channel.trim().length() > 0){
			sb.append(" and channel like '%" + channel.trim() + "%' ");
		}
		sb.append(" limit ?,?  ");
		
		StringBuffer sbCount = new StringBuffer();
		sbCount.append(" select count(1) cnt from ad_zj_info where 1=1 ");
		
		if( channel != null && channel.trim().length() > 0){
			sbCount.append(" and channel like '%" + channel.trim() + "%' ");
		}
		
		return getJsonData(pageNum, pageSize, draw, sb, sbCount);
	}

	/**
	 * 更新中奖信息的状态
	 * @param id
	 * @param if_send
	 * @return
	 */
	public String updateZJSendStatus(Integer id, Integer if_send) {
		String sql = " update ad_zj_info set if_send = ? where id = ? ";
		return jdbcTemplate.update(sql, new Object[]{if_send,id}) > 0 ? "1" : "0";
	}
	
	/**
     * 查询统计信息
     * @param pageNum
     * @param pageSize
     * @param begin_vtime
     * @param end_vtime
     * @param channel
     * @param draw
     * @return
     */
    public String queryAntiCheatingData(String pageNum, String pageSize, String begin_vtime, String end_vtime, String channel,
            String draw) {
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT pubcode,SUM(p_count) AS prate,SUM(c_count) AS crate,SUM(total) AS total FROM ad_cheatingcount WHERE 1=1 ");

        if(!StringUtil.isEmpty(begin_vtime))
        {
            sql.append(" AND vtime>='").append(begin_vtime+" 00:00:00").append("'");
        }
        if(!StringUtil.isEmpty(end_vtime))
        {
            sql.append(" AND vtime<='").append(end_vtime+" 23:59:59").append("'");
        }
        if(!StringUtil.isEmpty(channel))
        {
        	sql.append(" and pubcode like '%" + channel.trim() + "%' ");
        }
        sql.append(" group by pubcode order by SUM(total) desc limit ?,?");
        
        StringBuffer sbCount = new StringBuffer();
        sbCount.append("SELECT COUNT(1) as cnt FROM( SELECT pubcode FROM ad_cheatingcount WHERE 1=1 ");
        if(!StringUtil.isEmpty(begin_vtime))
        {
            sbCount.append(" AND vtime>='").append(begin_vtime).append("'");
        }
        if(!StringUtil.isEmpty(end_vtime))
        {
            sbCount.append(" AND vtime<='").append(end_vtime).append("'");
        }
        if(!StringUtil.isEmpty(channel))
        {
        	sbCount.append(" and pubcode like '%" + channel.trim() + "%' ");
        }
        sbCount.append(" group by pubcode) as A");
        
        
        return getAntiCheatingJsonData(pageNum, pageSize, draw, sql, sbCount);
    }
    
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(String v1, String v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供精确的乘法运算。
     * 
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).doubleValue();
    }

    private String getAntiCheatingJsonData(String pageNum, String pageSize, String draw, StringBuffer sql, StringBuffer sqlCount) {
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql.toString(),new Object[]{Integer.valueOf(pageNum),Integer.valueOf(pageSize)});
        List<Map<String,Object>> resultCount = jdbcTemplate.queryForList(sqlCount.toString(), new Object[]{});
        int total = Integer.valueOf(String.valueOf(resultCount.get(0).get("cnt")));
        for(Map<String,Object> map : result)
        {
            String pCount = map.get("prate").toString();
            String cCount = map.get("crate").toString();
            String totalCount = map.get("total").toString();
            double prate = div(pCount,totalCount,4);
            double crate = div(cCount,totalCount,4);
            double prateR = mul(prate+"","100");
            double crateR = mul(crate+"","100");
            map.put("prate",prateR+"%");
            map.put("crate",crateR+"%");
        }
        DataBean<Map<String,Object>> data = new DataBean<Map<String,Object>>();
        data.setDraw(Integer.valueOf(draw));
        data.setRecordsTotal(total);
        data.setRecordsFiltered(total);
        data.setData(result);
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
     * 查询渠道信息
     * @param channel
     * @return
     */
	public List<Map<String, Object>> queryChannelInfo(String channel) {
		String sql = " select * from ad_channel_info where channel = ? ";
		return jdbcTemplate.queryForList(sql, new Object[]{channel});
	}
	
	/**
	 * 查询渠道信息
	 * @param channel
	 * @return
	 */
	public List<Map<String, Object>> queryChannelInfo(Integer id) {
		String sql = " select * from ad_channel_info where id = ? ";
		return jdbcTemplate.queryForList(sql, new Object[]{id});
	}

	/**
	 * 添加渠道信息
	 * @param channel
	 * @param endTime 
	 * @param startTime 
	 * @param companyName
	 * @param channelDesc
	 */
	public void addChannel(String channel, String busyName, String customer, String startTime, String endTime) {
		String sql = "insert into ad_channel_info(channel,busy_name,customer,start_time,end_time) values(?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[]{channel,busyName,customer,startTime,endTime});
	}

	/**
	 * 获取渠道信息
	 * @param pageNum
	 * @param pageSize
	 * @param channel
	 * @param draw
	 * @return
	 */
	public String getChannelData(String pageNum, String pageSize, String channel, String draw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select id,channel,busy_name,customer,start_time,end_time,disable_flag,");
		sb.append("case when disable_flag = 1 then '已屏蔽' else '正常' end st ");
		sb.append(" from ad_channel_info where 1=1 ");
		
		if( channel != null && channel.trim().length() > 0){
			sb.append(" and channel like '%" + channel.trim() + "%' ");
		}
		sb.append(" limit ?,?  ");
		
		StringBuffer sbCount = new StringBuffer();
		sbCount.append(" select count(1) cnt from ad_channel_info where 1=1 ");
		
		if( channel != null && channel.trim().length() > 0){
			sbCount.append(" and channel like '%" + channel.trim() + "%' ");
		}
		
		return getJsonData(pageNum, pageSize, draw, sb, sbCount);
	}
	
	/**
	 * 更新渠道
	 * @param id
	 * @param channel
	 * @param busyName
	 * @param customer
	 */
	public void updateChannel(String id, String channel, String busyName, String customer,String startTime, String endTime) {
		String sql = "update ad_channel_info set busy_name=?,customer=?,start_time=?,end_time=? where id = ?";
		jdbcTemplate.update(sql, new Object[]{busyName,customer,startTime,endTime,id});
	}
	
	/**
	 * 删除渠道
	 * @param id
	 */
	public void deleteChannel(String id) {
		String sql = "delete from ad_channel_info where id = ?";
		jdbcTemplate.update(sql, new Object[]{id});
	}

	/**
	 * 
	 * @param id
	 * @param flag
	 */
	public void updateChannelSt(String id, String flag) {
		String sql = "update ad_channel_info set disable_flag = ? where id = ?";
		jdbcTemplate.update(sql, new Object[]{flag,id});
	}

	/**
	 * 检查渠道是否已经存在并且没有时间重叠
	 * @param channel
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean checkChannel(String id,String channel, String startTime, String endTime) {
		List<Map<String,Object>> list = queryChannelInfo(channel);
		if( list != null && list.size() > 0 ){
			//有此渠道存在 
			for( Map<String,Object> map : list ){
				if( id != null && String.valueOf(map.get("id")).equals(id)){
					continue;
				}
				//判断时间是否有重叠
				String start_time = String.valueOf(map.get("start_time"));
				String end_time = String.valueOf(map.get("end_time"));
				if( "null".equals(end_time)){
					end_time = null;
				}
				if(isConflict(start_time,end_time,startTime,endTime)){
					return true;
				}
			}
			
		}
		return false;
	}

	/**
	 *判断两时间段是否有时间冲突  即没有时间重叠
	 * @param startTime1 已经存在渠道的开始时间
	 * @param endTime1 已经存在渠道的结束时间
	 * @param startTime2 新添或修改的渠道的开始时间
	 * @param endTime2  新添或修改的渠道的结束时间
	 * @return
	 */
	private boolean isConflict(String startTime1, String endTime1, String startTime2, String endTime2) {
		if( endTime1 == null ){
			if( endTime2 != null && endTime2.compareTo(startTime1) < 0 ){
				return false;
			}
		}else{
			if( startTime2.compareTo(endTime1) > 0 ){
				return false;
			}
			if( endTime2 != null && endTime2.compareTo(startTime1) < 0 ){
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取渠道结算数据信息
	 * @param pageNum
	 * @param pageSize
	 * @param channel
	 * @param c_date
	 * @param draw
	 * @return
	 */
	public String getChannelFeeData(String pageNum, String pageSize, String channel, String c_date, String draw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from tb_channel_fee where 1=1 ");
		
		if( channel != null && channel.trim().length() > 0){
			sb.append(" and channel like '%" + channel.trim() + "%' ");
		}
		if( c_date != null && c_date.trim().length() > 0){
			sb.append(" and c_date ='" + c_date.trim() + "' ");
		}
		sb.append(" limit ?,?  ");
		
		StringBuffer sbCount = new StringBuffer();
		sbCount.append(" select count(1) cnt from tb_channel_fee where 1=1 ");
		
		if( channel != null && channel.trim().length() > 0){
			sbCount.append(" and channel like '%" + channel.trim() + "%' ");
		}
		if( c_date != null && c_date.trim().length() > 0){
			sbCount.append(" and c_date ='" + c_date.trim() + "' ");
		}
		
		return getJsonData(pageNum, pageSize, draw, sb, sbCount);
	}

	/**
	 * 获取结算总计 
	 * @param channel
	 * @param c_date
	 * @return
	 */
	public String getChannelTotalFeeData(String channel, String c_date) {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(fee_count) total_cnt,sum(fee) total_fee  from tb_channel_fee where 1=1 ");
		
		if( channel != null && channel.trim().length() > 0){
			sb.append(" and channel like '%" + channel.trim() + "%' ");
		}
		if( c_date != null && c_date.trim().length() > 0){
			sb.append(" and c_date ='" + c_date.trim() + "' ");
		}
		List<Map<String,Object>> cnt = jdbcTemplate.queryForList(sb.toString(), new Object[]{});
		if( cnt != null && cnt.size() > 0 ){
			if( cnt.get(0).get("total_cnt") != null ){
				return String.valueOf(cnt.get(0).get("total_cnt")) + "-"+String.valueOf(cnt.get(0).get("total_fee"));
			}
		}
		return null;
	}

	/**
	 * 获取大都会订单数
	 * @param channel
	 * @param c_date
	 * @param draw 
	 * @param pageNum 
	 * @param pageSize 
	 * @return
	 */
	public String getDdhData(String channel, String c_date, String pageSize, String pageNum, String draw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select channel ,left(vtime,10) c_date, count(1) cnt , sum(case when flag='true' then 1 else 0 end) true_cnt  ");
		sb.append("from ad_record_ddh ");
		sb.append("where 1=1 ");
		
		if( channel != null && channel.trim().length() > 0){
			sb.append(" and channel like '%" + channel.trim() + "%' ");
		}
		if( c_date != null && c_date.trim().length() > 0){
			sb.append(" and vtime >='" + c_date.trim() + " 00:00:00' and vtime<='"+ c_date.trim() +" 23:59:59'");
		}
		sb.append(" group by channel,left(vtime,10)");
		sb.append(" limit ?,?  ");
		
		StringBuffer sbCount = new StringBuffer();
		sbCount.append(" select count(1) cnt from ( select count(1) from ad_record_ddh where 1=1 ");
		
		if( channel != null && channel.trim().length() > 0){
			sbCount.append(" and channel like '%" + channel.trim() + "%' ");
		}
		if( c_date != null && c_date.trim().length() > 0){
			sbCount.append(" and vtime >='" + c_date.trim() + " 00:00:00' and vtime<='"+ c_date.trim() +" 23:59:59'");
		}
		
		sbCount.append(" group by channel,left(vtime,10) ) a");
		
		return getJsonData(pageNum, pageSize, draw, sb, sbCount);
	}

	/**
	 * 获取订单详细信息
	 * @param channel
	 * @param vtime
	 * @param pageSize
	 * @param pageNum
	 * @param draw
	 * @return
	 */
	public String getOrdDetailData(String channel, String vtime, String pageSize, String pageNum, String draw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ad_ord_log where 1=1 ");
		
		if(vtime != null && vtime.trim().length() > 0 ){
			sb.append(" and vtime >= '"+vtime.trim() +" 00:00:00' and vtime <= '"+vtime.trim()+" 23:59:59'");
		}
		if(channel != null && channel.trim().length() > 0){
			sb.append(" and pubcode = '" + channel.trim() + "'");
		}
		sb.append(" limit ?,?  ");
		 
		StringBuffer sbCount = new StringBuffer();
		sbCount.append("select count(1) cnt from ad_ord_log where 1=1 ");
		if(vtime != null && vtime.trim().length() > 0 ){
			sbCount.append(" and vtime >= '"+vtime.trim() +" 00:00:00' and vtime <= '"+vtime.trim()+" 23:59:59'");
		}
		if(channel != null && channel.trim().length() > 0){
			sbCount.append(" and pubcode = '" + channel.trim() + "'");
		}
		
		return getJsonData(pageNum, pageSize, draw, sb, sbCount);
	}

	/**
	 * 生成订单数据csv文件
	 * @param channel
	 * @param vtime
	 * @return
	 */
	public String createCSVforOrd(String channel, String vtime) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ad_ord_log where 1=1 ");
		if(vtime != null && vtime.trim().length() > 0 ){
			sb.append(" and vtime >= '"+vtime.trim() +" 00:00:00' and vtime <= '"+vtime.trim()+" 23:59:59'");
		}
		if(channel != null && channel.trim().length() > 0){
			sb.append(" and pubcode = '" + channel.trim() + "'");
		}
		sb.append(" limit ?,? ");
		int begin = 0;
		int len = 500;
		// 文件名称
		String fileName = System.currentTimeMillis()+"_"+(channel == null || channel.trim().length() <= 0 ? "all":channel)+"_"+(vtime == null || vtime.trim().length() <= 0 ? "all":vtime)+".csv";
		// 文件输出位置 /WEB_INF/export_files/
		String filePath = this.getClass().getClassLoader().getResource("/").getPath();
		filePath = filePath.substring(0,filePath.lastIndexOf("classes/")) + "export_files/" + fileName;
		System.out.println(filePath);
		OutputStream outStream = null;
		try {
			File file = new File(filePath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			outStream = new FileOutputStream(file);
			// 准备写数据了 
			long beginTime = System.currentTimeMillis();
			System.out.println("开始写数据--"+channel+"--"+vtime+"--"+beginTime);
			// 写标题
			outStream.write(ordTitle.getBytes("UTF-8"));
			outStream.write("\n\t".getBytes("UTF-8"));
			//获取数据
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString(), new Object[]{begin,len});
			StringBuilder strB = new StringBuilder();
			int count = 1;
			while( list != null && list.size() > 0 ){
				for( Map<String, Object> map : list ){
					// 拼装数据 
					strB.append(map.get("uname") == null ? "" : map.get("uname").toString()).append(",");
					strB.append(map.get("birthday") == null ? "" : map.get("birthday").toString()).append(",");
					strB.append(map.get("ddlSex") == null ? "" : map.get("ddlSex").toString()).append(",");
					strB.append(map.get("phone") == null ? "" : map.get("phone").toString()).append(",");
					strB.append(map.get("ipaddr") == null ? "" : map.get("ipaddr").toString()).append(",");
					strB.append(map.get("vtime") == null ? "" : map.get("vtime").toString()).append(",");
					strB.append(map.get("pubcode") == null ? "" : map.get("pubcode").toString()).append(",");
					strB.append(map.get("flag") == null ? "" : map.get("flag").toString()).append(",");
					strB.append(map.get("vstr1") == null ? "" : map.get("vstr1").toString());
					strB.append("\n\t");
					// 写数据到文件
					outStream.write(strB.toString().getBytes("UTF-8"));
					strB.setLength(0);// 清除数据
					count++;
				}
				outStream.flush();
				// 重新获取数据
				begin = begin + len;
				list.clear();
				list = null;
				list = jdbcTemplate.queryForList(sb.toString(), new Object[]{begin,len});
			}
			long endTime = System.currentTimeMillis();
			System.out.println("写完成--"+channel+"--"+vtime+"--耗时："+(endTime-beginTime)+"毫秒");
			System.out.println("count="+count);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if( outStream != null ){
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filePath;
	}
	
	/** 订单详情导出文件的标题 */
	private static final String ordTitle = "姓名,生日,性别,号码,IP,时间,渠道,同步状态,同步信息";
	
}
