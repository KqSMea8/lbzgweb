package com.lyarc.tp.corp.purchase.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPrice;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceLog;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceQueryBean;


@Mapper
public interface ProviderAdjustPriceLogMapper {
	
	int insertSelective(ProviderAdjustPriceLog record);
	
	ProviderAdjustPriceLog selectedObject(@Param("providerId") String providerId);
	
	List<ProviderAdjustPriceLog> list(ProviderAdjustPriceLog queryBean);
	
	Long count(ProviderAdjustPriceLog queryBean);
}
