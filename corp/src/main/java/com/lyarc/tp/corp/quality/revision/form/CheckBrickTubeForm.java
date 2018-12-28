package com.lyarc.tp.corp.quality.revision.form;

import java.util.Date;
import java.util.List;

import com.lyarc.tp.corp.quality.revision.bean.CheckBrickTubeDetail;

public class CheckBrickTubeForm {
    
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
	    
	    private Integer quantity;
	    
	    List <CheckBrickTubeDetailForm> detailList;
	    
	    
    
	    public Integer getId() {
			return id;
		}

		public String getBrickTubeId() {
			return brickTubeId;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public void setBrickTubeId(String brickTubeId) {
			this.brickTubeId = brickTubeId;
		}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
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

	public String getLotMoisture() {
		return lotMoisture;
	}

	public void setLotMoisture(String lotMoisture) {
		this.lotMoisture = lotMoisture;
	}

	public List<CheckBrickTubeDetailForm> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<CheckBrickTubeDetailForm> detailList) {
		this.detailList = detailList;
	}

	
    
	
}