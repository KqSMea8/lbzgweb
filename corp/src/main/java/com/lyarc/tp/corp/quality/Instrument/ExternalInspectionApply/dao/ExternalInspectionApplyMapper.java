package com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean.ExternalInspectionApply;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean.ExternalInspectionApplyQueryBean;

@Mapper
public interface ExternalInspectionApplyMapper {

	Long countExternalInspectionApply(ExternalInspectionApplyQueryBean bean);

	List<ExternalInspectionApply> listExternalInspectionApply(ExternalInspectionApplyQueryBean bean);

	Integer insertSelective(ExternalInspectionApply record);

	ExternalInspectionApply getExternalApplyId(Date date, String string);
	Integer updateByPrimaryKeySelective(ExternalInspectionApply externalApplyId);

	ExternalInspectionApply selectByPrimaryKey(String businessKey);

	void updateByStatus(ExternalInspectionApply contractBean);

	/*int updateExternalInspectionApply(ExternalInspectionApply externalInspectionApply);
	*/

   
	
	 
}