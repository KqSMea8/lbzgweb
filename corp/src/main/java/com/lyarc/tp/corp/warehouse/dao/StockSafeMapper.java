package com.lyarc.tp.corp.warehouse.dao;

import com.lyarc.tp.corp.warehouse.bean.*;
import com.lyarc.tp.corp.warehouse.dto.StockSafeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockSafeMapper {
    int deleteByPrimaryKey(StockSafeKey key);

    int insert(StockSafe record);

    int insertSelective(StockSafe record);

    StockSafe selectByPrimaryKey(StockSafeKey key);

    int updateByPrimaryKeySelective(StockSafe record);

    int updateByPrimaryKey(StockSafe record);

    List<StockSafe> list(StockSafeQueryBean queryBean);

    Long count(StockSafeQueryBean queryBean);
}