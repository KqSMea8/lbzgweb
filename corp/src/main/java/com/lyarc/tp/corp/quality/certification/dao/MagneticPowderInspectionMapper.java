package com.lyarc.tp.corp.quality.certification.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.quality.certification.bean.MagneticPowderInspection;
@Mapper
public interface MagneticPowderInspectionMapper {
    int deleteByPrimaryKey(String magneticId);

    int insert(MagneticPowderInspection record);

    int insertSelective(MagneticPowderInspection record);

    List<MagneticPowderInspection> selectByPrimaryKeys();

    int updateByPrimaryKeySelective(MagneticPowderInspection record);

    int updateByPrimaryKeyWithBLOBs(MagneticPowderInspection record);

    int updateByPrimaryKey(MagneticPowderInspection record);
    int updStatusM(MagneticPowderInspection record);
    MagneticPowderInspection selectByPrimaryKey(MagneticPowderInspection record);
    MagneticPowderInspection selectByMid(MagneticPowderInspection record);
}