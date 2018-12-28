package com.lyarc.tp.corp.workflow.dao;

import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.form.WorkflowOperLogParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkflowOperLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowOperLog record);

    int insertSelective(WorkflowOperLog record);

    WorkflowOperLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkflowOperLog record);

    int updateByPrimaryKey(WorkflowOperLog record);

    List<WorkflowOperLog> list(WorkflowOperLogParam param);

    WorkflowOperLog selectByInstId(@Param("instId") String instId);
}