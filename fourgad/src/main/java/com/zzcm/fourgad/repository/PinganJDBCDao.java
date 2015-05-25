package com.zzcm.fourgad.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
/*
 * id bigint auto_increment,
	channel varchar(20) not null,
	ipaddr varchar(15) not null,	
	prov varchar(10),
	vtime varchar(20),
	ua varchar(20),
	vstr1 varchar(20)
	
	uname	varchar(6) not null,
	birthday varchar(8) not null,
	ddlSex varchar(2) not null,
	phone	varchar(14) not null,
	ipaddr varchar(15) not null,
	prov varchar(10),
	vtime varchar(20),
 */

public class PinganJDBCDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void AddReqLogs(String channel,String ipaddr,String prov,String vtime ){
		String sql = "insert into ad_req_log (channel,ipaddr,prov,vtime) values (?,?,?,?)";
		Object o [] = {channel,ipaddr,prov,vtime};
		jdbcTemplate.update(sql,o);
	}
	
	public void AddOrdLogs(String uname,String birthday,String ddlSex,String phone,String ipaddr,String prov,String vtime){
		String sql = "insert into ad_ord_log (uname,birthday,ddlSex,phone,ipaddr,prov,vtime) values (?,?,?,?,?,?,?)";
		Object o [] = {uname,birthday,ddlSex,phone,ipaddr,prov,vtime};
		jdbcTemplate.update(sql,o);
	}
	
	public void AddOrdLogs(String uname,String birthday,String ddlSex,String phone,String ipaddr,String prov,String vtime,String pcontent){
		String sql = "insert into ad_ord_log (uname,birthday,ddlSex,phone,ipaddr,prov,vtime) values (?,?,?,?,?,?,?,?)";
		Object o [] = {uname,birthday,ddlSex,phone,ipaddr,prov,vtime,pcontent};
		jdbcTemplate.update(sql,o);
	}
}
