package com.lyarc.tp.corp.production.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.production.bean.SelfMaterMakeApplyBean;
import com.lyarc.tp.corp.production.bean.SelfMaterMakeApplyQueryBean;

@Mapper
public interface SelfMaterMakeApplyMapper {
	// 数据
	List<SelfMaterMakeApplyBean> list(SelfMaterMakeApplyQueryBean selfMaterMakeApplyQueryBean);

	// 数量
	long count(SelfMaterMakeApplyQueryBean selfMaterMakeApplyQueryBean);

	// 获取详情
	SelfMaterMakeApplyBean getBill(@Param("selfMaterMakeApplyId") String selfMaterMakeApplyId);

	// 添加
	Integer insertSelective(SelfMaterMakeApplyBean selfMaterMakeApplyBean);

	// 送审
	Integer submit(String selfMaterMakeApplyId);

	// 更新主数据
	Integer updateByPrimaryKeySelective(SelfMaterMakeApplyBean selfMaterMakeApplyBean);

	// 删除
	Integer deleteBySelfMaterMakeApplyId(String selfMaterMakeApplyId);

	// 查询
	SelfMaterMakeApplyBean selectedObject(@Param("selfMaterMakeApplyId") String selfMaterMakeApplyId);

}
