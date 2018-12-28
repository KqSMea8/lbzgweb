package com.lyarc.tp.corp.warehouse.bean;

public class StockKey {
	private String storageId;

	private String materId;

	private String placeId;

	private String batch;// 增加主键：批次号
	
	private String placeName;
	
	public StockKey() {}
	
	public StockKey(String storageId,String materId,String placeId,String batch) {
		this.storageId = storageId;
		this.materId = materId;
		this.placeId = placeId;
		this.batch = batch;
	}
	
	public static StockKey create(String storageId,String materId,String placeId,String batch) {
		return new StockKey(storageId,materId,placeId,batch);
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId == null ? null : storageId.trim();
	}

	public String getMaterId() {
		return materId;
	}

	public void setMaterId(String materId) {
		this.materId = materId == null ? null : materId.trim();
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId == null ? null : placeId.trim();
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	} 

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	@Override
	public String toString() {
		return "StockKey [storageId=" + storageId + ", materId=" + materId + ", placeId=" + placeId + ", batch=" + batch
				+ "]";
	}

}