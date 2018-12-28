package com.lyarc.tp.corp.quality.InspectionSheet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheetDetail;
import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheetDetailKey;
@Mapper
public interface InspectionSheetDetailMapper {
    int deleteByPrimaryKey(InspectionSheetDetailKey key);

    int insert(InspectionSheetDetail record);

    int insertSelective(InspectionSheetDetail record);

    List<InspectionSheetDetail> selectByPrimaryKey(@Param(value="inspectionApplyId")String inspectionApplyId,@Param(value="materId")String materId);

    int updateByPrimaryKeySelective(InspectionSheetDetail record);

    int updateByPrimaryKey(InspectionSheetDetail record);

	String selectId();
}