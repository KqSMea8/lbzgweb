package com.lyarc.tp.corp.workflow.dao;

import com.lyarc.tp.corp.workflow.bean.WorkflowGroup;
import com.lyarc.tp.corp.workflow.dto.WorkflowGroupListDTO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkflowGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowGroup record);

    int insertSelective(WorkflowGroup record);

    WorkflowGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkflowGroup record);

    int updateByPrimaryKey(WorkflowGroup record);

    List<WorkflowGroupListDTO> list(WorkflowGroup param);
}