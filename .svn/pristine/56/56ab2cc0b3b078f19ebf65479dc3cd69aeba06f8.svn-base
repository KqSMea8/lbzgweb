package com.lyarc.tp.corp.purchase.order.dao;

import com.lyarc.tp.corp.purchase.order.bean.InStockApply;
import com.lyarc.tp.corp.purchase.order.bean.InStockApplyQueryBean;
import com.lyarc.tp.corp.purchase.order.form.InStockApplyStatusRejectForm;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InStockApplyMapper {

    int insert(InStockApply record);

    int insertSelective(InStockApply record);

    InStockApply selectByPrimaryKey(String inStockApplyId);

    List<InStockApply> list(InStockApplyQueryBean queryBean);

    Long count(InStockApplyQueryBean queryBean);

    InStockApply selectAndDetail(@Param("inStockApplyId") String inStockApplyId);

    Integer createSequence(@Param("currentDate") String currentDate);

	Integer selectedId(@Param("inStockApplyId") String inStockApplyId);

	Integer rejectStatus(InStockApplyStatusRejectForm form);
	
	Integer pass(@Param("inStockApplyId") String inStockApplyId,@Param("stockBillId") String stockBillId);

}