package com.lyarc.tp.corp.contract.inquire.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class InquireDetailForm {
	private Integer id;
	@NotNull(message = "询价单id不能为空")
	private String inquireId;

	@NotNull(message = "物料编码不能为空")
	private String materId;

	@NotNull(message = "图号不能为空")
	private String drawing;

	private String materName;

	private String texture;

	private String hardness;

	private String deliveryType;

	private String unit;

	private String quantity;

	private BigDecimal weight;

	private BigDecimal weightTotal;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate deliveryDate;

	private Integer creator;

	private String remark;

	private String fieldJson;

	private String files;

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInquireId() {
		return inquireId;
	}

	public void setInquireId(String inquireId) {
		this.inquireId = inquireId == null ? null : inquireId.trim();
	}

	public String getMaterId() {
		return materId;
	}

	public void setMaterId(String materId) {
		this.materId = materId == null ? null : materId.trim();
	}

	public String getDrawing() {
		return drawing;
	}

	public void setDrawing(String drawing) {
		this.drawing = drawing == null ? null : drawing.trim();
	}

	public String getMaterName() {
		return materName;
	}

	public void setMaterName(String materName) {
		this.materName = materName;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture == null ? null : texture.trim();
	}

	public String getHardness() {
		return hardness;
	}

	public void setHardness(String hardness) {
		this.hardness = hardness == null ? null : hardness.trim();
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType == null ? null : deliveryType.trim();
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity == null ? null : quantity.trim();
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getWeightTotal() {
		return weightTotal;
	}

	public void setWeightTotal(BigDecimal weightTotal) {
		this.weightTotal = weightTotal;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getFieldJson() {
		return fieldJson;
	}

	public void setFieldJson(String fieldJson) {
		this.fieldJson = fieldJson;
	}
}