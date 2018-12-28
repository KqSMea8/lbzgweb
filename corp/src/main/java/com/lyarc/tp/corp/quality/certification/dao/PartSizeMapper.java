package com.lyarc.tp.corp.quality.certification.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.quality.certification.bean.PartSize;
@Mapper
public interface PartSizeMapper {
    int deleteByPrimaryKey(String partSizeId);

    int insert(PartSize record);

    int insertSelective(PartSize record);

    List<PartSize> selectByPrimaryKeys();

    int updateByPrimaryKeySelective(PartSize record);

    int updateByPrimaryKey(PartSize record);

   List<PartSize> selectByPrimaryKey(@Param("partSizeId") String partSizeId);
   PartSize selectByPid(PartSize partSize);
   int updStatusP(PartSize record);
}