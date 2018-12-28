package com.lyarc.tp.corp.warehouse.dao;

import com.lyarc.tp.corp.warehouse.bean.Storage;
import com.lyarc.tp.corp.warehouse.bean.StorageQueryBean;
import com.lyarc.tp.corp.warehouse.dto.StorageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface StorageMapper {
    int deleteByPrimaryKey(String storageId);

    int insert(Storage record);

    int insertSelective(Storage record);

    Storage selectByPrimaryKey(String storageId);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

    List<StorageDto> list(StorageQueryBean queryBean);

    Long count(StorageQueryBean queryBean);
}