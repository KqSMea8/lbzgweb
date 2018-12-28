package com.lyarc.tp.corp.purchase.provider.dao;

import com.lyarc.tp.corp.purchase.provider.bean.NewProviderMaterialDetail;
import com.lyarc.tp.corp.purchase.provider.bean.NewProviderMaterialDetailKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewProviderMaterialDetailMapper {
    int deleteByPrimaryKey(NewProviderMaterialDetailKey key);

    int insert(NewProviderMaterialDetail record);

    int insertSelective(NewProviderMaterialDetail record);

    NewProviderMaterialDetail selectByPrimaryKey(NewProviderMaterialDetailKey key);

    int updateByPrimaryKeySelective(NewProviderMaterialDetail record);

    int updateByPrimaryKey(NewProviderMaterialDetail record);

    int deleteByNpmId(@Param("npmId") String npmId);

    List<NewProviderMaterialDetail> selectByNpmId(@Param("npmId") String npmId);
}