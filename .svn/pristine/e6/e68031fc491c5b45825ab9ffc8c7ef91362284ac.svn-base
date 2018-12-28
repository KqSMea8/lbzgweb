package com.lyarc.tp.corp.quality.certification.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.quality.certification.bean.UltrasonicTesting;
@Mapper
public interface UltrasonicTestingMapper {
    int deleteByPrimaryKey(String ultrasonicId);	

    int insertSelective(UltrasonicTesting record);

    List<UltrasonicTesting> selectByPrimaryKeys();

    int updateByPrimaryKeySelective(UltrasonicTesting record);

    int updateByPrimaryKey(UltrasonicTesting record);
    int updStatusU(UltrasonicTesting record);
    UltrasonicTesting getUltrasonicTesting(String reocrd);

    UltrasonicTesting selectByPrimaryKey(UltrasonicTesting record);
}