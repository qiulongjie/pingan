package com.zzcm.fourgad.service.ad;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zzcm.fourgad.entity.AddrBean;
import com.zzcm.fourgad.util.StringUtil;

/**
 * 手机号码段归属地查询
 * @author qiulongjie
 *
 */
@Component
public class PhoneService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static String query_sql = "select province,city from tb_phone_info where prefix_phone=?";
	private static String url = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=%s";
	/**
	 * 根据手机号码段获取地址
	 * @author qiulongjie
	 * @param phone 号码段 手机号码的前7位
	 * @return 省份不带“省”字  城市不带“市”字
	 */
	public AddrBean phone2Addr(String phone){
		if( phone == null || phone.trim().length() < 7 ){
			return new AddrBean();
		}
		if( phone.trim().length() > 7 ){
			phone = phone.trim().substring(0, 7);
		}
		List<Map<String, Object>> list = jdbcTemplate.queryForList(query_sql, new Object[]{phone});
		if( list != null && list.size() > 0 ){
			AddrBean addr = new AddrBean();
			addr.setCountry("中国");
			addr.setProvinceCode((String)list.get(0).get("province"));
			addr.setCity((String)list.get(0).get("city"));
			return addr;
		}
		return phone2AddrByNet(phone);
	}
	
	/**
	 * 通过网络API获取手机号码段归属地 <br>
	 * http://www.ip138.com:8080/search.asp?action=mobile&mobile=%s
	 * @author qiulongjie
	 * @param phoneNumber
	 * @return  省份不带“省”字  城市不带“市”字
	 */
	protected AddrBean phone2AddrByNet(String phoneNumber) {
		AddrBean bean = new AddrBean();
		try {
			Document doc = Jsoup.connect(String.format(url, phoneNumber)).get();
			Elements els = doc.getElementsByClass("tdc2");
			// System.out.println("归属地：" + els.get(1).text());
			// System.out.println("类型：" + els.get(2).text());
			// System.out.println("区号：" + els.get(3).text());
			// System.out.println("邮编：" + els.get(4).text().substring(0, 6));
			String addr = els.get(1).text();
			if (!StringUtil.isEmpty(addr)) {
				String[] addrs = addr.split(" ");
				if (addrs != null) {
					String province = "";
					String city = "";
					if (addrs.length >= 2) {
						province = addrs[0];
						city = addrs[1];
					} else if (addrs.length == 1) {
						province = addrs[0];
						city = addrs[0];
					}
					bean.setProvinceCode(province);
					bean.setCity(city.replace("市", ""));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
