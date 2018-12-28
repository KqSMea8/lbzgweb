package com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.CorrectiveAction;
@Mapper
public interface CorrectiveActionMapper {
    int deleteByPrimaryKey(String remedialActionId);

    int insert(CorrectiveAction record);

    int insertSelective(CorrectiveAction record);

    CorrectiveAction selectByPrimaryKey(String remedialActionId);

    int updateByPrimaryKeySelective(CorrectiveAction record);

    int updateByPrimaryKey(CorrectiveAction record);

	Integer insertCorrertive(CorrectiveAction record);
}