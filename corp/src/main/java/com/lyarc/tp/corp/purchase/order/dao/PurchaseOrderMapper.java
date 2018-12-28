package com.lyarc.tp.corp.purchase.order.dao;

import com.lyarc.tp.corp.purchase.order.bean.PurchaseOrder;
import com.lyarc.tp.corp.purchase.order.bean.PurchaseOrderQueryBean;
import com.lyarc.tp.corp.purchase.order.dto.PurchaseOrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PurchaseOrderMapper {
    int deleteByPrimaryKey(String purchaseOrderId);

    int insert(PurchaseOrder record);

    int insertSelective(PurchaseOrder record);

    PurchaseOrder selectByPrimaryKey(String purchaseOrderId);

    int updateByPrimaryKeySelective(PurchaseOrder record);

    int updateByPrimaryKey(PurchaseOrder record);

    List<PurchaseOrderDto> list(PurchaseOrderQueryBean queryBean);

    Long count(PurchaseOrderQueryBean queryBean);

    PurchaseOrderDto selectAndDetail(@Param("purchaseOrderId") String purchaseOrderId);
}