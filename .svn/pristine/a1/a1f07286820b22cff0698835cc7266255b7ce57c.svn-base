package com.lyarc.tp.corp.contract.contract.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@JGlobalMap(excluded ={"customerName","salesName","currencyName","detailList"} )
public class ContractDto {
    private String contractId;

    private String contractNo;

    private String customerId;

    private String customerName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate signDate;

    private String signAddress;

    private Integer hasProcedure;

    private Integer isForeign;

    private BigDecimal amount;

    private String deliveryAddress;

    private String sales;

    private String salesName;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate inDate;

    private String currency;

    private String currencyName;

    private String files;

    private String remark;

    private Integer status;

    private String wfInstId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime wfInstTime;

    private String wfInstRemark;

    private Integer revisionStatus;

    private String revisionWfInstId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime revisionWfInstTime;

    private String revisionWfInstRemark;

    private Integer undoStatus;

    private String undoWfInstId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime undoWfInstTime;

    private String undoWfInstRemark;

    private Integer creator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tmstamp;



    private List<ContractDetailDto> detailList;


    private Integer shippingStatus;

    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }


    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public String getSignAddress() {
        return signAddress;
    }

    public void setSignAddress(String signAddress) {
        this.signAddress = signAddress == null ? null : signAddress.trim();
    }

    public Integer getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(Integer isForeign) {
        this.isForeign = isForeign;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales == null ? null : sales.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files == null ? null : files.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWfInstId() {
        return wfInstId;
    }

    public void setWfInstId(String wfInstId) {
        this.wfInstId = wfInstId == null ? null : wfInstId.trim();
    }


    public Integer getRevisionStatus() {
        return revisionStatus;
    }

    public void setRevisionStatus(Integer revisionStatus) {
        this.revisionStatus = revisionStatus;
    }

    public String getRevisionWfInstId() {
        return revisionWfInstId;
    }

    public void setRevisionWfInstId(String revisionWfInstId) {
        this.revisionWfInstId = revisionWfInstId == null ? null : revisionWfInstId.trim();
    }


    public Integer getUndoStatus() {
        return undoStatus;
    }

    public void setUndoStatus(Integer undoStatus) {
        this.undoStatus = undoStatus;
    }

    public String getUndoWfInstId() {
        return undoWfInstId;
    }

    public void setUndoWfInstId(String undoWfInstId) {
        this.undoWfInstId = undoWfInstId == null ? null : undoWfInstId.trim();
    }


    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public List<ContractDetailDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ContractDetailDto> detailList) {
        this.detailList = detailList;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public LocalDateTime getWfInstTime() {
        return wfInstTime;
    }

    public void setWfInstTime(LocalDateTime wfInstTime) {
        this.wfInstTime = wfInstTime;
    }

    public LocalDateTime getRevisionWfInstTime() {
        return revisionWfInstTime;
    }

    public void setRevisionWfInstTime(LocalDateTime revisionWfInstTime) {
        this.revisionWfInstTime = revisionWfInstTime;
    }

    public LocalDateTime getUndoWfInstTime() {
        return undoWfInstTime;
    }

    public void setUndoWfInstTime(LocalDateTime undoWfInstTime) {
        this.undoWfInstTime = undoWfInstTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getTmstamp() {
        return tmstamp;
    }

    public void setTmstamp(LocalDateTime tmstamp) {
        this.tmstamp = tmstamp;
    }

    public Integer getHasProcedure() {
        return hasProcedure;
    }

    public void setHasProcedure(Integer hasProcedure) {
        this.hasProcedure = hasProcedure;
    }

    public String getWfInstRemark() {
        return wfInstRemark;
    }

    public void setWfInstRemark(String wfInstRemark) {
        this.wfInstRemark = wfInstRemark;
    }

    public String getRevisionWfInstRemark() {
        return revisionWfInstRemark;
    }

    public void setRevisionWfInstRemark(String revisionWfInstRemark) {
        this.revisionWfInstRemark = revisionWfInstRemark;
    }

    public String getUndoWfInstRemark() {
        return undoWfInstRemark;
    }

    public void setUndoWfInstRemark(String undoWfInstRemark) {
        this.undoWfInstRemark = undoWfInstRemark;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}