package com.lyarc.tp.corp.workflow.dao;

import com.lyarc.tp.corp.workflow.bean.WorkflowDraft;
import com.lyarc.tp.corp.workflow.form.WorkflowDraftParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkflowDraftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowDraft record);

    int insertSelective(WorkflowDraft record);

    WorkflowDraft selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkflowDraft record);

    int updateByPrimaryKey(WorkflowDraft record);

    List<WorkflowDraft> list(WorkflowDraftParam param);

    List<WorkflowDraft> appList(WorkflowDraftParam param);

    Long count(WorkflowDraftParam param);
}