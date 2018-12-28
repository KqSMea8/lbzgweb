package com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 外检返厂验收单
 */
public class InstrumentInspectionAccept extends InstrumentInspectionAcceptKey {
	private int id;
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String instrumentId;

    private BigDecimal testAllCost;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date manufactureDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applianceReturnDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appraisalDate;

    private String operator;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tmstamp;
    
    List<InstrumentInspectionAcceptDetail> detailList;
    
  

	public List<InstrumentInspectionAcceptDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InstrumentInspectionAcceptDetail> detailList) {
		this.detailList = detailList;
	}

	public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId == null ? null : instrumentId.trim();
    }

    public BigDecimal getTestAllCost() {
        return testAllCost;
    }

    public void setTestAllCost(BigDecimal testAllCost) {
        this.testAllCost = testAllCost;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date date) {
        this.manufactureDate = date;
    }

    public Date getApplianceReturnDate() {
        return applianceReturnDate;
    }

    public void setApplianceReturnDate(Date applianceReturnDate) {
        this.applianceReturnDate = applianceReturnDate;
    }

    public Date getAppraisalDate() {
        return appraisalDate;
    }

    public void setAppraisalDate(Date appraisalDate) {
        this.appraisalDate = appraisalDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTmstamp() {
        return tmstamp;
    }

    public void setTmstamp(Date tmstamp) {
        this.tmstamp = tmstamp;
    }
}