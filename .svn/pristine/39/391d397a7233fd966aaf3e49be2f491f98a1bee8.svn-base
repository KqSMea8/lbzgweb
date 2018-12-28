package com.lyarc.tp.corp.contract.inquire.bean;

import com.lyarc.tp.corp.common.bean.BaseQueryBean;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

public class InquireQueryBean extends BaseQueryBean {
    private String customerName;

    private String customerId;

    private String inquireId;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer status;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInquireId() {
        return inquireId;
    }

    public void setInquireId(String inquireId) {
        this.inquireId = StringUtils.isEmpty(inquireId) ? null : inquireId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = StringUtils.isEmpty(customerId) ? null : customerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status =  status;
    }
}