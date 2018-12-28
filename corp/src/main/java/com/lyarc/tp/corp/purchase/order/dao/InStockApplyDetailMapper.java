package com.lyarc.tp.corp.purchase.order.dao;

import com.lyarc.tp.corp.purchase.order.bean.InStockApplyDetail;
import com.lyarc.tp.corp.purchase.order.bean.InStockApplyDetailKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InStockApplyDetailMapper {
    int deleteByPrimaryKey(InStockApplyDetailKey key);

    int insert(InStockApplyDetail record);

    int insertSelective(InStockApplyDetail record);

    InStockApplyDetail selectByPrimaryKey(InStockApplyDetailKey key);

    int updateByPrimaryKeySelective(InStockApplyDetail record);

    int updateByPrimaryKey(InStockApplyDetail record);

    void deleteByInStockApplyId(@Param("inStockApplyId") String inStockApplyId);
}