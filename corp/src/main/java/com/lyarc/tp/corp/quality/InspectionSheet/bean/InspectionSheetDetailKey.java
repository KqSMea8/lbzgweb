package com.lyarc.tp.corp.quality.InspectionSheet.bean;

public class InspectionSheetDetailKey {
    private String ledgerId;

    private String materId;

    private String inspectionId;
    
    private String unqualifiedId;

    public String getUnqualifiedId() {
		return unqualifiedId;
	}

	public void setUnqualifiedId(String unqualifiedId) {
		this.unqualifiedId = unqualifiedId;
	}

	public String getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId == null ? null : ledgerId.trim();
    }

    public String getMaterId() {
        return materId;
    }

    public void setMaterId(String materId) {
        this.materId = materId == null ? null : materId.trim();
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId == null ? null : inspectionId.trim();
    }
}