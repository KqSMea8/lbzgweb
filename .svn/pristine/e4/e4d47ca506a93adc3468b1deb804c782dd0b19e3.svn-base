package com.lyarc.tp.corp.warehouse.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class StockForm {

	@NotEmpty(message = "仓库代码不能为空")
	private String storageId;

	@NotEmpty(message = "物料编码不能为空")
	private String materId;

	@NotEmpty(message = "货位不能为空")
	private String placeId;

	private String materName;

	private String spec;

	private String model;

	private String unit;

	@NotEmpty(message = "批次不能为空")
	private String batch;

	private BigDecimal price;

	private BigDecimal quantity;

	private BigDecimal amount;

	@NotNull(message = "盘点状态不能为空")
	private Integer checkStatus;

	private BigDecimal checkQuantity;

	private String checkRemark;

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

	public String getBatch() {
		return batch;
	}

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

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId == null ? null : storageId.trim();
	}

	public String getMaterId() {
		return materId;
	}

	public void setMaterId(String materId) {
		this.materId = materId == null ? null : materId.trim();
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId == null ? null : placeId.trim();
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
		return "StockForm [storageId=" + storageId + ", materId=" + materId + ", placeId=" + placeId + ", materName="
				+ materName + ", spec=" + spec + ", model=" + model + ", unit=" + unit + ", batch=" + batch + ", price="
				+ price + ", quantity=" + quantity + ", amount=" + amount + ", checkStatus=" + checkStatus
				+ ", checkQuantity=" + checkQuantity + ", checkRemark=" + checkRemark + "]";
	}

}