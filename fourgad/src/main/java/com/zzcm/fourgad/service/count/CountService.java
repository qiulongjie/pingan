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
		
		List<Map<String,Object>> result = jdbcTemplate.queryForList(sb.toString(),new Object[]{Integer.valueOf(pageNum),Integer.valueOf(pageSize)});
		List<Map<String,Object>> resultCount = jdbcTemplate.queryForList(sbCount.toString(), new Object[]{});
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
	
}
