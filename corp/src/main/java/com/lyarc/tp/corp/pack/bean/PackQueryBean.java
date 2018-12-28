package com.lyarc.tp.corp.pack.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PackQueryBean {
	
	private String packId;
	private Integer status;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTimeStart;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTimeEnd;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPackId() {
		return packId;
	}
	public void setPackId(String packId) {
		this.packId = packId;
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
