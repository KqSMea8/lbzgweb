package com.lyarc.tp.corp.purchase.provider.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;
import com.lyarc.tp.corp.purchase.provider.form.ProviderAdjustPriceDetailForm;

@JGlobalMap(excluded = { "detailList" })
public class ProviderAdjustPriceDto {
private List<ProviderAdjustPriceDetailDto> detailList;
	
	private String providerName;

	private String pmapId;
	
	private String providerId;
	
	private String sales;
	
	private Integer status;
	
	private String wfInstId;
	
	private Date wfInstTime;
	
	private String wfInstRemark;
	
	private Integer creator;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;
	
	private Date tmStamp;

	public List<ProviderAdjustPriceDetailDto> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ProviderAdjustPriceDetailDto> detailList) {
		this.detailList = detailList;
	}

	public String getPmapId() {
		return pmapId;
	}

	public void setPmapId(String pmapId) {
		this.pmapId = pmapId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getWfInstId() {
		return wfInstId;
	}

	public void setWfInstId(String wfInstId) {
		this.wfInstId = wfInstId;
	}

	public Date getWfInstTime() {
		return wfInstTime;
	}

	public void setWfInstTime(Date wfInstTime) {
		this.wfInstTime = wfInstTime;
	}

	public String getWfInstRemark() {
		return wfInstRemark;
	}

	public void setWfInstRemark(String wfInstRemark) {
		this.wfInstRemark = wfInstRemark;
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

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
	
}
