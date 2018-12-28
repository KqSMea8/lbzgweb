package com.lyarc.tp.corp.warehouse.dao;

import com.lyarc.tp.corp.warehouse.bean.StorageAuth;
import com.lyarc.tp.corp.warehouse.bean.StorageAuthKey;
import com.lyarc.tp.corp.warehouse.dto.StorageAuthDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StorageAuthMapper {
    int deleteByPrimaryKey(StorageAuthKey key);

    int insert(StorageAuth record);

    int insertSelective(StorageAuth record);

    StorageAuth selectByPrimaryKey(StorageAuthKey key);

    int updateByPrimaryKeySelective(StorageAuth record);

    int updateByPrimaryKey(StorageAuth record);

    List<StorageAuthDto> list(StorageAuthKey queryBean);

    List<StorageAuthDto> getAuth(StorageAuthKey queryBean);

    int deleteByEmpId(@Param("empId") String empId);
}