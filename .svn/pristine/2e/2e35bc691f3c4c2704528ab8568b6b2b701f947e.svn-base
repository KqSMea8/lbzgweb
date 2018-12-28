package com.lyarc.tp.corp.purchase.provider.dao;

import com.lyarc.tp.corp.purchase.provider.bean.Provider;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderQueryBean;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderDto;
import com.lyarc.tp.corp.system.customer.bean.CustomerQueryBean;
import com.lyarc.tp.corp.system.material.dto.CustomerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProviderMapper {
    int deleteByPrimaryKey(String providerId);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(String providerId);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);

    List<ProviderDto> list(ProviderQueryBean queryBean);

    Long count(ProviderQueryBean queryBean);
}