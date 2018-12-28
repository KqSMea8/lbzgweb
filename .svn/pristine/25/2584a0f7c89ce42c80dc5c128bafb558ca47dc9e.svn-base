package com.lyarc.tp.corp.outprocess.contract.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.outprocess.contract.bean.OutContractBean;
import com.lyarc.tp.corp.outprocess.contract.bean.OutContractQueryBean;

@Mapper
public interface OutContractMapper {
	
	// 数据
	List<OutContractBean> query(OutContractQueryBean outContractQueryBean);
	
	// 数量
	Long count(OutContractQueryBean outContractQueryBean);

	// 添加
	Integer add(OutContractBean outContractBean);

	// 查询
	OutContractBean get(@Param("contractId") String contractId);

	// 更新
	Integer update(OutContractBean outContractBean);
	
	// 删除
	Integer del(@Param("contractId") String contractId);

}