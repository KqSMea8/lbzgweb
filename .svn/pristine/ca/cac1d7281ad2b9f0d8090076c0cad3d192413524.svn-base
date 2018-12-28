package com.lyarc.tp.corp.warehouse.bean;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

public class StockBillQueryBean extends BaseQueryBean {

	//单号
	private String stockBillId;

    //库存id
    private String storageId;
    
    private String operId;

    private Integer status;
    
    private Integer operType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeStart;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeEnd;
    

	public String getStockBillId() {
		return stockBillId;
	}

	public void setStockBillId(String stockBillId) {
		this.stockBillId = stockBillId;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	 

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
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
}
