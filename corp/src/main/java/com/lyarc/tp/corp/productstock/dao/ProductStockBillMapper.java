package com.lyarc.tp.corp.productstock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.productstock.bean.ProductStockBillBean;
import com.lyarc.tp.corp.productstock.bean.ProductStockBillQueryBean;

@Mapper
public interface ProductStockBillMapper {
	// 查询列表
	List<ProductStockBillBean> list(ProductStockBillQueryBean productStockBillQueryBean);

	// 查询条数
	Long count(ProductStockBillQueryBean productStockBillQueryBean);

	// 插入主要部分
	int insertSelective(ProductStockBillBean productStockBillBean);

	// 1.入库 2.出库 3.移库 4.调库
	Integer createSequence(@Param("currentDate") String currentDate);

	// 查询
	ProductStockBillBean selectedObject(@Param("stockBillId") String stockBillId);

	// 更新
	Integer updateByPrimaryKeySelective(ProductStockBillBean productStockBillBean);

	// 删除
	Integer delete(@Param("stockBillId") String stockBillId);

	// 获取详情
	ProductStockBillBean getBill(@Param("stockBillId") String stockBillId);

	// 提交
	Integer publicStatus(@Param("stockBillId") String stockBillId);

	// 审核通过
	Integer passStatus(String stockBillId);

	// 驳回 status=1
	Integer rejectStatus(@Param("stockBillId") String stockBillId, @Param("auditRemark") String auditRemark);
}
