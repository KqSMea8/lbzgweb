package com.lyarc.tp.corp.quality.InspectionSheet.bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

public class InspectionSheet extends BaseQueryBean{
    private String inspectionId;

    private String inspectionApplyId;

    private String purchaseOrderId;

    private String inspector;

    private Date inspectionDate;

    private String analyst;
    
    private String remark;
    
    private String weightId;

    private Date createTime;

    private Date updateTime;

    private Date tmstamp;
    
    List<InspectionSheetDetail> detailList;
    private String materId;
    private String department;
    private String proposer;
    
    
    
    public String getDepartment() {
		return department;
	}

	public String getProposer() {
		return proposer;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getMaterId() {
		return materId;
	}

	public void setMaterId(String materId) {
		this.materId = materId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createBeginTime;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createEndTime;;
    		

	public LocalDate getCreateBeginTime() {
		return createBeginTime;
	}

	public LocalDate getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateBeginTime(LocalDate createBeginTime) {
		this.createBeginTime = createBeginTime;
	}

	public void setCreateEndTime(LocalDate createEndTime) {
		this.createEndTime = createEndTime;
	}

	public String getRemark() {
		return remark;
	}

	public String getWeightId() {
		return weightId;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setWeightId(String weightId) {
		this.weightId = weightId;
	}

	
    
    public List<InspectionSheetDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InspectionSheetDetail> detailList) {
		this.detailList = detailList;
	}

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId == null ? null : inspectionId.trim();
    }

    public String getInspectionApplyId() {
        return inspectionApplyId;
    }

    public void setInspectionApplyId(String inspectionApplyId) {
        this.inspectionApplyId = inspectionApplyId == null ? null : inspectionApplyId.trim();
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId == null ? null : purchaseOrderId.trim();
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector == null ? null : inspector.trim();
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst == null ? null : analyst.trim();
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
}