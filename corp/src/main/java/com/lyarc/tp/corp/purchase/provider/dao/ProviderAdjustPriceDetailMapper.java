package com.lyarc.tp.corp.purchase.provider.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceDetail;

@Mapper
public interface ProviderAdjustPriceDetailMapper {
	int insertSelective(ProviderAdjustPriceDetail providerAdjustPriceDetail);
	
	Integer deleteByInStockId(@RequestParam String pmapId);
}
