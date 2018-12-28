package com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 外检返厂验收单
 */
public class InstrumentInspectionAcceptForm {
	private String externalApplyId;
	
	private String inspectionAcceptId;
	
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
    private Date operatorDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tmstamp;
    
    public String getExternalApplyId() {
		return externalApplyId;
	}

	public void setExternalApplyId(String externalApplyId) {
		this.externalApplyId = externalApplyId;
	}

	public String getInspectionAcceptId() {
		return inspectionAcceptId;
	}

	public void setInspectionAcceptId(String inspectionAcceptId) {
		this.inspectionAcceptId = inspectionAcceptId;
	}

	public Date getOperatorDate() {
		return operatorDate;
	}

	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
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