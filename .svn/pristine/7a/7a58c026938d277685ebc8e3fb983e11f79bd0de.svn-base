package com.lyarc.tp.corp.purchase.provider.service;

import com.lyarc.tp.corp.purchase.provider.bean.ProviderMaterial;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderMaterialKey;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderMaterialQueryBean;
import com.lyarc.tp.corp.purchase.provider.dao.ProviderMaterialMapper;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderMaterialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderMaterialService {
    @Autowired
    private ProviderMaterialMapper providerMaterialMapper;
   
    public List<ProviderMaterialDto> list(ProviderMaterialQueryBean queryBean) {
        List<ProviderMaterialDto> providerMaterialList = providerMaterialMapper.list(queryBean);
        return providerMaterialList;
    }

    public Long count(ProviderMaterialQueryBean queryBean) {
       Long count =  providerMaterialMapper.count(queryBean);
       return count;
    }



    public Integer update(ProviderMaterial providerMaterial) {
        int rows = providerMaterialMapper.updateByPrimaryKeySelective(providerMaterial);
        return rows;
    }

    public Integer add(ProviderMaterial providerMaterial) {
//        //校验id 是否重复
//        String providerId = providerMaterial.getProviderId();
//        String materialId = providerMaterial.getMaterId();
//        ProviderMaterialKey key = new ProviderMaterialKey();
//        key.setMaterId(materialId);
//        key.setProviderId(providerId);
//        ProviderMaterial existProviderMaterial = providerMaterialMapper.selectByPrimaryKey(key);
//        if(existProviderMaterial != null){
//            throw new CorpException(ResultVo.failure("供应商id已存在"));
//        }
        int row = providerMaterialMapper.insertSelective(providerMaterial);
        return row;
    }

    public ProviderMaterial getProviderMaterial(ProviderMaterialKey key) {
        ProviderMaterial providerMaterial = providerMaterialMapper.selectByPrimaryKey(key);
        return providerMaterial;
    }

}
