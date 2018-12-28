package com.lyarc.tp.corp.production.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

import java.time.LocalDate;
import java.util.Date;

public class OutStockApplyQueryBean extends BaseQueryBean {
    private String outStockApplyId;

    private Integer department;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createBeginTime;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createEndTime;

    private String storageId;
    
    private Integer status;
    
    public String getOutStockApplyId() {
        return outStockApplyId;
    }

    public void setOutStockApplyId(String outStockApplyId) {
        this.outStockApplyId = outStockApplyId == null ? null : outStockApplyId.trim();
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