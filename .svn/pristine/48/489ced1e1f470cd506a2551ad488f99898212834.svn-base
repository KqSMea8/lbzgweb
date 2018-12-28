package com.lyarc.tp.corp.contract.shipping.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.contract.shipping.bean.ShippingDetail;

@Mapper
public interface ShippingDetailMapper {

	/*
	 * 添加明细
	 */
	Integer insertSelective(ShippingDetail shippingDetail);

	// 删除发货单的明细
	Integer deleteByShippingId(@Param("shippingId") String shippingId);
}
