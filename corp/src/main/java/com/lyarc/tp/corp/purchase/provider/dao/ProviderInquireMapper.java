package com.lyarc.tp.corp.purchase.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.purchase.provider.bean.ProviderInquire;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderInquireQueryBean;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderInquireDto;

@Mapper
public interface ProviderInquireMapper {
	
	List<ProviderInquireDto> list(ProviderInquireQueryBean queryBean);
	
	Long count(ProviderInquireQueryBean queryBean);
	
	ProviderInquire selectByPrimaryKey(Integer id);
	
	int insertSelective(ProviderInquire record);
	
	int updateByPrimaryKey(ProviderInquire record);
	
	int deleteByPrimaryKey(Integer id);
}
