package com.lyarc.tp.corp.purchase.provider.dao;

import com.lyarc.tp.corp.purchase.provider.bean.NewProviderMaterial;
import com.lyarc.tp.corp.purchase.provider.bean.NewProviderMaterialQueryBean;
import com.lyarc.tp.corp.purchase.provider.dto.NewProviderMaterialDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewProviderMaterialMapper {
    int deleteByPrimaryKey(String npmId);

    int insert(NewProviderMaterial record);

    int insertSelective(NewProviderMaterial record);

    NewProviderMaterial selectByPrimaryKey(String npmId);

    int updateByPrimaryKeySelective(NewProviderMaterial record);

    int updateByPrimaryKey(NewProviderMaterial record);

    List<NewProviderMaterialDto> list(NewProviderMaterialQueryBean queryBean);

    Long count(NewProviderMaterialQueryBean queryBean);

    NewProviderMaterialDto selectAndDetail(@Param("npmId") String npmId);

    Integer createSequence(@Param("currentDate") String currentDate);
}