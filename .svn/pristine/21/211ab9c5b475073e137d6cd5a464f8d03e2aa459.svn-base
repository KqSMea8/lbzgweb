package com.lyarc.tp.corp.purchase.provider.dao;

import com.lyarc.tp.corp.purchase.provider.bean.ProviderMaterial;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderMaterialKey;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderMaterialQueryBean;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderQueryBean;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderDto;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderMaterialDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProviderMaterialMapper {
    int deleteByPrimaryKey(ProviderMaterialKey key);

    int insert(ProviderMaterial record);

    int insertSelective(ProviderMaterial record);

    ProviderMaterial selectByPrimaryKey(ProviderMaterialKey key);

    int updateByPrimaryKeySelective(ProviderMaterial record);

    int updateByPrimaryKey(ProviderMaterial record);


    List<ProviderMaterialDto> list(ProviderMaterialQueryBean queryBean);

    Long count(ProviderMaterialQueryBean queryBean);
}