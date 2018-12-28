package com.lyarc.tp.corp.quality.InspectionSheet.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InspectionSheetDetail extends InspectionSheetDetailKey {

    private String inspectionApplyId;

    private String materName;

    private String spec;

    private String subType;

    private Integer level;

    private String unit;

    private BigDecimal quantity;

    private String uses;

    private String origin;

    private String buyer;
    
    private String providerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date madeDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;

    private BigDecimal price;

    private BigDecimal amount;

    private BigDecimal arrivalQuantity;

    private String originBatch;

    private String batch;

    private BigDecimal packageCount;

    private String vehicleNo;

    private BigDecimal roughWeight;

    private BigDecimal takeOutWeight;

    private BigDecimal packageWeight;

	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date validityDate;

    private String qualityTime;

    private String batchId;

    private Integer isEnterLedger;

    private String purchaseOrderId;

    private String inspector;

    private String analyst;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inspectionDate;

    private Date createTime;

    private Date updateTime;

    private Date tmstamp;

	public String getInspectionApplyId() {
		return inspectionApplyId;
	}

	public String getMaterName() {
		return materName;
	}

	public String getSpec() {
		return spec;
	}

	public String getSubType() {
		return subType;
	}

	public Integer getLevel() {
		return level;
	}

	public String getUnit() {
		return unit;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public String getUses() {
		return uses;
	}

	public String getOrigin() {
		return origin;
	}

	public String getBuyer() {
		return buyer;
	}

	public String getProviderId() {
		return providerId;
	}

	public Date getMadeDate() {
		return madeDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getArrivalQuantity() {
		return arrivalQuantity;
	}

	public String getOriginBatch() {
		return originBatch;
	}

	public String getBatch() {
		return batch;
	}

	public BigDecimal getPackageCount() {
		return packageCount;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public BigDecimal getRoughWeight() {
		return roughWeight;
	}

	public BigDecimal getTakeOutWeight() {
		return takeOutWeight;
	}

	public BigDecimal getPackageWeight() {
		return packageWeight;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public String getQualityTime() {
		return qualityTime;
	}

	public String getBatchId() {
		return batchId;
	}

	public Integer getIsEnterLedger() {
		return isEnterLedger;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public String getInspector() {
		return inspector;
	}

	public String getAnalyst() {
		return analyst;
	}

	public Date getInspectionDate() {
		return inspectionDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Date getTmstamp() {
		return tmstamp;
	}

	public void setInspectionApplyId(String inspectionApplyId) {
		this.inspectionApplyId = inspectionApplyId;
	}

	public void setMaterName(String materName) {
		this.materName = materName;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public void setUses(String uses) {
		this.uses = uses;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setArrivalQuantity(BigDecimal arrivalQuantity) {
		this.arrivalQuantity = arrivalQuantity;
	}

	public void setOriginBatch(String originBatch) {
		this.originBatch = originBatch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public void setPackageCount(BigDecimal packageCount) {
		this.packageCount = packageCount;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public void setRoughWeight(BigDecimal roughWeight) {
		this.roughWeight = roughWeight;
	}

	public void setTakeOutWeight(BigDecimal takeOutWeight) {
		this.takeOutWeight = takeOutWeight;
	}

	public void setPackageWeight(BigDecimal packageWeight) {
		this.packageWeight = packageWeight;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public void setQualityTime(String qualityTime) {
		this.qualityTime = qualityTime;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public void setIsEnterLedger(Integer isEnterLedger) {
		this.isEnterLedger = isEnterLedger;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public void setAnalyst(String analyst) {
		this.analyst = analyst;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setTmstamp(Date tmstamp) {
		this.tmstamp = tmstamp;
	} 
    
   

	

}