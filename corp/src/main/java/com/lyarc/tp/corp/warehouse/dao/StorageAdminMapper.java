package com.lyarc.tp.corp.warehouse.dao;

import com.lyarc.tp.corp.warehouse.bean.StorageAdmin;
import com.lyarc.tp.corp.warehouse.dto.StorageAdminDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StorageAdminMapper {
    int deleteByPrimaryKey(String empId);

    int insert(StorageAdmin record);

    int insertSelective(StorageAdmin record);

    StorageAdmin selectByPrimaryKey(String empId);

    int updateByPrimaryKeySelective(StorageAdmin record);

    int updateByPrimaryKey(StorageAdmin record);

    List<StorageAdminDto> list();
}