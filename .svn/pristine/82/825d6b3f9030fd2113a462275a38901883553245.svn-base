package com.lyarc.tp.corp.productstock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.productstock.bean.ProductStock;
import com.lyarc.tp.corp.productstock.bean.ProductStockKey;
import com.lyarc.tp.corp.productstock.bean.ProductStockQueryBean;

@Mapper
public interface ProductStockMapper {
	// 查寻产成品库存
	List<ProductStock> list(ProductStockQueryBean productStockQueryBean);

	// 查询总条数
	Long count(ProductStockQueryBean productStockQueryBean);

	// 设置盘点状态
	Integer checkBatchStatus(ProductStock productStock);

	// 获得谋仓库的ProductStock集合
	List<ProductStock> loadCheck(@Param("storageId") String storageId, @Param("checkStatus") Integer checkStatus);

	// 更新 check_status 值、check_quantity、check_remark的值
	Integer checkUpdate(ProductStock productStock);

	// (盘点)状态为提交
	Integer checkSubmit(String storageId);

	// 查找ProductStock
	ProductStock selectByPrimaryKey(ProductStockKey productStockKey);

	// 修改ProductStock
	int updateByPrimaryKeySelective(ProductStock productStock);

	// 添加ProductStock
	int insertSelective(ProductStock record);

	ProductStock get(ProductStockKey key);

	Integer add(ProductStock productStock);

	Integer update(ProductStock productStock);

}
