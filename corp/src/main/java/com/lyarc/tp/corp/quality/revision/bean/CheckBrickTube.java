package com.lyarc.tp.corp.quality.revision.bean;

import java.util.Date;
import java.util.List;

public class CheckBrickTube extends TcheckRevisionLog{
    private Integer id;

    private String ledgerId;

    private String brickTubeId;

    private String batchId;

    private Integer versionId;

    private String conclusion;

    private String lotMoisture;

    private Date createTime;

    private Date updateTime;

    private Date tmstamp;

    private String operator;
    
    List <CheckBrickTubeDetail> detailList;

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

    public String getBrickTubeId() {
        return brickTubeId;
    }

    public void setBrickTubeId(String brickTubeId) {
        this.brickTubeId = brickTubeId == null ? null : brickTubeId.trim();
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion == null ? null : conclusion.trim();
    }

    public String getLotMoisture() {
        return lotMoisture;
    }

    public void setLotMoisture(String lotMoisture) {
        this.lotMoisture = lotMoisture == null ? null : lotMoisture.trim();
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

	public List<CheckBrickTubeDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<CheckBrickTubeDetail> detailList) {
		this.detailList = detailList;
	}
    
    
}