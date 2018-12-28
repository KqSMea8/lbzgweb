package com.lyarc.tp.corp.workflow.dao;

import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkflowInstEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowInstEvent record);

    int insertSelective(WorkflowInstEvent record);

    WorkflowInstEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkflowInstEvent record);

    int updateByPrimaryKey(WorkflowInstEvent record);

    List<WorkflowInstEvent> queryEventInfo();
}