package com.lyarc.tp.corp.inspection.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.inspection.bean.InspectionBean;
import com.lyarc.tp.corp.inspection.dto.InspectionDTO;
import com.lyarc.tp.corp.inspection.form.InspectionForm;
import com.lyarc.tp.corp.inspection.form.InspectionQueryForm;

@Mapper
public interface InspectionMapper {
	// 新建
	Integer add(InspectionBean inspectionBean);

	// 创建单号序列号
	Integer createSequence(@Param("currentDate") String currentDate);

	// 判断id是否存在
	Integer selectedId(@Param("inspectionApplyId") String inspectionApplyId);

	// 详情
	InspectionDTO get(@Param("inspectionApplyId") String inspectionApplyId);

	// 删除
	Integer delete(@Param("inspectionApplyId") String inspectionApplyId);

	/**
	 * 更新 updateByPrimaryKeySelective
	 */
	Integer update(InspectionForm inspectionForm);

	// 列表
	List<InspectionDTO> list(InspectionQueryForm inspectionQueryForm);

	// 计数
	Long count(InspectionQueryForm inspectionQueryForm);
}
