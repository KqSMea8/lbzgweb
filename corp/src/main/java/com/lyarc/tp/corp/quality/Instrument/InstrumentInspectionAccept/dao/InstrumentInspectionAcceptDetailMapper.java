package com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.bean.InstrumentInspectionAcceptDetail;
@Mapper
public interface InstrumentInspectionAcceptDetailMapper {


    int deleteByPrimaryKey(String inspectionAcceptId);

    int insert(InstrumentInspectionAcceptDetail record);

    int insertSelective(InstrumentInspectionAcceptDetail record);

    InstrumentInspectionAcceptDetail selectByPrimaryKey(String inspectionAcceptId);

    int updateByPrimaryKeySelective(InstrumentInspectionAcceptDetail record);

    int updateByPrimaryKey(InstrumentInspectionAcceptDetail record);
}