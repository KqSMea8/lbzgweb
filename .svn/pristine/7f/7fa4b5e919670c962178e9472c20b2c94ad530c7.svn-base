package com.lyarc.tp.corp.outprocess.contract.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.outprocess.contract.bean.OutContractDetailBean;

@Mapper
public interface OutContractDetailMapper {
	
	// 数据
	List<OutContractDetailBean> query(@Param("contractId") String contractId);
	
	// 数量
	Long count(@Param("contractId") String contractId);
	
	// 查询
	OutContractDetailBean get(@Param("id") Integer id);

	// 添加
	Integer add(OutContractDetailBean outContractDetailBean);

	// 更新
	Integer update(OutContractDetailBean outContractDetailBean);
	
	// 删除
	Integer del(@Param("id") Integer id);

}
