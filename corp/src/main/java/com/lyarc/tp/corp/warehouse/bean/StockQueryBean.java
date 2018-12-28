package com.lyarc.tp.corp.warehouse.bean;

import com.lyarc.tp.corp.common.bean.BaseQueryBean;

public class StockQueryBean extends BaseQueryBean {

	private String storageId;

	private String materId;

	private String placeId;
	
	private String placeName;

	private String batch;// 批次

	private String keyword;

	private Integer checkStatus;// 盘点状态
	
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getMaterId() {
		return materId;
	}

	public void setMaterId(String materId) {
		this.materId = materId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Override
	public String toString() {
		return "StockQueryBean [storageId=" + storageId + ", materId=" + materId + ", placeId=" + placeId + ", batch="
				+ batch + ", keyword=" + keyword + ", checkStatus=" + checkStatus + "]";
	}

}