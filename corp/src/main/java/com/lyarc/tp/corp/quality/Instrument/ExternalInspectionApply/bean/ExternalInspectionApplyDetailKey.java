package com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean;

public class ExternalInspectionApplyDetailKey {
    private String instrumentId;

    private String externalApplyId;

    private String materId;

    public String getMaterId() {
		return materId;
	}

	public void setMaterId(String materId) {
		this.materId = materId;
	}

	public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId == null ? null : instrumentId.trim();
    }

    public String getExternalApplyId() {
        return externalApplyId;
    }

    //XX
    public void setExternalApplyId(String externalApplyId) {
        this.externalApplyId = externalApplyId == null ? null : externalApplyId.trim();
    }
}