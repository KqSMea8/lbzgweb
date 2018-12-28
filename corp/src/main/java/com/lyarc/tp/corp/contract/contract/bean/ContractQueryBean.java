package com.lyarc.tp.corp.contract.contract.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

public class ContractQueryBean extends BaseQueryBean {

    private String drawing;

    private String customerName;

    private String customerId;

    private String contractId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate signBeginDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate signEndDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeEnd;

    private String contractNo;

    private Integer creator;

    //是否外贸合同
    private Integer foreignFlag;

    private Integer status;
    
    private String keyword;

    public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = StringUtils.isEmpty(contractNo) ? null : contractNo;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = StringUtils.isEmpty(drawing) ? null :drawing;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName =  StringUtils.isEmpty(customerName) ? null :customerName;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = StringUtils.isEmpty(contractId) ? null :contractId;
    }

    public LocalDate getSignBeginDate() {
        return signBeginDate;
    }

    public void setSignBeginDate(LocalDate signBeginDate) {
        this.signBeginDate = signBeginDate;
    }

    public LocalDate getSignEndDate() {
        return signEndDate;
    }

    public void setSignEndDate(LocalDate signEndDate) {
        this.signEndDate = signEndDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = StringUtils.isEmpty(customerId) ? null :customerId;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getForeignFlag() {
        return foreignFlag;
    }

    public void setForeignFlag(Integer foreignFlag) {
        this.foreignFlag = foreignFlag;
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
