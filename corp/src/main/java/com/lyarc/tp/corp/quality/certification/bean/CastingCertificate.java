package com.lyarc.tp.corp.quality.certification.bean;

import java.util.Date;
import java.util.List;
/**
 * 铸件合格证
 * @author Administrator
 *
 */
public class CastingCertificate {
	
	private String id;
	
    private String contractId;

    private String materId;
    
    private String certificateId;

    private Integer status; 
    
    private String testPeople;
    
    private String checkedBy;
    
    private String approvedBy;

    private String wfInstId;

    private Date wfInstTime;

    private String wfInstRemark;

    private String audit;

    private Date createTime;

    private Date updateTime;

    private Date tmstamp;
    
    private String testItem;

    private String chemicalComponent;

    private String mechanicalProperty;

	public Object testItemObj;
	public Object testItema;
	public Object testItemb;

	public Object getTestItema() {
		return testItema;
	}

	public Object getTestItemb() {
		return testItemb;
	}

	public void setTestItema(Object testItema) {
		this.testItema = testItema;
	}

	public void setTestItemb(Object testItemb) {
		this.testItemb = testItemb;
	}

	public String getId() {
		return id;
	}

	public String getContractId() {
		return contractId;
	}

	public String getMaterId() {
		return materId;
	}

	public String getCertificateId() {
		return certificateId;
	}

	public Integer getStatus() {
		return status;
	}

	public String getTestPeople() {
		return testPeople;
	}

	public String getCheckedBy() {
		return checkedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public String getWfInstId() {
		return wfInstId;
	}

	public Date getWfInstTime() {
		return wfInstTime;
	}

	public String getWfInstRemark() {
		return wfInstRemark;
	}

	public String getAudit() {
		return audit;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Date getTmstamp() {
		return tmstamp;
	}

	public String getTestItem() {
		return testItem;
	}

	public String getChemicalComponent() {
		return chemicalComponent;
	}

	public String getMechanicalProperty() {
		return mechanicalProperty;
	}

	public Object getTestItemObj() {
		return testItemObj;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public void setMaterId(String materId) {
		this.materId = materId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setTestPeople(String testPeople) {
		this.testPeople = testPeople;
	}

	public void setCheckedBy(String checkedBy) {
		this.checkedBy = checkedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public void setWfInstId(String wfInstId) {
		this.wfInstId = wfInstId;
	}

	public void setWfInstTime(Date wfInstTime) {
		this.wfInstTime = wfInstTime;
	}

	public void setWfInstRemark(String wfInstRemark) {
		this.wfInstRemark = wfInstRemark;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setTmstamp(Date tmstamp) {
		this.tmstamp = tmstamp;
	}

	public void setTestItem(String testItem) {
		this.testItem = testItem;
	}

	public void setChemicalComponent(String chemicalComponent) {
		this.chemicalComponent = chemicalComponent;
	}

	public void setMechanicalProperty(String mechanicalProperty) {
		this.mechanicalProperty = mechanicalProperty;
	}

	public void setTestItemObj(Object testItemObj) {
		this.testItemObj = testItemObj;
	}

}