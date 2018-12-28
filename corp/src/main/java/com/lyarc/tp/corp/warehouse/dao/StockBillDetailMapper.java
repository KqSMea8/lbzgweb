package com.lyarc.tp.corp.warehouse.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.warehouse.bean.StockBillDetail;

@Mapper
public interface StockBillDetailMapper {

	// 插入详情
	int insertSelective(StockBillDetail stockBillDetail);

	// 删除先前的存在旧数据
	Integer deleteByInStockId(@Param("stockBillId") String stockBillId);
}
