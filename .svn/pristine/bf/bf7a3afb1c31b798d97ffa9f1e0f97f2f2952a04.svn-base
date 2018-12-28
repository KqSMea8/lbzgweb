package com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean.ExternalInspectionApply;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean.ExternalInspectionApplyDetail;
@Mapper
public interface ExternalInspectionApplyDetailMapper  {

	List<ExternalInspectionApplyDetail> findDetail(ExternalInspectionApplyDetail detail);


	/*ExternalInspectionApplyDetail selectByPrimaryKey(ExternalInspectionApplyDetail key);*/
	
	List<ExternalInspectionApplyDetail> findDetail(@Param("externalApplyId") String externalApplyId);

	Integer selectedId(@Param("externalApplyId") String externalApplyId);


	Integer insertSelective(ExternalInspectionApplyDetail record);
	Integer updateByPrimaryKeySelective(ExternalInspectionApplyDetail record);


	Integer updateInstruementId(ExternalInspectionApplyDetail externalInspectionApplyDetail);


   

	
	
}