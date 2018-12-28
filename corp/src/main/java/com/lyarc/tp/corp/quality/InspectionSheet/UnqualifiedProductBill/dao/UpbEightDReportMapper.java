package com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.UpbEightDReport;
@Mapper
public interface UpbEightDReportMapper {
    int deleteByPrimaryKey(String fileId);

    int insert(UpbEightDReport record);

    UpbEightDReport selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(UpbEightDReport record);

    int updateByPrimaryKeyWithBLOBs(UpbEightDReport record);

    int updateByPrimaryKey(UpbEightDReport record);

	Integer insertUpbEightD(UpbEightDReport record);
}