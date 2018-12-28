package com.lyarc.tp.corp.quality.revision.bean;

import java.util.Date;

public class CheckResinChromeOre extends TcheckRevisionLog{
	
	private Integer id;
	
    private String batchId;

    private String ledgerId;

    private Integer versionId;
    
    private Integer quantity;

    private Integer conclusion;
    
    private String operator;
    
    private String itemOne;
    
    private String itemTwo;

    private Date createTime;

    private Date updateTime;

    private Date tmstamp;
    
    private Object itemOneObj;
    
    private Object itemTwoObj;
    
    public Object getItemTwoObj() {
		return itemTwoObj;
	}

	public void setItemTwoObj(Object itemTwoObj) {
		this.itemTwoObj = itemTwoObj;
	}

	public String getItemTwo() {
		return itemTwo;
	}

	public void setItemTwo(String itemTwo) {
		this.itemTwo = itemTwo;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getItemOne() {
		return itemOne;
	}

	public void setItemOne(String itemOne) {
		this.itemOne = itemOne;
	}
    
    
}