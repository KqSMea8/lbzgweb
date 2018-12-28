package com.lyarc.tp.corp.contract.inquire.dao;

import com.lyarc.tp.corp.contract.inquire.bean.InquireDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquireDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InquireDetail record);

    int insertSelective(InquireDetail record);

    InquireDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InquireDetail record);

    int updateByPrimaryKeyWithBLOBs(InquireDetail record);

    int updateByPrimaryKey(InquireDetail record);

    Integer deleteDetailByIds(@Param("ids") List<String> ids);

    void deleteByInquireId(@Param("inquireId") String inquireId);

    List<InquireDetail> selectListByInquireId(@Param("inquireId") String inquireId);
}