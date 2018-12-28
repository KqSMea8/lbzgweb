package com.lyarc.tp.corp.purchase.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.lyarc.tp.corp.purchase.provider.bean.ProviderPayApply;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderPayApplyQueryBean;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderPayApplyDto;
import com.lyarc.tp.corp.purchase.provider.form.ProviderPayApplyForm;

@Mapper
public interface ProviderPayApplyMapper {

	List<ProviderPayApply> list(ProviderPayApplyQueryBean queryBean);
	
	Long count(ProviderPayApplyQueryBean queryBean);

	Integer insertSelective(ProviderPayApply queryBean);

	Integer selectedId(@Param("paId") String paId);
	
	Integer updateByPrimaryKeySelective(ProviderPayApply providerPayApply);
	
	Integer submit(ProviderPayApplyForm ProviderPayApplyForm);
	
	ProviderPayApplyDto selectedObject(@Param("paId") String paId); 
	
	Integer delete(@Param("paId") String paId);
	
}
  