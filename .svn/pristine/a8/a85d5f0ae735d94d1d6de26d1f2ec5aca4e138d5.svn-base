package com.lyarc.tp.corp.purchase.order.form;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.lyarc.tp.corp.purchase.order.dto.InStockApplyDetailDto;

import java.util.Date;
import java.util.List;

@JGlobalMap(excluded ={"detailList"} )
public class InStockApplyForm {
    private String inStockApplyId;

    private String purchaseOrderId;

    private Integer department;

    private String proposer;

    private String remark;

    private Integer creator;
    
    private String storageId;

	private Integer status;

	private String stockBillId;

	private String auditRemark;

    private List<InStockApplyDetailForm> detailList;

    public String getInStockApplyId() {
        return inStockApplyId;
    }

    public void setInStockApplyId(String inStockApplyId) {
        this.inStockApplyId = inStockApplyId == null ? null : inStockApplyId.trim();
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId == null ? null : purchaseOrderId.trim();
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer == null ? null : proposer.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public List<InStockApplyDetailForm> getDetailList() {
        return detailList;
    }
    
    
    
    public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStockBillId() {
		return stockBillId;
	}

	public void setStockBillId(String stockBillId) {
		this.stockBillId = stockBillId;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	public void setDetailList(List<InStockApplyDetailForm> detailList) {
        this.detailList = detailList;
    }
}