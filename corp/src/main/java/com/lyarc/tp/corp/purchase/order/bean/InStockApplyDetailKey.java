package com.lyarc.tp.corp.purchase.order.bean;

public class InStockApplyDetailKey {
    private String inStockApplyId;

    private String materId;

    public String getInStockApplyId() {
        return inStockApplyId;
    }

    public void setInStockApplyId(String inStockApplyId) {
        this.inStockApplyId = inStockApplyId == null ? null : inStockApplyId.trim();
    }

    public String getMaterId() {
        return materId;
    }

    public void setMaterId(String materId) {
        this.materId = materId == null ? null : materId.trim();
    }
}