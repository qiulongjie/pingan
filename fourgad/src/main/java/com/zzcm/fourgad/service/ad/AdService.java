package com.zzcm.fourgad.service.ad;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zzcm.fourgad.entity.AddrBean;
import com.zzcm.fourgad.entity.OrdLogs;
import com.zzcm.fourgad.util.DateUtil;

@Component
@Transactional
public class AdService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IPService iPService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void AddReqLogs(String channel,String ipaddr,String prov,String vtime,String ua ){
		//String sql = "insert into ad_req_log (channel,ipaddr,prov,vtime,ua) values (?,?,?,?,?)";
		// 分区表 
		String vmonth = vtime.substring(0, 8).replaceAll("-", "");
		String sql = "insert into ad_req_log_partition (channel,ipaddr,prov,vtime,ua,vmonth) values (?,?,?,?,?,?)";
		Object o [] = {channel,ipaddr,prov,vtime,ua,Integer.valueOf(vmonth)};
		jdbcTemplate.update(sql,o);
	}
	
    public void AddReqLogs(String channel, String ipaddr, String prov, String vtime, String ua, String vstr1) {
    	//String sql = "insert into ad_req_log (channel,ipaddr,prov,vtime,ua,vstr1) values (?,?,?,?,?,?)";
    	// 分区表
    	String vmonth = vtime.substring(0, 8).replaceAll("-", "");
    	String sql = "insert into ad_req_log_partition (channel,ipaddr,prov,vtime,ua,vstr1,vmonth) values (?,?,?,?,?,?,?)";
		Object o [] = {channel,ipaddr,prov,vtime,ua,vstr1,Integer.valueOf(vmonth)};
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
	
	public void AddOrdLogs(String uname,String birthday,String ddlSex,String phone,String ipaddr,String prov,String vtime,String pubcode,String pcontent,Integer flag){
		String sql = "insert into ad_ord_log (uname,birthday,ddlSex,phone,ipaddr,prov,vtime,pubcode,pcontent,flag) values (?,?,?,?,?,?,?,?,?,?)";
		Object o [] = {uname,birthday,ddlSex,phone,ipaddr,prov,vtime,pubcode,pcontent,flag};
		jdbcTemplate.update(sql,o);
	}
	
	public void AddOrdLogs(String uname,String birthday,String ddlSex,String phone,
			               String ipaddr,String prov,String vtime,String pubcode,
			               String pcontent,Integer flag,Integer ads,String province,String city){
		String sql = "insert into ad_ord_log (uname,birthday,ddlSex,phone,ipaddr,prov,vtime,pubcode,pcontent,flag,ads,province,city) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object o [] = {uname,birthday,ddlSex,phone,ipaddr,prov,vtime,pubcode,pcontent,flag,ads,province,city};
		jdbcTemplate.update(sql,o);
	}
	
	/**
	 * 使用程序push过来的订单 测试用的 
	 */
	public void AddOrdLogsPush(String uname,String birthday,String ddlSex,String phone,String ipaddr,String prov,String vtime,String pubcode,String pcontent){
		String sql = "insert into ad_ord_log_push (uname,birthday,ddlSex,phone,ipaddr,prov,vtime,pubcode,pcontent) values (?,?,?,?,?,?,?,?,?)";
		Object o [] = {uname,birthday,ddlSex,phone,ipaddr,prov,vtime,pubcode,pcontent};
		jdbcTemplate.update(sql,o);
	}
	
	public void updOrdLogs(Long id,int flag,String msg){
		String sql = "update ad_ord_log set flag = ?,vstr1 = ? where id = ?";
		Object o [] = {flag,msg,id};
		jdbcTemplate.update(sql,o);
	}
	public void updOrdLogsPush(Long id,int flag,String msg){
		String sql = "update ad_ord_log_push set flag = ?,vstr1 = ? where id = ?";
		Object o [] = {flag,msg,id};
		jdbcTemplate.update(sql,o);
	}
	
	public void updOrdLogsPush(Long id,int flag,String msg,String prov){
		String sql = "update ad_ord_log_push set flag = ?,vstr1 = ?,prov = ? where id = ?";
		Object o [] = {flag,msg,prov,id};
		jdbcTemplate.update(sql,o);
	}
	public void updOrdLogs(Long id,int flag,String msg,String prov){
		String sql = "update ad_ord_log set flag = ?,vstr1 = ?,prov = ? where id = ?";
		Object o [] = {flag,msg,prov,id};
		jdbcTemplate.update(sql,o);
	}
	
	public List<Map<String,Object>> getOrdsByFlag(int flag,int num){
		String sql = "select * from ad_ord_log where flag = ? and vtime>=? and vtime <=? and pubcode not in (select channel from ad_channel_info a where a.disable_flag = 1) limit 0,?";
		Object o [] = {flag,DateUtil.getTodayDate()+" 00:00:00", DateUtil.getTodayDate()+" 23:59:59",num};
		return jdbcTemplate.queryForList(sql,o);
	}
	
	/**
	 * 根据ads(接口类型),flag(订单标识)获取订单
	 * @param flag
	 * @param num
	 * @return
	 */
	public List<Map<String,Object>> getOrdsByFlagAds(int flag, int ads,int num){
		String sql = "select id, uname, birthday, phone, pubcode, case when ddlSex='男' then 1 else 0 end ddlSex"
				+ " from ad_ord_log where flag = ? and ads = ? and vtime>=? and vtime <=? "
				+ "and pubcode not in (select channel from ad_channel_info a where a.disable_flag = 1) limit 0,?";
		Object o [] = {flag, ads, DateUtil.getTodayDate()+" 00:00:00", DateUtil.getTodayDate()+" 23:59:59",num};
		return jdbcTemplate.queryForList(sql,o);
	}

	public List<OrdLogs> getOrds(long id, int num)
	{
	    String sql = "select * from ad_ord_log where id > ? order by id asc limit 0,?";
	    Object o [] = {id,num};
	    RowMapper<OrdLogs> mapper = BeanPropertyRowMapper.newInstance(OrdLogs.class);
	    return jdbcTemplate.query(sql,o,mapper);
	}
	/**
	 * 获取 抽奖计算结果
	 * @param channel
	 * @param z
	 * @param time 
	 * @param ip 
	 * @return
	 */
	public String getLtyInfo2As(String channel, String z,String p ,String c, String ip, String time) {
		
		//统计转盘点击日志
		String insertSql = " insert into ad_lty_click_log(channel,vtime,ipaddr) values(?,?,?) ";
		jdbcTemplate.update(insertSql, new Object[]{channel,time,ip});
		
		Map<String,Object> resultMap = new HashMap<String, Object>();//结果信息
		// 没有渠道
		if(channel == null || channel.trim().equals("")){
			channel = "xx";
		}
		// 获取渠道的活动时间
		Date date=new Date();//取时间				
		String currTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String sql = "select * from ad_lty_info where channel = ? limit 0,1";
		List<Map<String,Object>> condition = jdbcTemplate.queryForList(sql,new Object[]{channel});
		if( condition == null || condition.size() <= 0 ){
			//resultMap.put("error", "此渠道还未设置奖项信息或者活动未开始或已结束");
			//return getJson(resultMap);
			condition = jdbcTemplate.queryForList("select * from ad_lty_info where channel = 'xx' ",new Object[]{});
			if( condition != null && condition.size() > 0 ){
				resultMap = condition.get(0);
				
				// 返回中奖信息
				return getJson(resultMap);
			}
		}
		// 获取开始与结束时间
		String startTime = String.valueOf(condition.get(0).get("start_time"));
		String endTime = String.valueOf(condition.get(0).get("end_time"));
		
		// 判断当前是否符合条件
		// 查询该时间范围的有效订单数flag=1
		String eft_cnt_sql = " select count(1) eft_cnt "+
							" from ad_ord_log "+
							" where flag=1 "+
							" and vtime >= ? and vtime <= ? "+
							" and pubcode = ? ";
		List<Map<String,Object>> eftCnt = jdbcTemplate.queryForList(eft_cnt_sql,new Object[]{startTime+" 00:00:00",endTime+" 23:59:59",channel});
		//有效订单数
		int eftCntNum = 0;
		if(eftCnt != null && eftCnt.size() > 0 ){
			eftCntNum = Integer.valueOf(String.valueOf(eftCnt.get(0).get("eft_cnt")));
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
		
		resultMap = null;//中奖信息
		if( z != null && !z.trim().equals("") 
		    && p != null && p.trim().equals("fake") 
		    && c != null && c.trim().equals("jia")){
			
			String s = "select * from ad_lty_info where channel = ? and start_time <= ? and end_time >= ? and lty_angle = ? and allow_num > 0 limit 0,1";
			condition = jdbcTemplate.queryForList(s,new Object[]{channel,currTime,currTime,z});
			if( condition != null && condition.size() > 0 ){
				resultMap = condition.get(0);
				String updateSql = " update ad_lty_info set allow_num = allow_num-1 where id = ? ";
				jdbcTemplate.update(updateSql, new Object[]{resultMap.get("id")});
				// 返回中奖信息
				return getJson(resultMap);
			}
		}
		String s = "select * from ad_lty_info where channel = ? and v_count = 0 ";
		condition = jdbcTemplate.queryForList(s,new Object[]{channel});
		if( condition != null && condition.size() > 0 ){
			resultMap = condition.get(0);
			
			// 返回中奖信息
			return getJson(resultMap);
		}
		
		// 返回中奖信息
		return getJson(resultMap);
	}
	
	/**
	 * 获取 抽奖计算结果 爱奇艺s
	 * @param channel
	 * @param z
	 * @param time 
	 * @param ip 
	 * @return
	 */
	public String getLtyInfo2aqy(String channel, String z,String p ,String c, String ip, String time) {
		
		//统计转盘点击日志
		String insertSql = " insert into ad_lty_click_log(channel,vtime,ipaddr) values(?,?,?) ";
		jdbcTemplate.update(insertSql, new Object[]{channel,time,ip});
		
		Map<String,Object> resultMap = new HashMap<String, Object>();//结果信息
		// 没有渠道
		if(channel == null || channel.trim().equals("")){
			channel = "xx";
		}
		// 获取渠道的活动时间
		Date date=new Date();//取时间				
		String currTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String sql = "select * from ad_lty_info where channel = ? limit 0,1";
		List<Map<String,Object>> condition = jdbcTemplate.queryForList(sql,new Object[]{channel});
		if( condition == null || condition.size() <= 0 ){
			condition = jdbcTemplate.queryForList("select * from ad_lty_info where channel = 'xx' ",new Object[]{});
			if( condition != null && condition.size() > 0 ){
				resultMap = condition.get(0);
				
				// 返回中奖信息
				return getJson(resultMap);
			}
		}
		// 获取开始与结束时间
		String startTime = String.valueOf(condition.get(0).get("start_time"));
		String endTime = String.valueOf(condition.get(0).get("end_time"));
		
		// 判断当前是否符合条件
		// 查询该时间范围的有效订单数flag=1
		String eft_cnt_sql = " select count(1) eft_cnt "+
							" from ad_ord_log "+
							" where flag=1 "+
							" and vtime >= ? and vtime <= ? "+
							" and pubcode = ? ";
		List<Map<String,Object>> eftCnt = jdbcTemplate.queryForList(eft_cnt_sql,new Object[]{startTime+" 00:00:00",endTime+" 23:59:59",channel});
		//有效订单数
		int eftCntNum = 0;
		if(eftCnt != null && eftCnt.size() > 0 ){
			eftCntNum = Integer.valueOf(String.valueOf(eftCnt.get(0).get("eft_cnt")));
		}
		eftCntNum++;
		
		String sql2 = "select * from ad_lty_info where channel = ? and start_time <= ? and end_time >= ? and v_count = ? and allow_num > 0 ";
		List<Map<String,Object>> condition2 = jdbcTemplate.queryForList(sql2,new Object[]{channel,currTime,currTime,eftCntNum});
		if( condition2 != null && condition2.size() > 0 ){
			
			// 有中奖信息
			resultMap = condition2.get(0);
			
			String updateSql = " update ad_lty_info set allow_num = allow_num-1 where id = ? ";
			jdbcTemplate.update(updateSql, new Object[]{resultMap.get("id")});
			
			//  获取爱奇艺激活码   start 
			String lty_angle = String.valueOf(resultMap.get("lty_angle"));
			
			String jihuomaSql = "select * from tb_aqy_jihuoma where lty_angle = ? and flag = 1 limit 0,1";
			List<Map<String,Object>> jihuomaResult = jdbcTemplate.queryForList(jihuomaSql,new Object[]{lty_angle});
			if(jihuomaResult != null && jihuomaResult.size() > 0){
				Map<String, Object> m = jihuomaResult.get(0);
				int id = Integer.valueOf(String.valueOf(m.get("id")));
				String uSql = "update tb_aqy_jihuoma set flag = 0 where id = ? ";
				jdbcTemplate.update(uSql, new Object[]{id});
				resultMap.put("jihuoma", String.valueOf(m.get("jihuoma")));
			}else{
				resultMap.put("jihuoma", "x");
			}
		    //  获取爱奇艺激活码   end 
			// 返回中奖信息
			return getJson(resultMap);
		}
		
		resultMap = null;//中奖信息
		String s = "select * from ad_lty_info where channel = ? and v_count = 0 ";
		condition = jdbcTemplate.queryForList(s,new Object[]{channel});
		if( condition != null && condition.size() > 0 ){
			resultMap = condition.get(0);
			resultMap.put("jihuoma", "x");
			// 返回中奖信息
			return getJson(resultMap);
		}
		
		// 返回中奖信息
		return getJson(resultMap);
	}
	
	/**
	 * 获取 抽奖计算结果 掌阅
	 * @param channel
	 * @param z
	 * @param time 
	 * @param ip 
	 * @return
	 */
	public String getLottery2zy(String channel, String z,String p ,String c, String ip, String time) {
		// 没有渠道
		if(channel == null || channel.trim().equals("")){
			channel = "xx";
		}
		
		//统计转盘点击日志
		String insertSql = " insert into ad_lty_click_log(channel,vtime,ipaddr) values(?,?,?) ";
		jdbcTemplate.update(insertSql, new Object[]{channel,time,ip});
		
		Map<String,Object> resultMap = new HashMap<String, Object>();//结果信息
		List<Map<String,Object>> condition = new ArrayList<Map<String,Object>>();
		
		// **** 根据IP定位位置跳转到大都会  start ******
		long startTime1 = System.currentTimeMillis();
		logger.info("startTime===="+startTime1);
		AddrBean addrBean = iPService.getIPAddr2(ip);
		long endTime1 = System.currentTimeMillis();
		logger.info("耗时=="+(endTime1-startTime1));
		String province = addrBean.getProvinceCode();// 根据IP获取的省份名称
		// 从数据库中获取渠道省份对应的跳转链接 
		String proSql = "select * from ad_province_redirect where channel = ? and province_name like ?";
		condition = jdbcTemplate.queryForList(proSql,new Object[]{channel,province+"%"});
		if( condition != null && condition.size() > 0 ){
			Map<String,Object> map = condition.get(0);
			// 取出默认的中奖信息  即平安意外险 
			String s = "select * from ad_lty_info where channel = ? and v_count = 0 ";
			condition.clear();
			condition = null;
			condition = jdbcTemplate.queryForList(s,new Object[]{channel});
			if( condition != null && condition.size() > 0 ){
				resultMap = condition.get(0);
				resultMap.put("link_url", map.get("link_url"));// 修改跳转链接
				// 返回中奖信息
				logger.info(ip+"命中"+province+",跳转"+map.get("link_url"));
				return getJson(resultMap);
			}
		}
		// **** 根据IP定位位置跳转到大都会  END ******
		
		// 获取渠道的活动时间
		Date date=new Date();//取时间				
		String currTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String sql = "select * from ad_lty_info where channel = ? limit 0,1";
		condition.clear();
		condition = null;
		condition = jdbcTemplate.queryForList(sql,new Object[]{channel});
		if( condition == null || condition.size() <= 0 ){
			condition = jdbcTemplate.queryForList("select * from ad_lty_info where channel = 'xx' ",new Object[]{});
			if( condition != null && condition.size() > 0 ){
				resultMap = condition.get(0);
				
				// 返回中奖信息
				return getJson(resultMap);
			}
		}
		// 获取开始与结束时间
		String startTime = String.valueOf(condition.get(0).get("start_time"));
		String endTime = String.valueOf(condition.get(0).get("end_time"));
		
		// 判断当前是否符合条件
		// 查询该时间范围的有效订单数flag=1
		String eft_cnt_sql = " select count(1) eft_cnt "+
				" from ad_ord_log "+
				" where flag=1 "+
				" and vtime >= ? and vtime <= ? "+
				" and pubcode = ? ";
		List<Map<String,Object>> eftCnt = jdbcTemplate.queryForList(eft_cnt_sql,new Object[]{startTime+" 00:00:00",endTime+" 23:59:59",channel});
		//有效订单数
		int eftCntNum = 0;
		if(eftCnt != null && eftCnt.size() > 0 ){
			eftCntNum = Integer.valueOf(String.valueOf(eftCnt.get(0).get("eft_cnt")));
		}
		eftCntNum++;
		
		String sql2 = "select * from ad_lty_info where channel = ? and start_time <= ? and end_time >= ? and v_count = ? and allow_num > 0 ";
		List<Map<String,Object>> condition2 = jdbcTemplate.queryForList(sql2,new Object[]{channel,currTime,currTime,eftCntNum});
		if( condition2 != null && condition2.size() > 0 ){
			
			// 有中奖信息
			resultMap = condition2.get(0);
			String updateSql = " update ad_lty_info set allow_num = allow_num-1 where id = ? ";
			jdbcTemplate.update(updateSql, new Object[]{resultMap.get("id")});
			
			//  获取掌阅代金券兑换码   start 
			String lty_angle = String.valueOf(resultMap.get("lty_angle"));
			
			String jihuomaSql = "select * from tb_zy_daijinquan where channel = ? and lty_angle = ? and flag = 1 limit 0,1";
			List<Map<String,Object>> daijinquanResult = jdbcTemplate.queryForList(jihuomaSql,new Object[]{channel,lty_angle});
			if(daijinquanResult != null && daijinquanResult.size() > 0){
				Map<String, Object> m = daijinquanResult.get(0);
				int id = Integer.valueOf(String.valueOf(m.get("id")));
				String uSql = "update tb_zy_daijinquan set flag = 0 where id = ? ";
				jdbcTemplate.update(uSql, new Object[]{id});
				resultMap.put("duihuan_code", String.valueOf(m.get("duihuan_code")));
			}else{
				resultMap.put("duihuan_code", "x");
			}
			//  获取掌阅代金券兑换码   end 
			// 返回中奖信息
			return getJson(resultMap);
		}
		
		resultMap = null;//中奖信息
		String s = "select * from ad_lty_info where channel = ? and v_count = 0 ";
		condition.clear();
		condition = null;
		condition = jdbcTemplate.queryForList(s,new Object[]{channel});
		if( condition != null && condition.size() > 0 ){
			resultMap = condition.get(0);
			resultMap.put("duihuan_code", "x");
			// 返回中奖信息
			return getJson(resultMap);
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
	 * @param lty_angle 
	 * @param ok_title 
	 */
	public void addRewardInfo(String uname, String phone, String address, String ip, String channel, String time,String ua, String ok_title, String lty_angle) {
		String sql = " insert into ad_zj_info(uname,phone,address,ip,channel,vtime,if_send,ua,ok_title,lty_angle) values(?,?,?,?,?,?,?,?,?,?) ";
		jdbcTemplate.update(sql,new Object[]{uname, phone, address, ip, channel, time,0,ua,ok_title,lty_angle});
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
		if( !ywx.equals("0")){
			if( birthday == null || birthday.trim().equals("") ){
				birthday = "19890809";
			}
			AddOrdLogs(uname, birthday, "男", phone, ipaddr, "", vtime, channel, carNum);
		}
	}

	/**
	 * 获取抽奖初始化信息
	 * @param channel
	 * @return
	 */
	public String getLtyInitInfo(String channel,String ltyType) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ad_lty_init_info where channel = ? and lty_type = ? ");
		List<Map<String, Object>> data = jdbcTemplate.queryForList(sb.toString(),new Object[]{channel,ltyType});
		if( data != null && data.size() > 0 ){
			return getJson(data.get(0));
		}else{
			data = jdbcTemplate.queryForList("select * from ad_lty_init_info where channel = 'xx'",new Object[]{});
			if( data != null && data.size() > 0 ){
				return getJson(data.get(0));
			}
		}
		return null;
	}
	
	public String getEftCntNum(String channel){
		String eft_cnt_sql = " select pubcode a,sum(case when flag=1 then 1 else 0 end) eft_cnt "+
							" from ad_ord_log "+
							" where pubcode = ? ";
		List<Map<String,Object>> eftCnt = jdbcTemplate.queryForList(eft_cnt_sql,new Object[]{channel});
		String eftCntNum = "0";
		if (eftCnt != null && eftCnt.size() > 0) {
			if (eftCnt.get(0).get("eft_cnt") != null) {
				eftCntNum = String.valueOf(eftCnt.get(0).get("eft_cnt"));
			}
		}
		
		String sql = "select * from ad_lty_info where channel = ? ";
		String sqlzj = "select * from ad_zj_info where channel = ? ";
		List<Map<String,Object>> ltyInfo = jdbcTemplate.queryForList(sql,new Object[]{channel});
		List<Map<String,Object>> zjInfo = jdbcTemplate.queryForList(sqlzj,new Object[]{channel});
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("eftCntNum", eftCntNum);
		resultMap.put("ltyInfo", ltyInfo);
		resultMap.put("zjInfo", zjInfo);
		return getJson(resultMap);
	}

	// =====  你我贷  start
	/**
	 * 获取你我贷连接的渠道和添加信息
	 * @param cid
	 * @param uname
	 * @param phone
	 * @param birthday
	 * @param isCheck
	 * @return
	 */
	public String addNiwodaiOrdLog(String cid, String uname, String phone, String birthday, String isCheck,String ipaddr,String vtime) {
		String sql = " insert into tb_niwodai_ord_log (channel,uname,phone,birthday,is_check,ipaddr,vtime) values(?,?,?,?,?,?,?) ";
		jdbcTemplate.update(sql, new Object[]{cid,uname,phone,birthday,Integer.valueOf(isCheck),ipaddr,vtime});
		sql = "select channel from tb_cid_channel where cid = ? ";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql,new Object[]{cid});
		if(list !=  null && list.size() > 0 ){
			return String.valueOf(list.get(0).get("channel"));
		}else{
			return jdbcTemplate.queryForObject("select channel from tb_cid_channel where cid = 0 ", String.class);
		}
	}
	// =====  你我贷  end

	/**
	 * 荣时代结算数据同步
	 * @param feeData
	 */
	public void saveFeeData(String feeData,final String ipaddr,final String vtime) throws Exception{
		String sql = "insert into tb_channel_fee(c_date,channel,fee_count,fee,ipaddr,vtime) values(?,?,?,?,?,?)";
		String sqlDel = "delete from tb_channel_fee where c_date = ? ";
		JsonParser parser = new JsonParser();
		JsonElement je = parser.parse(feeData);
		JsonArray jsonArray = null ;
		if(je.isJsonArray()){
			jsonArray = je.getAsJsonArray();
		}
		if(jsonArray != null ){
			Iterator<JsonElement> it = jsonArray.iterator();
			while(it.hasNext()){
				JsonElement el = it.next();
				JsonObject obj = el.getAsJsonObject();
				final String date = obj.get("date").getAsString();
				JsonArray arr = obj.get("data").getAsJsonArray();
				Iterator<JsonElement> i = arr.iterator();
				final List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				while(i.hasNext()){
					Map<String,Object> map = new HashMap<String, Object>();
					JsonElement e = i.next();
					JsonObject o = e.getAsJsonObject();
					map.put("channel", o.get("channel").getAsString());
					map.put("fee_count", o.get("fee_count").getAsInt());
					map.put("fee", o.get("fee").getAsDouble());
					list.add(map);
				}
				jdbcTemplate.update(sqlDel,new Object[]{date});
				jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						int count = 1;
						ps.setString(count++, date);
						ps.setObject(count++, list.get(i).get("channel"));
						ps.setObject(count++, list.get(i).get("fee_count"));
						ps.setObject(count++, list.get(i).get("fee"));
						ps.setString(count++, ipaddr);
						ps.setString(count++, vtime);
					}
					
					@Override
					public int getBatchSize() {
						return list.size();
					}
				});
			}
		}
		/*List<LinkedHashMap<String, Object>> list = JsonUtil.fromJson(feeData, List.class);
		for(LinkedHashMap<String, Object> map : list){
			final String date = String.valueOf(map.get("date"));
			final ArrayList<LinkedHashMap<String,Object>> data =  (ArrayList<LinkedHashMap<String, Object>>) map.get("data");
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					int count = 1;
					ps.setString(count++, date);
					ps.setString(count++, String.valueOf(data.get(i).get("channel")));
					ps.setInt(count++, Integer.valueOf(String.valueOf(data.get(i).get("fee_count"))));
					ps.setDouble(count++, Double.valueOf(String.valueOf(data.get(i).get("fee"))));
					ps.setString(count++, ipaddr);
					ps.setString(count++, vtime);
				}
				
				@Override
				public int getBatchSize() {
					return data.size();
				}
			});
		}*/
	}

	// ************************** 新的抽奖页面  九宫格  start ******************
	/**
	 * 获取九宫格抽奖页面初始化信息
	 * @param channel
	 * @param ltyType
	 * @return
	 */
	public String getLtyInitInfoNew(String channel, String ltyType) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ad_lty_init_info_new where channel = ? and lty_type = ? ");
		List<Map<String, Object>> data = jdbcTemplate.queryForList(sb.toString(),new Object[]{channel,ltyType});
		if( data != null && data.size() > 0 ){
			return getJson(data.get(0));
		}
		return null;
	}
	
	/**
	 * 获取九宫格抽奖结果
	 * @param channel
	 * @param z
	 * @param p
	 * @param c
	 * @param ip
	 * @param time
	 * @return
	 */
	public String getLotteryNew(String channel, String z, String p, String c, String ip, String time) {
		//统计转盘点击日志
		String insertSql = " insert into ad_lty_click_log(channel,vtime,ipaddr) values(?,?,?) ";
		jdbcTemplate.update(insertSql, new Object[]{channel,time,ip});
		
		Map<String,Object> resultMap = new HashMap<String, Object>();//结果信息
		// 没有渠道
		if(channel == null || channel.trim().equals("")){
			channel = "xx";
		}
		// 获取渠道的活动时间
		
		String sql = "select * from ad_lty_info_new where channel = ? limit 0,1";
		List<Map<String,Object>> condition = jdbcTemplate.queryForList(sql,new Object[]{channel});
		if( condition == null || condition.size() <= 0 ){
			//resultMap.put("error", "此渠道还未设置奖项信息或者活动未开始或已结束");
			//return getJson(resultMap);
			condition = jdbcTemplate.queryForList("select * from ad_lty_info_new where channel = 'xx' ",new Object[]{});
			if( condition != null && condition.size() > 0 ){
				resultMap = condition.get(0);
				
				// 返回中奖信息
				return getJson(resultMap);
			}
		}
		
		// 判断当前是否符合条件
		// 查询该时间范围的有效订单数flag=1
		Date date=new Date();//取时间				
		String currTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String eft_cnt_sql = " select count(1) eft_cnt "+
							" from ad_ord_log "+
							" where flag=1 "+
							" and vtime <= ? "+
							" and pubcode = ? ";
		List<Map<String,Object>> eftCnt = jdbcTemplate.queryForList(eft_cnt_sql,new Object[]{currTime+" 23:59:59",channel});
		//有效订单数
		int eftCntNum = 0;
		if(eftCnt != null && eftCnt.size() > 0 ){
			eftCntNum = Integer.valueOf(String.valueOf(eftCnt.get(0).get("eft_cnt")));
		}
		eftCntNum++;
		
		String sql2 = "select * from ad_lty_info_new where channel = ? and v_count = ? and allow_num > 0 ";
		List<Map<String,Object>> condition2 = jdbcTemplate.queryForList(sql2,new Object[]{channel,eftCntNum});
		if( condition2 != null && condition2.size() > 0 ){
			
			// 有中奖信息
			resultMap = condition2.get(0);
			String updateSql = " update ad_lty_info_new set allow_num = allow_num-1 where id = ? ";
			jdbcTemplate.update(updateSql, new Object[]{resultMap.get("id")});
			
			// 返回中奖信息
			return getJson(resultMap);
		}
		
		resultMap = null;//中奖信息
		
		String s = "select * from ad_lty_info_new where channel = ? and v_count = 0 ";
		condition = jdbcTemplate.queryForList(s,new Object[]{channel});
		if( condition != null && condition.size() > 0 ){
			resultMap = condition.get(0);
			
			// 返回中奖信息
			return getJson(resultMap);
		}
		
		// 返回中奖信息
		return getJson(resultMap);
	}
	// ************************** 新的抽奖页面  九宫格  END ******************

	/**
	 * 获取掌阅的中奖  1000月饼
	 * @param channel
	 * @param ip
	 * @param time
	 * @return
	 */
	public String getLtyPrizeZy(String channel,String ltyPrize, String ip, String time) {
		String sql = "select * from tb_zy_yuebing where channel = ? and lty_prize = ? and flag > 0";
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql,new Object[]{channel,ltyPrize});
		String duihuan_code = "";
		if(result != null && result.size() > 0){
			duihuan_code = String.valueOf(result.get(0).get("duihuan_code"));
			sql = "update tb_zy_yuebing set flag = 0 where id = ? ";
			jdbcTemplate.update(sql, new Object[]{result.get(0).get("id")});
			// 记录访问记录
			sql = "insert into tb_zy_yuebing_log(channel,duihuan_code,ipaddr,vtime) values(?,?,?,?)";
			jdbcTemplate.update(sql, new Object[]{channel,duihuan_code,ip,time});
			return getJson(result.get(0));
		}
		return null;
	}

	/**
	 * 
	 * @param cid
	 * @param ipaddr
	 * @param vtime
	 * @param ua
	 */
	public void addDaiLogs(String cid, String ipaddr, String vtime, String ua) {
		String sql = "insert into ad_dai_log (cid,ipaddr,vtime,ua) values (?,?,?,?)";
		jdbcTemplate.update(sql,new Object[]{cid,ipaddr,vtime,ua});
	}

	/**
	 * 判断推送订单数据是否合法
	 * @param channel
	 * @param ipaddr
	 * @return
	 */
	public boolean isPassForPushOrd(String channel, String ipaddr) {
		String sql = "select id from ad_ord_push_rule where channel =? and ipaddr = ? and flag=1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]{channel,ipaddr});
		if( list != null && list.size() > 0 ){
			return true;
		}
		return false;
	}

	/**
	 * 添加在ok页面中点击红包或者下载apk包的记录
	 * @author qiulongjie
	 * @param channel
	 * @param clickType
	 * @param ipaddr
	 * @param vtime
	 */
	public void addOKClickLog(String channel, String clickType, String ipaddr, String vtime) {
		String sql = "insert into ad_ok_click_log(channel,click_type,ipaddr,vtime) values(?,?,?,?)";
		jdbcTemplate.update(sql,new Object[]{channel,clickType,ipaddr,vtime});
	}

	/**
	 * 获取当天有效订单数
	 * @param date 日期
	 * @return
	 *//*
	public Integer getDayEftOrdNum(String date){
		String eft_cnt_sql = " select count(1) eft_cnt "+
				" from ad_ord_log a "+
				" where flag=1 and a.vtime>='" + date+" 00:00:00'"+" and vtime<='"+date+" 23:59:59'";
		List<Map<String,Object>> eftCnt = jdbcTemplate.queryForList(eft_cnt_sql,new Object[]{});
		if (eftCnt != null && eftCnt.size() > 0) {
			if (eftCnt.get(0).get("eft_cnt") != null) {
				return Integer.valueOf(eftCnt.get(0).get("eft_cnt").toString());
			}
		}
		return 0;
	}*/
	
	/**
	 * 获取当天订单数(有效订单，传输订单等)
	 * @param date 日期
	 * @return
	 */
	public Integer getDayOrdNum(String startdate, String enddate, String sql){
		Integer count = jdbcTemplate.queryForObject(sql,new Object[]{startdate, enddate}, Integer.class);
		if (count != null) {
			return count;
		}
		return 0;
	}
}
