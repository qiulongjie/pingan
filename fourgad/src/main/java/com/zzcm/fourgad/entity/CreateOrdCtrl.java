package com.zzcm.fourgad.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

//JPA标识
@Entity
@Table(name = "ad_crt_ord_ctrl")
public class CreateOrdCtrl extends IdEntity {

	private String ordTitle;
	private String ordKey;
	private Integer crtOrdStatus; //0,生成订单停止状态；1：生成订单启动状态

	// JSR303 BeanValidator的校验规则
	@NotBlank
	public String getOrdTitle() {
		return ordTitle;
	}

	public void setOrdTitle(String ordTitle) {
		this.ordTitle = ordTitle;
	}

	public Integer getCrtOrdStatus() {
		return crtOrdStatus;
	}

	public String getOrdKey() {
		return ordKey;
	}

	public void setOrdKey(String ordKey) {
		this.ordKey = ordKey;
	}

	public void setCrtOrdStatus(Integer crtOrdStatus) {
		this.crtOrdStatus = crtOrdStatus;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
