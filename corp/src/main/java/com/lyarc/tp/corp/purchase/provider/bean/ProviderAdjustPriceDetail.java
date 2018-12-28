package com.lyarc.tp.corp.purchase.provider.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProviderAdjustPriceDetail {
	private String pmapId;
	
	private String materId;
	
	private String materName;
	
	private String spec;
	
	private String model;
	
	private String subType;
	
	private String use;
	
	private BigDecimal price;
	
	private BigDecimal afterPrice;
	
	private String remark;
	
	private Integer creator;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;
	
	private Date tmStamp;



	public String getPmapId() {
		return pmapId;
	}

	public void setPmapId(String pmapId) {
		this.pmapId = pmapId;
	}

	public String getMaterId() {
		return materId;
	}

	public void setMaterId(String materId) {
		this.materId = materId;
	}

	public String getMaterName() {
		return materName;
	}

	public void setMaterName(String materName) {
		this.materName = materName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAfterPrice() {
		return afterPrice;
	}

	public void setAfterPrice(BigDecimal afterPrice) {
		this.afterPrice = afterPrice;
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
