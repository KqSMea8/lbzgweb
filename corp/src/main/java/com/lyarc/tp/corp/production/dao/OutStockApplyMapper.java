package com.lyarc.tp.corp.production.dao;

import com.lyarc.tp.corp.production.bean.OutStockApply;
import com.lyarc.tp.corp.production.bean.OutStockApplyQueryBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OutStockApplyMapper {

    int insertSelective(OutStockApply outStockApply);

    List<OutStockApply> list(OutStockApplyQueryBean queryBean);

    Long count(OutStockApplyQueryBean queryBean);

    OutStockApply selectAndDetail(@Param("outStockApplyId") String outStockApplyId);

    Integer createSequence(@Param("currentDate") String currentDate);
    
    int update(OutStockApply outStockApply);
    
    int delete(String outStockApplyId);

	Integer selectedId(@Param("outStockApplyId") String ioutStockApplyId);

	Integer rejectStatus(OutStockApply outStockApply);

	Integer submitStatus(String outStockApplyId);
	

}