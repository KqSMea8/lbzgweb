package com.lyarc.tp.corp.workflow.service;

import com.lyarc.tp.corp.workflow.bean.WorkflowDraft;
import com.lyarc.tp.corp.workflow.dao.WorkflowDraftMapper;
import com.lyarc.tp.corp.workflow.form.WorkflowDraftParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @description
 * @param
 * @return
 * @author wangting
 * @date 2018/5/18 0018 14:36
 *
 */
@Service
public class WorkflowDraftService {

   @Autowired
   private WorkflowDraftMapper mapper;

   public Integer insert(WorkflowDraft bean){
       mapper.insertSelective(bean);
       return bean.getId();
   }

   public Integer update(WorkflowDraft bean){
       int row = mapper.updateByPrimaryKeySelective(bean);
       return row;
   }

   public WorkflowDraft get(Integer id){
       return mapper.selectByPrimaryKey(id);
   }

   public List<WorkflowDraft> list(WorkflowDraftParam param){
       return mapper.list(param);
   }

    public Long count(WorkflowDraftParam param){
        return mapper.count(param);
    }

    public int delete(Integer id){
        return mapper.deleteByPrimaryKey(id);
    }

    public List<WorkflowDraft> appList(WorkflowDraftParam param){
        return mapper.appList(param);
    }
}
