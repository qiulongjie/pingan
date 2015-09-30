package com.zzcm.fourgad.service.ad;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zzcm.fourgad.entity.AddrBean;
import com.zzcm.fourgad.service.task.CrtOrdService;

@Component
public class UpdateAddrService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PhoneService phoneService;
	@Autowired
	private LiqiService liqiService;
	@Autowired
	private NewMediaService newMediaService;
	@Autowired
	private CrtOrdService crtOrdService;

	public void updateAddr(int num){
		try {
			List<Map<String, Object>> list = getOrdsNoAddr(num);
			int flag=0;
			int ads=0;
			
			if(null != list && list.size() > 0){
				for(Map<String, Object> obj : list) {
					AddrBean addr = phoneService.phone2Addr(obj.get("phone").toString());
					String prov = addr.getProvinceCode();
					String city = addr.getCity();
					
					boolean isLiqi = liqiService.check(null,prov,city);
					
					if(isLiqi && crtOrdService.getOrdCtrl(CrtOrdService.LIQI_KEY)){
						// 如果是立其娱乐则把 flag设置为8
						flag = 8; 
						ads = 5;
					}else{
						// 判断是否要传给360新媒体的数据
						boolean isNewMedia = newMediaService.checkOrd(obj.get("pubcode").toString(),city);
						if(isNewMedia && crtOrdService.getOrdCtrl(CrtOrdService.NEWMEDIA_KEY)){
							// 如果是360新媒体则把 flag设置为8
							flag = 8;
							ads = 4;
						}else{
							flag = 0;
							ads = 1;
						}
					}
					
					updateOrdLog(Long.valueOf(obj.get("id").toString()), prov, city, flag, ads);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<Map<String,Object>> getOrdsNoAddr(int num) {
		String sql = "select id, phone, pubcode from ad_ord_log"
				+ "  where flag = 3 and ads = 1  limit 0,?";
		return jdbcTemplate.queryForList(sql,new Object[]{num});
	}
	
	private void updateOrdLog(Long id, String prov, String city, int flag, int ads){
		String sql = "update ad_ord_log set flag=?,ads=?,province=?,city=? where id=? ";
		jdbcTemplate.update(sql,new Object[]{flag, ads, prov, city, id});
	}
	
	
}

