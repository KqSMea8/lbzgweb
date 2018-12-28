package com.lyarc.tp.corp.workflow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.workflow.bean.WorkflowReport;
import com.lyarc.tp.corp.workflow.dto.WorkflowReportDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WorkflowReportMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowReport report);

    int insertSelective(WorkflowReport report);

    WorkflowReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkflowReport report);

    int updateByPrimaryKey(WorkflowReport report);

    List<WorkflowReport> list(Integer formId);
    
    List<WorkflowReportDTO> query(String empId);




    WorkflowReport selectReportById(@Param("id") Integer id, @Param("corpId") String corpId);
}
