package com.lyarc.tp.corp.contract.shipping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.contract.shipping.bean.ShippingBean;
import com.lyarc.tp.corp.contract.shipping.bean.ShippingQueryBean;

@Mapper
public interface ShippingMapper {
	// 数据
	List<ShippingBean> list(ShippingQueryBean shippingQueryBean);

	// 数量
	long count(ShippingQueryBean shippingQueryBean);

	// 添加
	Integer insertSelective(ShippingBean shippingBean);

	// 查询
	ShippingBean selectedObject(@Param("shippingId") String shippingId);

	// 更新
	Integer updateByPrimaryKeySelective(ShippingBean shippingBean);

	// 获取详情
	ShippingBean getBill(@Param("shippingId") String shippingId);

}
