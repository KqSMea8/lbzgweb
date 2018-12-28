package com.lyarc.tp.corp.warehouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.warehouse.bean.Stock;
import com.lyarc.tp.corp.warehouse.bean.StockKey;
import com.lyarc.tp.corp.warehouse.bean.StockQueryBean;

@Mapper
public interface StockMapper {
	int deleteByPrimaryKey(StockKey key);

	int insertSelective(Stock record);

	Stock selectByPrimaryKey(StockKey key);

	int updateByPrimaryKeySelective(Stock record);

	List<Stock> list(StockQueryBean queryBean);

	Long count(StockQueryBean queryBean);

	Integer checkBatchStatus(Stock record);

	Integer checkSubmit(String storageId);

	List<Stock> loadCheck(@Param("storageId") String storageId, @Param("checkStatus") Integer checkStatus);

	Integer checkUpdate(Stock stock);
	
	List<Stock> listMater(StockQueryBean queryBean);

	Long countMater(StockQueryBean queryBean);
	
}