package com.lyarc.tp.corp.purchase.provider.bean;

import com.googlecode.jmapper.annotations.JMap;

public class ProviderMaterialKey {
    private String providerId;

    @JMap(attributes = {"materId"}, classes = {ProviderMaterial.class})
    private String materId;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId == null ? null : providerId.trim();
    }

    public String getMaterId() {
        return materId;
    }

    public void setMaterId(String materId) {
        this.materId = materId == null ? null : materId.trim();
    }
}