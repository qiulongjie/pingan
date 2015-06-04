package com.zzcm.fourgad.service.count;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzcm.fourgad.entity.DataBean;


@Component
@Transactional
public class CountService {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void AddReqLogs(String channel,String ipaddr,String prov,String vtime,String ua ){
		String sql = "insert into ad_req_log (channel,ipaddr,prov,vtime,ua) values (?,?,?,?,?)";
		Object o [] = {channel,ipaddr,prov,vtime,ua};
		jdbcTemplate.update(sql,o);
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
		/*StringBuffer sb = new StringBuffer();
		sb.append("select a.a ,");
		sb.append("       a.vtime,");
		sb.append("       a.cnt v_cnt,");
		sb.append("       case when b.cnt is null then 0 else b.cnt end ord_cnt,");
		sb.append("       case when b.eft_cnt is null then 0 else b.eft_cnt end eft_cnt ");
		sb.append("from ");
		sb.append("( ");
		sb.append("select channel a,left(vtime,10) vtime ,count(1) cnt ");
		sb.append("       from ad_req_log ");
		sb.append("       where 1=1 ");
		
		//拼装查询条件
		if( vtime != null && vtime.trim().length() > 0){
			sb.append(" and vtime >= '" + vtime.trim() + " 00:00:00' and vtime <= '" + vtime.trim() + " 23:59:59' ");
		}
		if( channel != null && channel.trim().length() > 0){
			sb.append(" and channel like '%" + channel.trim() + "%' ");
		}
		
		sb.append("       group by channel ,left(vtime,10) ");
		sb.append(") a ");
		sb.append("left join  ");
		sb.append("( ");
		sb.append("select pubcode a,left(vtime,10) vtime , count(1) cnt,sum(case when flag=1 then 1 else 0 end) eft_cnt  ");
		sb.append("       from ad_ord_log  ");
		sb.append("       where 1=1 ");
		
		//拼装查询条件
		if( vtime != null && vtime.trim().length() > 0){
			sb.append(" and vtime >= '" + vtime.trim() + " 00:00:00' and vtime <= '" + vtime.trim() + " 23:59:59' ");
		}
		if( channel != null && channel.trim().length() > 0){
			sb.append(" and pubcode like '%" + channel.trim() + "%' ");
		}
				
		sb.append("       group by pubcode ,left(vtime,10) ");
		sb.append(") b ");
		sb.append("on a.a = b.a and a.vtime = b.vtime ");
		sb.append("limit ?,? ");
		
		// 总数sql
		StringBuffer sbCount = new StringBuffer();
		sbCount.append(" select count(1) cnt ");
		sbCount.append(" from");
		sbCount.append(" (");
		sbCount.append(" select channel a from ad_req_log where 1=1 ");
		
		//拼装查询条件
		if( vtime != null && vtime.trim().length() > 0){
			sbCount.append(" and vtime >= '" + vtime.trim() + " 00:00:00' and vtime <= '" + vtime.trim() + " 23:59:59' ");
		}
		if( channel != null && channel.trim().length() > 0){
			sbCount.append(" and channel like '%" + channel.trim() + "%' ");
		}
		
		sbCount.append(" group by channel ,left(vtime,10)");
		sbCount.append(" ) a");*/
		
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
		
		return getJsonData(pageNum, pageSize, draw, sb, sbCount);
	}

	// 统计PV
	public String queryPV(String vtime, String channel) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) v_cnt from ad_req_log where 1=1 ");
		
		//拼装查询条件
		if( vtime != null && vtime.trim().length() > 0){
			sb.append(" and vtime >= '" + vtime.trim() + " 00:00:00' and vtime <= '" + vtime.trim() + " 23:59:59' ");
		}
		if( channel != null && channel.trim().length() > 0){
			sb.append(" and channel like '%" + channel.trim() + "%' ");
		}
		
		List<Map<String,Object>> resultCount = jdbcTemplate.queryForList(sb.toString(), new Object[]{});
		String pv = String.valueOf(resultCount.get(0).get("v_cnt"));
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
	
}
