package com.lyarc.tp.corp.production.dao;

import com.lyarc.tp.corp.production.bean.OutStockApply;
import com.lyarc.tp.corp.production.bean.OutStockApplyDetail;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OutStockApplyDetailMapper {

    int insertSelective(OutStockApplyDetail outStockApplyDetail);
    
    Integer update(OutStockApplyDetail outStockApplyDetail);
    
    int delete(String outStockApplyId);
	
    List<OutStockApplyDetail> getDetails(String outStockApplyId);
}