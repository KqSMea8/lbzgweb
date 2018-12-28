package com.lyarc.tp.corp.purchase.order.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

import java.time.LocalDate;
import java.util.Date;

public class InStockApplyQueryBean extends BaseQueryBean {
    private String inStockApplyId;

    private String purchaseOrderId;

    private Integer department;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createBeginTime;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createEndTime;

    private String storageId;
    
    private Integer status;
    
    private String buyer;
    
    public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

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

    public LocalDate getCreateBeginTime() {
        return createBeginTime;
    }

    public void setCreateBeginTime(LocalDate createBeginTime) {
        this.createBeginTime = createBeginTime;
    }

    public LocalDate getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(LocalDate createEndTime) {
        this.createEndTime = createEndTime;
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
	
    
}