package com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpbEightDReport {
    private String fileId;

    private String unqualifiedId;

    private String notConformReport;

    private String typeName;

    private String rejectId;
    
    private String specialRequest;

    private String oneD;
    
    private String twoD;
    
    private String threeD;
    
    private String fourD;
    
    private String fiveD;
    
    private String sixD;
    
    private String sevenD;
    
    private String eightD;
    
    private String nineD;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private Date updateTime;

    private Date tmstamp;

    public String getSpecialRequest() {
		return specialRequest;
	}

	public String getOneD() {
		return oneD;
	}

	public String getTwoD() {
		return twoD;
	}

	public String getThreeD() {
		return threeD;
	}

	public String getFourD() {
		return fourD;
	}

	public String getFiveD() {
		return fiveD;
	}

	public String getSixD() {
		return sixD;
	}

	public String getSevenD() {
		return sevenD;
	}

	public String getEightD() {
		return eightD;
	}

	public String getNineD() {
		return nineD;
	}

	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}

	public void setOneD(String oneD) {
		this.oneD = oneD;
	}

	public void setTwoD(String twoD) {
		this.twoD = twoD;
	}

	public void setThreeD(String threeD) {
		this.threeD = threeD;
	}

	public void setFourD(String fourD) {
		this.fourD = fourD;
	}

	public void setFiveD(String fiveD) {
		this.fiveD = fiveD;
	}

	public void setSixD(String sixD) {
		this.sixD = sixD;
	}

	public void setSevenD(String sevenD) {
		this.sevenD = sevenD;
	}

	public void setEightD(String eightD) {
		this.eightD = eightD;
	}

	public void setNineD(String nineD) {
		this.nineD = nineD;
	}

	public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getUnqualifiedId() {
        return unqualifiedId;
    }

    public void setUnqualifiedId(String unqualifiedId) {
        this.unqualifiedId = unqualifiedId == null ? null : unqualifiedId.trim();
    }

    public String getNotConformReport() {
        return notConformReport;
    }

    public void setNotConformReport(String notConformReport) {
        this.notConformReport = notConformReport;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getRejectId() {
        return rejectId;
    }

    public void setRejectId(String rejectId) {
        this.rejectId = rejectId == null ? null : rejectId.trim();
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