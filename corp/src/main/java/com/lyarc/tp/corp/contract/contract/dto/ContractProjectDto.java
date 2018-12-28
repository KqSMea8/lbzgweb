package com.lyarc.tp.corp.contract.contract.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.contract.contract.bean.Contract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ContractProjectDto {
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

    private Integer shippingStatus;

    private String projectType;

    private String projectTypeName;

    private String designer;


    private String designerName;

    private BigDecimal quantity;

    private Integer proStatus;

    private String proWfInstId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime proWfInstTime;

    private String proWfInstRemark;

    private Integer proRevisionStatus;

    private String proRevisionWfInstId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime proRevisionWfInstTime;

    private String proRevisionWfInstRemark;

    private Integer creator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tmstamp;

    private List<ContractProjectDetailDto> projectDetailList;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer == null ? null : designer.trim();
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getProStatus() {
        return proStatus;
    }

    public void setProStatus(Integer proStatus) {
        this.proStatus = proStatus;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
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


    public String getProWfInstId() {
        return proWfInstId;
    }

    public void setProWfInstId(String proWfInstId) {
        this.proWfInstId = proWfInstId;
    }

    public LocalDateTime getProWfInstTime() {
        return proWfInstTime;
    }

    public void setProWfInstTime(LocalDateTime proWfInstTime) {
        this.proWfInstTime = proWfInstTime;
    }

    public String getProWfInstRemark() {
        return proWfInstRemark;
    }

    public void setProWfInstRemark(String proWfInstRemark) {
        this.proWfInstRemark = proWfInstRemark;
    }

    public Integer getProRevisionStatus() {
        return proRevisionStatus;
    }

    public void setProRevisionStatus(Integer proRevisionStatus) {
        this.proRevisionStatus = proRevisionStatus;
    }

    public String getProRevisionWfInstId() {
        return proRevisionWfInstId;
    }

    public void setProRevisionWfInstId(String proRevisionWfInstId) {
        this.proRevisionWfInstId = proRevisionWfInstId;
    }

    public LocalDateTime getProRevisionWfInstTime() {
        return proRevisionWfInstTime;
    }

    public void setProRevisionWfInstTime(LocalDateTime proRevisionWfInstTime) {
        this.proRevisionWfInstTime = proRevisionWfInstTime;
    }

    public String getProRevisionWfInstRemark() {
        return proRevisionWfInstRemark;
    }

    public void setProRevisionWfInstRemark(String proRevisionWfInstRemark) {
        this.proRevisionWfInstRemark = proRevisionWfInstRemark;
    }

    public List<ContractProjectDetailDto> getProjectDetailList() {
        return projectDetailList;
    }

    public void setProjectDetailList(List<ContractProjectDetailDto> projectDetailList) {
        this.projectDetailList = projectDetailList;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
        this.signAddress = signAddress;
    }

    public Integer getHasProcedure() {
        return hasProcedure;
    }

    public void setHasProcedure(Integer hasProcedure) {
        this.hasProcedure = hasProcedure;
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
        this.deliveryAddress = deliveryAddress;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }
}