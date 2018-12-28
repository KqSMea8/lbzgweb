package com.lyarc.tp.corp.production.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.production.bean.SelfMaterMakeApplyDetail;

@Mapper
public interface SelfMaterMakeApplyDetailMapper {

	// 增添明细
	Integer insertSelective(SelfMaterMakeApplyDetail selfMaterMakeApplyDetail);

	// 删除自用件生产申请的明细
	Integer deleteByselfMaterMakeApplyId(@Param("selfMaterMakeApplyId") String selfMaterMakeApplyId);

}
