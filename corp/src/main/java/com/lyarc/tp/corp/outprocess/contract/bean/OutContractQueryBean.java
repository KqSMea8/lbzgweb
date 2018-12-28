package com.lyarc.tp.corp.outprocess.contract.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

public class OutContractQueryBean extends BaseQueryBean{
	
	//合同编号
	private String contractId;
	private String contractNo;
	//承揽人
	private String partB;
	private String providerName;
	//状态
	private Integer status;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTimeStart;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTimeEnd;
	
	public String getPartB() {
		return partB;
	}
	public void setPartB(String partB) {
		this.partB = partB;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

}
