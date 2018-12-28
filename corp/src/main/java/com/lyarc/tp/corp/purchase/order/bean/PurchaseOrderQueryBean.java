package com.lyarc.tp.corp.purchase.order.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PurchaseOrderQueryBean extends BaseQueryBean {
    private String purchaseOrderId;

    private String subType;

    private String materName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeStart;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeEnd;

    private Integer department;

    private Integer status;

    private Integer[] statusIn;

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
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

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMaterName() {
        return materName;
    }

    public void setMaterName(String materName) {
        this.materName = materName;
    }

    public Integer[] getStatusIn() {
        return statusIn;
    }

    public void setStatusIn(Integer[] statusIn) {
        this.statusIn = statusIn;
    }
}