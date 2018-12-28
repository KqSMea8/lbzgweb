package com.lyarc.tp.corp.productstock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.productstock.bean.ProductStockInBean;
import com.lyarc.tp.corp.productstock.bean.ProductStockInQueryBean;

@Mapper
public interface ProductStockInMapper {
	
	List<ProductStockInBean> list(ProductStockInQueryBean queryBean);

	Long count(ProductStockInQueryBean queryBean);

	int insertSelective(ProductStockInBean bean);
}
