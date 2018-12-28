package com.lyarc.tp.corp.purchase.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPrice;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceQueryBean;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderAdjustPriceDto;
import com.lyarc.tp.corp.purchase.provider.form.ProviderAdjustPriceForm;

@Mapper
public interface ProviderAdjustPriceMapper {
	
	List<ProviderAdjustPrice> list(ProviderAdjustPriceQueryBean queryBean);
	
	Long count(ProviderAdjustPriceQueryBean queryBean);
	
	Integer insertSelective(ProviderAdjustPrice queryBean);
	
	Integer updateByPrimaryKeySelective(ProviderAdjustPrice providerAdjustPrice);

	Integer selectedId(@Param("pmapId") String pmapId);
	
	ProviderAdjustPrice selectByPrimaryKey(@Param("pmapId") String pmapId);
	
	ProviderAdjustPriceDto selectAndDetail(@Param("pmapId") String pmapId);

	ProviderAdjustPriceDto selectedObject(@Param("pmapId") String pmapId);

	Integer passStatus(@Param("pmapId") String pmapId);

	Integer publicStatus(@Param("pmapId") String pmapId);

	Integer rejectStatus(ProviderAdjustPriceForm form);
	
	Integer delete(@Param("pmapId") String pmapId);

	Integer submit(ProviderAdjustPriceForm providerAdjustPriceForm);
	
	ProviderAdjustPrice selectedObject(ProviderAdjustPrice providerAdjustPrice);
	
}
