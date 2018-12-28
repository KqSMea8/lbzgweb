package com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean;

public class UnqualifiedProductBillKey {
    private String unqualifiedId;

    private String inspectionId;

    public String getUnqualifiedId() {
        return unqualifiedId;
    }

    public void setUnqualifiedId(String unqualifiedId) {
        this.unqualifiedId = unqualifiedId == null ? null : unqualifiedId.trim();
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId == null ? null : inspectionId.trim();
    }
}