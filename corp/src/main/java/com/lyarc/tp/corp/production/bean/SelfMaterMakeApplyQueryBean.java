package com.lyarc.tp.corp.production.bean;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

public class SelfMaterMakeApplyQueryBean extends BaseQueryBean {

	private String texture;

	private String materName;

	private String proposer;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createTimeStart;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createTimeEnd;

	private Integer status;

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public String getMaterName() {
		return materName;
	}

	public void setMaterName(String materName) {
		this.materName = materName;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
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