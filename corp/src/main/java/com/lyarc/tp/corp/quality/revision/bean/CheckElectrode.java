package com.lyarc.tp.corp.quality.revision.bean;

import java.math.BigDecimal;
import java.util.Date;

public class CheckElectrode extends TcheckRevisionLog{
    private String batchId;

    private String ledgerId;

    private Integer quantity;
    
    private Integer versionId;

    private Integer standard;
    
    private String operator;

    private BigDecimal lengthSize;
    
    private Integer conclusion;

    private BigDecimal resistance;

    private Date createTime;

    private Date updateTime;

    private Date tmstamp;
    
    private String inspectionItem;
    
    private Object itemOneObj;
    
    

    public String getInspectionItem() {
		return inspectionItem;
	}

	public void setInspectionItem(String inspectionItem) {
		this.inspectionItem = inspectionItem;
	}

	public Object getItemOneObj() {
		return itemOneObj;
	}

	public void setItemOneObj(Object itemOneObj) {
		this.itemOneObj = itemOneObj;
	}

	public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
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

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public BigDecimal getLengthSize() {
        return lengthSize;
    }

    public void setLengthSize(BigDecimal lengthSize) {
        this.lengthSize = lengthSize;
    }

    public BigDecimal getResistance() {
        return resistance;
    }

    public void setResistance(BigDecimal resistance) {
        this.resistance = resistance;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getConclusion() {
		return conclusion;
	}

	public void setConclusion(Integer conclusion) {
		this.conclusion = conclusion;
	}
    
    
}