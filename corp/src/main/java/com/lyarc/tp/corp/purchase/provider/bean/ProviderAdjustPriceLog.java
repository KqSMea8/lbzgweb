package com.lyarc.tp.corp.purchase.provider.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.lyarc.tp.corp.common.bean.BaseQueryBean;

public class ProviderAdjustPriceLog extends BaseQueryBean{
	private Integer id;
	
	private String providerId;
	
	private String materId;
	
	private BigDecimal price;
	
	private String pmapId;
	
	private String remark;
	
	private Integer creator;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Date tmstamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getMaterId() {
		return materId;
	}

	public void setMaterId(String materId) {
		this.materId = materId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPmapId() {
		return pmapId;
	}

	public void setPmapId(String pmapId) {
		this.pmapId = pmapId;
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

	public Date getTmstamp() {
		return tmstamp;
	}

	public void setTmstamp(Date tmstamp) {
		this.tmstamp = tmstamp;
	}
	
	
}
