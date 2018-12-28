package com.lyarc.tp.corp.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.system.customer.bean.Customer;
import com.lyarc.tp.corp.warehouse.bean.StorageTempQueryBean;
import com.lyarc.tp.corp.warehouse.dao.StorageTempMapper;
import com.lyarc.tp.corp.warehouse.dto.StorageTempDto;


@Service
public class StorageTempService {
	@Autowired
	private StorageTempMapper storageTempMapper;
	
	public List<StorageTempDto> list(StorageTempQueryBean queryBean) {
		List<StorageTempDto> storageTempList = storageTempMapper.list(queryBean);
		return storageTempList;
	}

	public Long count(StorageTempQueryBean queryBean) {
		Long count =  storageTempMapper.count(queryBean);
		return count;
	}

	public Integer delete(Integer id) {
		int rows=storageTempMapper.deleteByPrimaryKey(id);
		return rows;
	}

	
	public Integer add(StorageTempQueryBean storageTempQueryBean) {
		int row=storageTempMapper.insertSelective(storageTempQueryBean);
		return row;
	}
	
	public Integer update(StorageTempQueryBean storageTempQueryBean) {
		int rows=storageTempMapper.updateByPrimaryKey(storageTempQueryBean);
		return rows;
	}

	public StorageTempQueryBean getStorageTemp(String storageId) {
	        StorageTempQueryBean storageTempQueryBean = storageTempMapper.selectByPrimaryKey(storageId);
	        return storageTempQueryBean;
	    }
}
