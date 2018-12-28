package com.lyarc.tp.corp.quality.revision.bean;

import java.math.BigDecimal;
import java.util.Date;

public class RevisedInspection {
	private String batchId;

    private String ledgerId;

    private Integer versionId;

    private String brickTubeName;
    
    private Integer stockQuantity;
    
    private Integer spotCheckQuantity;
    
    private String conclusion;
    
    private String analyst;
    
    private String inspector;
    
    private String auditor;
    
    private String quantity;
    
    private Date updateTime;
    
    private String origin;

    private BigDecimal yieid;
	
    private Date auditTime;

    public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(String ledgerId) {
		this.ledgerId = ledgerId;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getBrickTubeName() {
		return brickTubeName;
	}

	public void setBrickTubeName(String brickTubeName) {
		this.brickTubeName = brickTubeName;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Integer getSpotCheckQuantity() {
		return spotCheckQuantity;
	}

	public void setSpotCheckQuantity(Integer spotCheckQuantity) {
		this.spotCheckQuantity = spotCheckQuantity;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getAnalyst() {
		return analyst;
	}

	public void setAnalyst(String analyst) {
		this.analyst = analyst;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public BigDecimal getYieid() {
		return yieid;
	}

	public void setYieid(BigDecimal yieid) {
		this.yieid = yieid;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	
    
}
