package com.lyarc.tp.corp.warehouse.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Stock extends StockKey {
	private String materName;

	private String spec;

	private String model;

	private String unit;

	private String batch;

	private BigDecimal price;

	private BigDecimal quantity;

	private BigDecimal amount;

	private Date createTime;

	private Date updateTime;

	private Date tmstamp;

	private Integer checkStatus; // 盘点状态

	private BigDecimal checkQuantity;// 盘点数量

	private String checkRemark;// 盘点备注

	public String getMaterName() {
		return materName;
	}

	public void setMaterName(String materName) {
		this.materName = materName == null ? null : materName.trim();
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec == null ? null : spec.trim();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model == null ? null : model.trim();
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	@Override
	public String getBatch() {
		return batch;
	}

	@Override
	public void setBatch(String batch) {
		this.batch = batch == null ? null : batch.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public BigDecimal getCheckQuantity() {
		return checkQuantity;
	}

	public void setCheckQuantity(BigDecimal checkQuantity) {
		this.checkQuantity = checkQuantity;
	}

	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	@Override
	public String toString() {
		return "Stock [materName=" + materName + ", spec=" + spec + ", model=" + model + ", unit=" + unit + ", batch="
				+ batch + ", price=" + price + ", quantity=" + quantity + ", amount=" + amount + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", tmstamp=" + tmstamp + ", checkStatus=" + checkStatus
				+ ", checkQuantity=" + checkQuantity + ", checkRemark=" + checkRemark + "]";
	}

}