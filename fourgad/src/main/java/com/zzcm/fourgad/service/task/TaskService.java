/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.zzcm.fourgad.service.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.zzcm.fourgad.entity.Task;
import com.zzcm.fourgad.repository.TaskDao;
import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.util.DateUtil;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class TaskService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private TaskDao taskDao;

	@Autowired
	private AdService adService;

	public Task getTask(Long id) {
		return taskDao.findOne(id);
	}

	public void saveTask(Task entity) {
		taskDao.save(entity);
	}

	public void deleteTask(Long id) {
		taskDao.delete(id);
	}

	public List<Task> getAllTask() {
		return (List<Task>) taskDao.findAll();
	}

	public Page<Task> getUserTask(Long userId,
			Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,
				sortType);
		Specification<Task> spec = buildSpecification(userId, searchParams);

		return taskDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Task> buildSpecification(Long userId,
			Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Task> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), Task.class);
		return spec;
	}

	@Autowired
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	/**
	 * 重置对应的任务开关
	 * 
	 * @author qiulongjie
	 */
	public void resetTaskList(String taskKey, Integer status) {
		taskList.put(taskKey, status == 0 ? false : true);
	}

	/**
	 * 获取任务钥匙对应的开关
	 * 
	 * @author qiulongjie
	 * @param taskKey
	 * @return
	 */
	public Boolean getTaskWork(String taskKey) {
		Boolean flag = taskList.get(taskKey);
		if (flag == null) {
			initTaskList();
			flag = taskList.get(taskKey);
		}
		if (null != flag && flag) {
			Integer dayMaxNum = maxNumList.get(taskKey);
			Integer dayTransMax = transMaxList.get(taskKey);
			if (null != dayTransMax || null != dayMaxNum) {
				Integer max = dayTransMax != null ? dayTransMax : dayMaxNum;
				Integer temp = adService.getDayOrdNum(DateUtil.getTodayDate() + " 00:00:00", DateUtil.getTodayDate()
						+ " 23:59:59", ordSqlList.get(taskKey));
				if (temp != null && temp >= max) {
					return false;
				}
			} 
			return true;
		}
		return false;
	}

	/**
	 * 初始化任务列表
	 * 
	 * @author qiulongjie
	 */
	public void initTaskList() {
		logger.warn("初始化任务列表...");
		List<Task> list = (List<Task>) taskDao.findAll();
		for (Task t : list) {
			taskList.put(t.getTaskKey(), t.getStatus() == 0 ? false : true);
			maxNumList.put(t.getTaskKey(), t.getDayMaxNum());
			transMaxList.put(t.getTaskKey(), t.getDayTransMax());
			ordSqlList.put(t.getTaskKey(), t.getOrdSql());
		}
	}

	/**
	 * 任务开关列表
	 */
	private static Map<String, Boolean> taskList = new HashMap<String, Boolean>();

	/**
	 * 每日有效订单阀值
	 */
	private static Map<String, Integer> maxNumList = new HashMap<String, Integer>();

	/**
	 * 每日传输订单阀值
	 */
	private static Map<String, Integer> transMaxList = new HashMap<String, Integer>();

	/**
	 * 订单sql
	 */
	private static Map<String, String> ordSqlList = new HashMap<String, String>();

}
