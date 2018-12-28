package com.lyarc.tp.corp.purchase.provider.bean;

import com.lyarc.tp.corp.common.bean.BaseQueryBean;

import java.math.BigDecimal;
import java.util.Date;

public class ProviderMaterialQueryBean extends BaseQueryBean{
    private String providerId;

    private String materId;

    private String materType;

    private Integer status;

    private String materName;
    
    private Integer containOtherMater=0;

    public Integer getContainOtherMater() {
		return containOtherMater;
	}

	public void setContainOtherMater(Integer containOtherMater) {
		this.containOtherMater = containOtherMater;
	}

	public String getMaterType() {
        return materType;
    }

    public void setMaterType(String materType) {
        this.materType = materType == null ? null : materType.trim();
    }

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


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMaterName() {
        return materName;
    }

    public void setMaterName(String materName) {
        this.materName = materName;
    }
}