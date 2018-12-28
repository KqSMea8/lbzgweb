package com.lyarc.tp.corp.contract.contract.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContractProjectDetailDto {
    private Integer id;

    private String contractId;

//    private ContractDetailDto contractDetailDto;

    private String fieldJson;

    private String files;

    private Integer creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tmstamp;


    private String materId;

    private String drawing;

    private String materName;

    private String texture;

    private String hardness;

    private String deliveryType;

    private String unit;

    private BigDecimal quantity;

    private BigDecimal weight;

    private BigDecimal weightTotal;

    private BigDecimal workblank;

    private BigDecimal workblankFee;

    private BigDecimal processFee;

    private BigDecimal packingFee;

    private BigDecimal freightFee;

    private BigDecimal portFee;

    private BigDecimal modelFee;

    private BigDecimal amount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public String getFieldJson() {
        return fieldJson;
    }

    public void setFieldJson(String fieldJson) {
        this.fieldJson = fieldJson == null ? null : fieldJson.trim();
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files == null ? null : files.trim();
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

    public String getMaterId() {
        return materId;
    }

    public void setMaterId(String materId) {
        this.materId = materId;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    public String getMaterName() {
        return materName;
    }

    public void setMaterName(String materName) {
        this.materName = materName;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getWeightTotal() {
        return weightTotal;
    }

    public void setWeightTotal(BigDecimal weightTotal) {
        this.weightTotal = weightTotal;
    }

    public BigDecimal getWorkblank() {
        return workblank;
    }

    public void setWorkblank(BigDecimal workblank) {
        this.workblank = workblank;
    }

    public BigDecimal getWorkblankFee() {
        return workblankFee;
    }

    public void setWorkblankFee(BigDecimal workblankFee) {
        this.workblankFee = workblankFee;
    }

    public BigDecimal getProcessFee() {
        return processFee;
    }

    public void setProcessFee(BigDecimal processFee) {
        this.processFee = processFee;
    }

    public BigDecimal getPackingFee() {
        return packingFee;
    }

    public void setPackingFee(BigDecimal packingFee) {
        this.packingFee = packingFee;
    }

    public BigDecimal getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(BigDecimal freightFee) {
        this.freightFee = freightFee;
    }

    public BigDecimal getPortFee() {
        return portFee;
    }

    public void setPortFee(BigDecimal portFee) {
        this.portFee = portFee;
    }

    public BigDecimal getModelFee() {
        return modelFee;
    }

    public void setModelFee(BigDecimal modelFee) {
        this.modelFee = modelFee;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}