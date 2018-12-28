package com.lyarc.tp.corp.purchase.order.dao;

import com.lyarc.tp.corp.purchase.order.bean.PurchaseOrderDetail;
import com.lyarc.tp.corp.purchase.order.bean.PurchaseOrderDetailKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PurchaseOrderDetailMapper {
    int deleteByPrimaryKey(PurchaseOrderDetailKey key);

    int insert(PurchaseOrderDetail record);

    int insertSelective(PurchaseOrderDetail record);

    PurchaseOrderDetail selectByPrimaryKey(PurchaseOrderDetailKey key);

    int updateByPrimaryKeySelective(PurchaseOrderDetail record);

    int updateByPrimaryKey(PurchaseOrderDetail record);

    void deleteByPurchaseOrderId(@Param("purchaseOrderId") String purchaseOrderId);
}