package com.lyarc.tp.corp.purchase.provider.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.purchase.provider.bean.Provider;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderQueryBean;
import com.lyarc.tp.corp.purchase.provider.dao.ProviderMapper;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    @Autowired
    private ProviderMapper providerMapper;
   
    public List<ProviderDto> list(ProviderQueryBean queryBean) {
        List<ProviderDto> providerList = providerMapper.list(queryBean);
        return providerList;
    }

    public Long count(ProviderQueryBean queryBean) {
       Long count =  providerMapper.count(queryBean);
       return count;
    }

    public Integer delete(String providerId) {
        int rows = providerMapper.deleteByPrimaryKey(providerId);
        return rows;
    }

    public Integer update(Provider provider) {
        int rows = providerMapper.updateByPrimaryKeySelective(provider);
        return rows;
    }

    public Integer add(Provider provider) {
        //校验id 是否重复
        String providerId = provider.getProviderId();
        Provider existProvider = providerMapper.selectByPrimaryKey(providerId);
        if(existProvider != null){
            throw new CorpException(ResultVo.failure("供应商id已存在"));
        }
        int row = providerMapper.insertSelective(provider);
        return row;
    }

    public Provider getProvider(String providerId) {
        Provider provider = providerMapper.selectByPrimaryKey(providerId);
        return provider;
    }

}
