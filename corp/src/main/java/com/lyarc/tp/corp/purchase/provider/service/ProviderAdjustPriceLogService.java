package com.lyarc.tp.corp.purchase.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPrice;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceLog;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceQueryBean;
import com.lyarc.tp.corp.purchase.provider.dao.ProviderAdjustPriceLogMapper;

@Service
public class ProviderAdjustPriceLogService {
	@Autowired
	private ProviderAdjustPriceLogMapper providerAdjustPriceLogMapper;
	
	public ProviderAdjustPriceLog selectedObject(String providerId) {
		return providerAdjustPriceLogMapper.selectedObject(providerId);
	}
	
	public Integer add(ProviderAdjustPriceLog providerAdjustPriceLog) {
		int rows=providerAdjustPriceLogMapper.insertSelective(providerAdjustPriceLog);
		return rows;	 
	 }

	public PageResultBean list(ProviderAdjustPriceLog queryBean) {
		List<ProviderAdjustPriceLog> providerAdjustPriceList = providerAdjustPriceLogMapper.list(queryBean);
		Long count=providerAdjustPriceLogMapper.count(queryBean);
		return PageResultBean.success(count, providerAdjustPriceList);
	}
}
