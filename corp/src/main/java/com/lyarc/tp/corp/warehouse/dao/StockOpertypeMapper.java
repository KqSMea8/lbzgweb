package com.lyarc.tp.corp.warehouse.dao;

import com.lyarc.tp.corp.warehouse.bean.StockOpertype;
import com.lyarc.tp.corp.warehouse.bean.StockOpertypeQueryBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockOpertypeMapper {
    int deleteByPrimaryKey(String operId);

    int insert(StockOpertype record);

    int insertSelective(StockOpertype record);

    StockOpertype selectByPrimaryKey(String operId);

    int updateByPrimaryKeySelective(StockOpertype record);

    int updateByPrimaryKey(StockOpertype record);

    List<StockOpertype> list(StockOpertypeQueryBean queryBean);
}