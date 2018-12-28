package com.lyarc.tp.corp.warehouse.dao;

import com.lyarc.tp.corp.warehouse.bean.StoragePlace;
import com.lyarc.tp.corp.warehouse.bean.StoragePlaceKey;
import com.lyarc.tp.corp.warehouse.bean.StoragePlaceQueryBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoragePlaceMapper {
    int deleteByPrimaryKey(StoragePlaceKey key);

    int insert(StoragePlace record);

    int insertSelective(StoragePlace record);

    StoragePlace selectByPrimaryKey(StoragePlaceKey key);

    int updateByPrimaryKeySelective(StoragePlace record);

    int updateByPrimaryKey(StoragePlace record);

    List<StoragePlace> list(StoragePlaceQueryBean queryBean);

    List<StoragePlace> selectChildPlace(StoragePlaceKey storagePlaceKey);
}