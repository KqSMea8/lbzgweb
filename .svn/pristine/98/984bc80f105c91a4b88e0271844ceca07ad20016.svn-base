package com.lyarc.tp.corp.contract.contract.form;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.lyarc.tp.corp.contract.inquire.form.InquireDetailForm;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@JGlobalMap(excluded ={"detailList"} )
public class ContractForm {
    private String contractId;

    private String contractNo;
    @NotNull(message = "客户id不能为空")
    private String customerId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate signDate;

    private String signAddress;

    private Integer hasProcedure;

    private Integer isForeign;

    private BigDecimal amount;
    @NotNull(message = "交货地点不能为空")
    private String deliveryAddress;

    private String sales;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate inDate;

    private String currency;

    private String files;

    private String remark;

    private Integer status;

    private String wfInstId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime wfInstTime;

    private String wfInstRemark;

    private Integer revisionStatus;

    private String revisionWfInstId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime revisionWfInstTime;

    private String revisionWfInstRemark;

    private Integer undoStatus;

    private String undoWfInstId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime undoWfInstTime;

    private String undoWfInstRemark;

    private Integer creator;
    

    private List<ContractDetailForm> detailList;

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

    public List<ContractDetailForm> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ContractDetailForm> detailList) {
        this.detailList = detailList;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
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
}