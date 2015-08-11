package com.zzcm.fourgad.service.ad;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.metlife.icare.webservice.YSW2ICareSaveServiceLocator;
import cn.com.metlife.icare.webservice.YSW2ICareSave_PortType;

import com.zzcm.fourgad.entity.AddrBean;
import com.zzcm.fourgad.entity.HolderIdentify;
import com.zzcm.fourgad.entity.Record;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.RecordXmlUtil;
import com.zzcm.fourgad.util.ValidUtil;

@Component
@Transactional
public class DdhService {
	@Autowired
    private IPService iPService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** 活动包含省份  */
	private static final String[] INCLUD_PROVINCE = {"北京市","上海市","重庆市","辽宁省","江苏省","浙江省","福建省","广东省","四川省","湖北省","天津市"};
	/** 不包含的城市 */
	private static final String[] EXCLUD_CITY = {"厦门"};

	/**
	 * 检查数据<br/>
	 *  1	姓名不合法，领取失败！<br/>
		2	生日格式错误
		3	年龄不符合要求，领取失败！<br/>
		4	手机号码格式错误<br/>
		5	手机号码已经存在，领取失败！<br/>
		6	本次活动不包含您所在的城市，领取失败！<br/>
	 * @param uname
	 * @param birthday
	 * @param ddlSex
	 * @param phone
	 * @param ipaddr 
	 * @return
	 */
	public Object[] checkDdhSumbit(String uname, String birthday, String ddlSex, String phone, String ipaddr) {
		if(!ValidUtil.isValidName(uname)){
			return new Object[]{1};
		}
		
		if(!ValidUtil.isBirthday(birthday)){
			return new Object[]{2};
		}
		
		if(!ValidUtil.isValidBirthday(birthday)){
			return new Object[]{3};
		}
		
		if(!ValidUtil.isPhoneNumber(phone)){
			return new Object[]{4};
		}
		
		if(isExsitPhone(phone)){
			return new Object[]{5};
		}
		
		//   判断IP对应的城市是否在包含在活动的城市内
		AddrBean IPAddr = iPService.getIPAddr2(ipaddr);
		String city = IPAddr.getCity();
		for(String c : EXCLUD_CITY){
			if(c.contains(city)){
				return new Object[]{6};
			}
		}
		String province = IPAddr.getProvinceCode();
		for(String p : INCLUD_PROVINCE){
			if(p.contains(province)){
				return new Object[]{0,province,city};
			}
		}
		
		return new Object[]{6};
	}
	
	/**
	 * 手机号码是否已经存在
	 * @param phone
	 * @return
	 */
	public boolean isExsitPhone(String phone){
	    String sql = "select 1 from ad_record_ddh where phone = ? ";
	    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,new Object[]{phone});
	    if( list != null && list.size() > 0 ){
	    	return true;
	    }
		return false;
	}

	/**
	 * 大都会数据提交
	 * @param channel
	 * @param uname
	 * @param birthday
	 * @param ddlSex
	 * @param phone
	 * @param ipaddr
	 * @param vtime
	 * @param isCheck
	 * @param result 
	 */
	public void addRecordDdh(String channel, String uname, String birthday, String ddlSex, String phone, String ipaddr,
			String vtime, String isCheck, Object[] result) {
		String sql = "insert into ad_record_ddh(channel,uname,birthday,ddlSex,phone,ipaddr,vtime,is_check,contact_state,contact_city,contact_address) values(?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[]{channel,uname,birthday,ddlSex,phone,ipaddr,vtime,isCheck,String.valueOf(result[1]),String.valueOf(result[2]),String.valueOf(result[1])+String.valueOf(result[2])});
	}

	/**
	 * 同步数据给大都会
	 * @param recordCount 同步总数
	 * @throws Exception 
	 */
	public void synData(int recordCount) throws Exception {
		String sql = "select * from ad_record_ddh where flag is null limit 0,?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,new Object[]{recordCount});
		for( Map<String, Object> map : list ){
			Record record = new Record("ZhangYue");
		    record.Name = String.valueOf(map.get("uname"));
		    record.Sex = String.valueOf(map.get("ddlsex"));
		    record.Birthday = DateUtil.changeDateFormat(String.valueOf(map.get("birthday")));
		    record.Mobile = String.valueOf(map.get("phone"));
		    record.ContactStateName = String.valueOf(map.get("contact_city"));
		    record.ContactCityName = String.valueOf(map.get("contact_city"));
		    record.ContactAddress = String.valueOf(map.get("contact_address"));
		    //record.PresentCode = "PC0000000123";
			String data = RecordXmlUtil.getXmlData(record);
			HolderIdentify holder = send(data);
			updateRecord(holder,Integer.valueOf(String.valueOf(map.get("id"))));
		}
	}
	
	/**
	 * 更新状态
	 * @param holder 
	 * @param id
	 */
	public void updateRecord(HolderIdentify holder,Integer id) {
		String sql = "update ad_record_ddh set flag = ? ,message=?,free_insure_no=? where id = ?";
		jdbcTemplate.update(sql, new Object[]{holder.Flag,holder.Message,holder.FreeInsureNo,id});
	}

	/**
	 * 发送数据给大都会
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public HolderIdentify send(String data) throws Exception{
		YSW2ICareSaveServiceLocator loc = new YSW2ICareSaveServiceLocator();
		//loc.setYSW2ICareSaveEndpointAddress("http://icare-uat.metlife.com.cn/services/YSW2ICareSave");// 测试地址
		loc.setYSW2ICareSaveEndpointAddress("http://icare.metlife.com.cn/services/YSW2ICareSave");
		YSW2ICareSave_PortType service = loc.getYSW2ICareSave();
		String res = service.doRequest(data);
		HolderIdentify holder = RecordXmlUtil.parserXmlResult(res);
		return holder;
	}

	/**
	 * 获取大都会跳转地址
	 * @param channel
	 * @param ip
	 * @return
	 */
	public String getPingUrlDdh(String channel, String ip) {
		AddrBean IPAddr = iPService.getIPAddr2(ip);
		String province = IPAddr.getProvinceCode();
		String sql = "select * from ad_province_redirect where channel = ? and province_name like ?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,new Object[]{channel,province+"%"});
		if(list != null && list.size() > 0){
			return String.valueOf(list.get(0).get("link_url"));
		}
		return "";
	}
}
