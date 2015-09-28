/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.zzcm.fourgad.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

//JPA标识
@Entity
@Table(name = "ad_task")
public class Task extends IdEntity {

	private String title;
	private String description;
	private String taskKey;
	private Integer status = 0;
	private User user;
	private Integer dayMaxNum;
	private Integer dayTransMax;
	private String ordSql;

	// JSR303 BeanValidator的校验规则
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// JPA 基于USER_ID列的多对一关系定义
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDayMaxNum() {
		return dayMaxNum;
	}

	public void setDayMaxNum(Integer dayMaxNum) {
		this.dayMaxNum = dayMaxNum;
	}

	public Integer getDayTransMax() {
		return dayTransMax;
	}

	public void setDayTransMax(Integer dayTransMax) {
		this.dayTransMax = dayTransMax;
	}

	public String getOrdSql() {
		return ordSql;
	}

	public void setOrdSql(String ordSql) {
		this.ordSql = ordSql;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
