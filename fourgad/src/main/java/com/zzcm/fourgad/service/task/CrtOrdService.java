package com.zzcm.fourgad.service.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zzcm.fourgad.entity.CreateOrdCtrl;
import com.zzcm.fourgad.service.ad.AdService;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class CrtOrdService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdService adService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static final String LIQI_KEY = "Liqi";
	public static final String NEWMEDIA_KEY = "NewMedia";

	/**
	 * 获取生成订单类型列表
	 * @return
	 */
	public List<CreateOrdCtrl> getCrtOrdCtrl() {	
		String sql = "select * from ad_crt_ord_ctrl";
		List<CreateOrdCtrl> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper(CreateOrdCtrl.class));
		return result;
	}
	
	/**
	 * 根据id获取订单类型
	 * @param id
	 * @return
	 */
	public CreateOrdCtrl getCrtOrdCtrlById(long id) {
		String sql = "select * from ad_crt_ord_ctrl where id="+id;
		CreateOrdCtrl  createOrdCtrl = (CreateOrdCtrl) jdbcTemplate.query(sql,new BeanPropertyRowMapper(CreateOrdCtrl.class)).get(0);
		return createOrdCtrl;
	}

	/**
	 * 插入一条订单类型
	 * @param title
	 * @param taskKey
	 */
	public void saveCrtOrdCtrl(String title, String taskKey) {
		String sql = "insert ad_crt_ord_ctrl(ord_title, ord_key) values(?,?)";
		jdbcTemplate.update(sql, new Object[]{title, taskKey});
	}
	
	/**
	 * 更新订单类型
	 * @param title
	 * @param taskKey
	 * @param id
	 */
	public void updateCrtOrdCtrl(String title, String taskKey, Long id) {
		String sql = "update ad_crt_ord_ctrl set ord_title=?,ord_key=? where id=? ";
		jdbcTemplate.update(sql, new Object[]{title, taskKey, id});
	}
	
	/**
	 * 修改生成订单的状态
	 * @param crtOrdStatus
	 * @param id
	 */
	public void updateStatus(Integer crtOrdStatus, Long id) {
		String sql = "update ad_crt_ord_ctrl set crt_ord_status=? where id=? ";
		jdbcTemplate.update(sql, new Object[]{crtOrdStatus, id});
	}
	
	/**
	 * 删除订单类型
	 * @param id
	 */
	public void deleteCrtOrdCtrl(Long id) {
		String sql = "delete from ad_crt_ord_ctrl where id=" + id;
		jdbcTemplate.update(sql);
	}
	
	/**
	 * 重置订单类型列表
	 * @param taskKey
	 * @param status
	 */
	public void resetCrtOrdList(String taskKey, Integer status) {
		crtOrdList.put(taskKey, status == 0 ? false : true);
	}

	/**
	 * 获取订单类型对应的开关
	 * @param taskKey
	 * @return
	 */
	public Boolean getOrdCtrl(String ordKey) {
		Boolean flag = crtOrdList.get(ordKey);
		if (flag == null) {
			initOrdCtrlList();
			flag = crtOrdList.get(ordKey);
		}
		return flag == null ? false : flag;
	}

	/**
	 * 初始化订单类型开关列表
	 * 
	 * @author wancan
	 */
	public void initOrdCtrlList() {
		logger.warn("初始化订单类型开关列表...");
		List<CreateOrdCtrl> list = getCrtOrdCtrl();
		for (CreateOrdCtrl t : list) {
			crtOrdList.put(t.getOrdKey(), t.getCrtOrdStatus() == 0 ? false : true);
		}
	}

	/**
	 * 订单类型开关列表
	 */
	private static Map<String, Boolean> crtOrdList = new HashMap<String, Boolean>();
}
