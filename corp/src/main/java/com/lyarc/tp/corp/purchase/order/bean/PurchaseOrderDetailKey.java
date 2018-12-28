package com.lyarc.tp.corp.purchase.order.bean;

public class PurchaseOrderDetailKey {
    private String purchaseOrderId;

    private String materId;

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId == null ? null : purchaseOrderId.trim();
    }

    public String getMaterId() {
        return materId;
    }

    public void setMaterId(String materId) {
        this.materId = materId == null ? null : materId.trim();
    }
}