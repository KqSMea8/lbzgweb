package com.lyarc.tp.corp.purchase.provider.form;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class ProviderInquireForm {
	private Integer id;
	@NotNull(message = "供应商编码不能为空")
	private String remark;
	
	private String providerId;
	
	private String materId;
	
	private String materName;
	
	private String spec;
	
	private String model;
	
	private String unit;
	
	private String subType;
	
	private String applyToProductType;
	
	private BigDecimal price;
	

	private String deliveryDate;
	
	private String payType;
	
	private String linkMan;
	
	private String address;
	
	private String linkPhone;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date inquireTime;
	
	private Date createTime;
	
	private Date createTimeEnd;
	
	private Date updateTime;
	
	private Date tmStamp;

	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getApplyToProductType() {
		return applyToProductType;
	}

	public void setApplyToProductType(String applyToProductType) {
		this.applyToProductType = applyToProductType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public Date getInquireTime() {
		return inquireTime;
	}

	public void setInquireTime(Date inquireTime) {
		this.inquireTime = inquireTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
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
