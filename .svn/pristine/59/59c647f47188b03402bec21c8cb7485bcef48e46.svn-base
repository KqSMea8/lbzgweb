package com.lyarc.tp.corp.inspection.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.inspection.bean.InspectionDetailBean;

@Mapper
public interface InspectionDetailMapper {
	// 插入详情
	int insertSelective(InspectionDetailBean inspectionDetailBean);

	// 删除先前的存在旧数据
	Integer deleteByInspectionApplyId(@Param("inspectionApplyId") String inspectionApplyId);
}
