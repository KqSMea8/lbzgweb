package com.lyarc.tp.corp.warehouse.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class InStockQueryBean extends BaseQueryBean {

    private String inStockId;

    //库存id
    private String storageId;
    
    private String operId;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeStart;

    public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeEnd;

    public String getInStockId() {
        return inStockId;
    }

    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public LocalDate getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(LocalDate createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public LocalDate getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(LocalDate createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
}