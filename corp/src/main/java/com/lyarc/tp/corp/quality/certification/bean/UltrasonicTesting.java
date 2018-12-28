package com.lyarc.tp.corp.quality.certification.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UltrasonicTesting {
	private String certificateId;

	private String ultrasonicId;

	private String id;

	private String contractId;

	private String reportId;

	private String description;

	private String reportNo;

	private String orderNo;

	private String partNo;

	private Integer status;
	private Integer statu;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date reportDate;

	private String drawingNo;

	private String commissionUnit;

	private String quantity;

	private String material;

	private String typeOf;

	private String sensitivity;

	private String requiredStandard;

	private String typeOfThe;

	private String typeOfCoupling;

	private String surfaceCondition;

	private String typeOfMachine;

	private String size;

	private String frequency;

	private String adjustingField;

	private String angle;

	private String coupling;

	private String remark;

	private String testPeople;

	private String checkedBy;

	private String approvedBy;

	private String wfInstId;

	private Date wfInstTime;

	private String wfInstRemark;

	private String audit;

	private Date createTime;

	private Date updateTime;

	private Date tmstamp;

	private String sketchPart;

	private String ultrasonicTestResult;

	private Object sketchPartObj;
	private Object ultrasonicTestResultObj;
	public String getCertificateId() {
		return certificateId;
	}
	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}
	public String getUltrasonicId() {
		return ultrasonicId;
	}
	public void setUltrasonicId(String ultrasonicId) {
		this.ultrasonicId = ultrasonicId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatu() {
		return statu;
	}
	public void setStatu(Integer statu) {
		this.statu = statu;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getDrawingNo() {
		return drawingNo;
	}
	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}
	public String getCommissionUnit() {
		return commissionUnit;
	}
	public void setCommissionUnit(String commissionUnit) {
		this.commissionUnit = commissionUnit;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getTypeOf() {
		return typeOf;
	}
	public void setTypeOf(String typeOf) {
		this.typeOf = typeOf;
	}
	public String getSensitivity() {
		return sensitivity;
	}
	public void setSensitivity(String sensitivity) {
		this.sensitivity = sensitivity;
	}
	public String getRequiredStandard() {
		return requiredStandard;
	}
	public void setRequiredStandard(String requiredStandard) {
		this.requiredStandard = requiredStandard;
	}
	public String getTypeOfThe() {
		return typeOfThe;
	}
	public void setTypeOfThe(String typeOfThe) {
		this.typeOfThe = typeOfThe;
	}
	public String getTypeOfCoupling() {
		return typeOfCoupling;
	}
	public void setTypeOfCoupling(String typeOfCoupling) {
		this.typeOfCoupling = typeOfCoupling;
	}
	public String getSurfaceCondition() {
		return surfaceCondition;
	}
	public void setSurfaceCondition(String surfaceCondition) {
		this.surfaceCondition = surfaceCondition;
	}
	public String getTypeOfMachine() {
		return typeOfMachine;
	}
	public void setTypeOfMachine(String typeOfMachine) {
		this.typeOfMachine = typeOfMachine;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getAdjustingField() {
		return adjustingField;
	}
	public void setAdjustingField(String adjustingField) {
		this.adjustingField = adjustingField;
	}
	public String getAngle() {
		return angle;
	}
	public void setAngle(String angle) {
		this.angle = angle;
	}
	public String getCoupling() {
		return coupling;
	}
	public void setCoupling(String coupling) {
		this.coupling = coupling;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTestPeople() {
		return testPeople;
	}
	public void setTestPeople(String testPeople) {
		this.testPeople = testPeople;
	}
	public String getCheckedBy() {
		return checkedBy;
	}
	public void setCheckedBy(String checkedBy) {
		this.checkedBy = checkedBy;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
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
	public String getAudit() {
		return audit;
	}
	public void setAudit(String audit) {
		this.audit = audit;
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
	public String getSketchPart() {
		return sketchPart;
	}
	public void setSketchPart(String sketchPart) {
		this.sketchPart = sketchPart;
	}
	public String getUltrasonicTestResult() {
		return ultrasonicTestResult;
	}
	public void setUltrasonicTestResult(String ultrasonicTestResult) {
		this.ultrasonicTestResult = ultrasonicTestResult;
	}
	public Object getSketchPartObj() {
		return sketchPartObj;
	}
	public void setSketchPartObj(Object sketchPartObj) {
		this.sketchPartObj = sketchPartObj;
	}
	public Object getUltrasonicTestResultObj() {
		return ultrasonicTestResultObj;
	}
	public void setUltrasonicTestResultObj(Object ultrasonicTestResultObj) {
		this.ultrasonicTestResultObj = ultrasonicTestResultObj;
	}

}