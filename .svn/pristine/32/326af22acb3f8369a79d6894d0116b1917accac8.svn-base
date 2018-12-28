package com.lyarc.tp.corp.warehouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.lyarc.tp.corp.warehouse.bean.StockBillBean;
import com.lyarc.tp.corp.warehouse.bean.StockBillQueryBean;
import com.lyarc.tp.corp.warehouse.dto.StockBillDTO;

@Mapper
public interface StockBillMapper {

	/**
	 * 查询列表
	 * 
	 * @param queryBean
	 * @return
	 */
	List<StockBillBean> list(StockBillQueryBean queryBean);

	Long count(StockBillQueryBean queryBean);

	Integer createSequence(@Param("currentDate") String currentDate);

	// 插入主要部分
	int insertSelective(StockBillBean stockBillBean);

	/**
	 * (单独或批量发布)提交
	 */
	Integer publicStatus(@Param("stockBillId") String stockBillId);

	/**
	 * 驳回 status=3
	 */
	Integer rejectStatus(@Param("stockBillId") String stockBillId,@Param("auditRemark") String auditRemark);

	/**
	 * 获取详情
	 */
	StockBillBean getBill(@Param("stockBillId") String stockBillId);

	/**
	 * 更新
	 */
	Integer updateByPrimaryKeySelective(StockBillBean stockBillBean);

	/**
	 * 删除
	 */
	Integer delete(@Param("stockBillId") String stockBillId);

	/**
	 * 通过 status=2
	 */
	Integer passStatus(@Param("stockBillId") String stockBillId);

	StockBillBean selectedObject(@Param("stockBillId") String stockBillId);
	

}
