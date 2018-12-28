package com.lyarc.tp.corp.contract.inquire.form;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap(excluded = { "detailList" })
public class InquireForm {
	private String inquireId;

	@NotNull(message = "客户不能为空")
	private String customerId;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate inDate;

	private Integer isInquire;

	private Integer hasProcedure;

	private Integer isForeign;

	private Integer isCraftDesign;

	private String sales;

	private Integer creator;

	private String remark;

	private String designer;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate completeTime;

	private Integer status;

	private List<InquireDetailForm> detailList;

	public String getInquireId() {
		return inquireId;
	}

	public void setInquireId(String inquireId) {
		this.inquireId = inquireId == null ? null : inquireId.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public LocalDate getInDate() {
		return inDate;
	}

	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
	}

	public Integer getIsInquire() {
		return isInquire;
	}

	public void setIsInquire(Integer isInquire) {
		this.isInquire = isInquire;
	}

	public Integer getHasProcedure() {
		return hasProcedure;
	}

	public void setHasProcedure(Integer hasProcedure) {
		this.hasProcedure = hasProcedure;
	}

	public Integer getIsForeign() {
		return isForeign;
	}

	public void setIsForeign(Integer isForeign) {
		this.isForeign = isForeign;
	}

	public Integer getIsCraftDesign() {
		return isCraftDesign;
	}

	public void setIsCraftDesign(Integer isCraftDesign) {
		this.isCraftDesign = isCraftDesign;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales == null ? null : sales.trim();
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

	public List<InquireDetailForm> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InquireDetailForm> detailList) {
		this.detailList = detailList;
	}

	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	public LocalDate getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(LocalDate completeTime) {
		this.completeTime = completeTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}