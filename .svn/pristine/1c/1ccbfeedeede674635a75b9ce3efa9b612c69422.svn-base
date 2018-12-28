package com.lyarc.tp.corp.aftersales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.aftersales.bean.ComplaintDisposeBean;
import com.lyarc.tp.corp.aftersales.bean.ComplaintDisposeQueryBean;

@Mapper
public interface ComplaintDisposeMapper {
	// 列表
	List<ComplaintDisposeBean> list(ComplaintDisposeQueryBean complaintDisposeQueryBean);

	// 数量
	Long count(ComplaintDisposeQueryBean complaintDisposeQueryBean);

	// 查询
	ComplaintDisposeBean selectObject(String disposeId);

	// 添加
	Integer add(ComplaintDisposeBean complaintDisposeBean);

	// 修改
	Integer updateByPrimarySelective(ComplaintDisposeBean dbcomplaintDispose);
}
