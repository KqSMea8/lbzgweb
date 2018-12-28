package com.lyarc.tp.corp.quality.revision.bean;

import java.math.BigDecimal;
import java.util.Date;

public class CheckVMethodCoatingDetail {
    private Integer id;

    private String ledgerId;

    private Integer versionId;

    private String batchId;

    private String sample;

    private BigDecimal proportion;

    private BigDecimal baumeDegree;

    private BigDecimal suspensionProperty;

    private Integer quantity;

    private Integer conclusion;

    private Date createTime;

    private Date updateTime;

    private Date tmstamp;
    
    private String vMethodId;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId == null ? null : ledgerId.trim();
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample == null ? null : sample.trim();
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public BigDecimal getBaumeDegree() {
        return baumeDegree;
    }

    public void setBaumeDegree(BigDecimal baumeDegree) {
        this.baumeDegree = baumeDegree;
    }

    public BigDecimal getSuspensionProperty() {
        return suspensionProperty;
    }

    public void setSuspensionProperty(BigDecimal suspensionProperty) {
        this.suspensionProperty = suspensionProperty;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getConclusion() {
        return conclusion;
    }

    public void setConclusion(Integer conclusion) {
        this.conclusion = conclusion;
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

	public String getvMethodId() {
		return vMethodId;
	}

	public void setvMethodId(String vMethodId) {
		this.vMethodId = vMethodId;
	}

	
}