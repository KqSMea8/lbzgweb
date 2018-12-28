package com.lyarc.tp.corp.quality.revision.bean;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

public class TcheckRevisionLogQueryBean extends BaseQueryBean{
	
	private String batchId;
	
	private Integer versionId;
    
	private String revisionId;
	
	private String analyst;
	
	private String inspector;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createBeginTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createEndTime;
    

    public String getAnalyst() {
		return analyst;
	}

	public String getInspector() {
		return inspector;
	}

	public void setAnalyst(String analyst) {
		this.analyst = analyst;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public LocalDate getCreateBeginTime() {
		return createBeginTime;
	}

	public void setCreateBeginTime(LocalDate createBeginTime) {
		this.createBeginTime = createBeginTime;
	}

	public LocalDate getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(LocalDate createEndTime) {
		this.createEndTime = createEndTime;
	}

	public String getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId == null ? null : revisionId.trim();
    }

    
}