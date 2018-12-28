package com.lyarc.tp.corp.warehouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.system.customer.bean.Customer;
import com.lyarc.tp.corp.warehouse.bean.StorageTempQueryBean;
import com.lyarc.tp.corp.warehouse.dto.StorageTempDto;

@Mapper
public interface StorageTempMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(StorageTempQueryBean record);
	
	int insertSelective(StorageTempQueryBean record);
	
	int updateByPrimaryKey(StorageTempQueryBean record);
	
	List<StorageTempDto> list(StorageTempQueryBean queryBean);
	
	Long count(StorageTempQueryBean queryBean);

	StorageTempQueryBean selectByPrimaryKey(String storageId);
	  
}
