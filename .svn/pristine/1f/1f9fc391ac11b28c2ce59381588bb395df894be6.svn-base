package com.lyarc.tp.corp.aftersales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.aftersales.bean.ComplaintBean;
import com.lyarc.tp.corp.aftersales.bean.ComplaintQueryBean;

@Mapper
public interface ComplaintMapper {
	// 列表
	List<ComplaintBean> list(ComplaintQueryBean complaintQueryBean);

	// 数量
	Long count(ComplaintQueryBean complaintQueryBean);

	// 查询
	ComplaintBean selectObject(String complaintId);

	// 添加
	Integer add(ComplaintBean complaintBean);

	// 修改
	Integer updateByPrimarySelective(ComplaintBean complaintBean);

	// 删除
	Integer delete(String complaintId);
}
