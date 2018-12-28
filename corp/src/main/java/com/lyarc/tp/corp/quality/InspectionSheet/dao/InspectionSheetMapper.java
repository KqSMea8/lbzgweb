package com.lyarc.tp.corp.quality.InspectionSheet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheet;
import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheetQueryBean;
@Mapper
public interface InspectionSheetMapper {
    int deleteByPrimaryKey(String inspectionId);

    int insert(InspectionSheet record);

    int addMain(InspectionSheet record);

//    InspectionSheet selectByPrimaryKey(String inspectionId);

    int updateByPrimaryKeySelective(InspectionSheet record);

    int updateByPrimaryKey(InspectionSheet record);

	List<InspectionSheet> selectId(InspectionSheet inspectionId);

	List<InspectionSheet> list(InspectionSheet Bean);

	Long count(InspectionSheet Bean);

	List<InspectionSheet> selectBySelective(InspectionSheetQueryBean bean);

	List<InspectionSheet> StandardList(InspectionSheet bean);

}