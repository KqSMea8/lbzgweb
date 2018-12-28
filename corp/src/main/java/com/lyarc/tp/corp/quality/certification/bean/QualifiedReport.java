package com.lyarc.tp.corp.quality.certification.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

public class QualifiedReport extends BaseQueryBean{
    private String id;
    
    private String contractId;

    private String certificateId;

    private String partSizeId;

    private String magneticId;

    private String ultrasonicId;

    private String customerId;

    private String customerName;

    private Integer isForeign;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private Date updateTime;

    private Date tmstamp;
    
    private String testItem;
    private String casting;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createBeginTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createEndTime;
    
    private String drawing;

    List<QualifiedReport> detailList;
	
	List<CastingCertificate> detailListC;
	
	List<MagneticPowderInspection> detailListM;
	
	List<PartSize> detailListP;
	
	List<UltrasonicTesting> detailListU;

	public String getId() {
		return id;
	}

	public String getContractId() {
		return contractId;
	}

	public String getCertificateId() {
		return certificateId;
	}

	public String getPartSizeId() {
		return partSizeId;
	}

	public String getMagneticId() {
		return magneticId;
	}

	public String getUltrasonicId() {
		return ultrasonicId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Integer getIsForeign() {
		return isForeign;
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

	public String getCasting() {
		return casting;
	}

	public Date getCreateBeginTime() {
		return createBeginTime;
	}

	public Date getCreateEndTime() {
		return createEndTime;
	}

	public String getDrawing() {
		return drawing;
	}

	public List<QualifiedReport> getDetailList() {
		return detailList;
	}

	public List<CastingCertificate> getDetailListC() {
		return detailListC;
	}

	public List<MagneticPowderInspection> getDetailListM() {
		return detailListM;
	}

	public List<PartSize> getDetailListP() {
		return detailListP;
	}

	public List<UltrasonicTesting> getDetailListU() {
		return detailListU;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	public void setPartSizeId(String partSizeId) {
		this.partSizeId = partSizeId;
	}

	public void setMagneticId(String magneticId) {
		this.magneticId = magneticId;
	}

	public void setUltrasonicId(String ultrasonicId) {
		this.ultrasonicId = ultrasonicId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setIsForeign(Integer isForeign) {
		this.isForeign = isForeign;
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

	public void setCasting(String casting) {
		this.casting = casting;
	}

	public void setCreateBeginTime(Date createBeginTime) {
		this.createBeginTime = createBeginTime;
	}

	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}

	public void setDrawing(String drawing) {
		this.drawing = drawing;
	}

	public void setDetailList(List<QualifiedReport> detailList) {
		this.detailList = detailList;
	}

	public void setDetailListC(List<CastingCertificate> detailListC) {
		this.detailListC = detailListC;
	}

	public void setDetailListM(List<MagneticPowderInspection> detailListM) {
		this.detailListM = detailListM;
	}

	public void setDetailListP(List<PartSize> detailListP) {
		this.detailListP = detailListP;
	}

	public void setDetailListU(List<UltrasonicTesting> detailListU) {
		this.detailListU = detailListU;
	}
	
	
	
}