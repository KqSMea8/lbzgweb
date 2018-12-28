package com.lyarc.tp.corp.productstock.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.productstock.bean.ProductStockBillDetail;

@Mapper
public interface ProductStockBillDetailMapper {

	// 插入详情
	int insertSelective(ProductStockBillDetail productStockBillDetail);

	Integer deleteByInStockId(@Param("stockBillId") String stockBillId);
}
