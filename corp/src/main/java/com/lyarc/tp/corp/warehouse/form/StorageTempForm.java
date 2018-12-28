package com.lyarc.tp.corp.warehouse.form;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class StorageTempForm {
	private Integer id;
	@NotNull(message = "仓库代码不能为空")
	private String storageId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date collTimeStart;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date collTimeEnd1;
	
	private BigDecimal temp;
	
	private BigDecimal humi;
	
	private String remark;
	
	private Integer creator;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;
	
	private Date tmStamp;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Date getCollTimeStart() {
		return collTimeStart;
	}

	public void setCollTimeStart(Date collTimeStart) {
		this.collTimeStart = collTimeStart;
	}

	public Date getCollTimeEnd1() {
		return collTimeEnd1;
	}

	public void setCollTimeEnd1(Date collTimeEnd1) {
		this.collTimeEnd1 = collTimeEnd1;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public BigDecimal getTemp() {
		return temp;
	}

	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}

	public BigDecimal getHumi() {
		return humi;
	}

	public void setHumi(BigDecimal humi) {
		this.humi = humi;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getTmStamp() {
		return tmStamp;
	}

	public void setTmStamp(Date tmStamp) {
		this.tmStamp = tmStamp;
	}
	
}
