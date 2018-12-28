package com.lyarc.tp.corp.workflow.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.workflow.bean.WorkflowDraft;
import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.dao.WorkflowDraftMapper;
import com.lyarc.tp.corp.workflow.dao.WorkflowOperLogMapper;
import com.lyarc.tp.corp.workflow.form.WorkflowDraftParam;
import com.lyarc.tp.corp.workflow.form.WorkflowOperLogParam;
import com.lyarc.tp.corp.workflowact.constant.OperLogEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @description
 * @param
 * @return
 * @author wangting
 * @date 2018/5/30 0030 11:20
 *
 */
@Service
public class WorkflowOperLogService {

  @Autowired
  private WorkflowOperLogMapper mapper;

  public Integer insert(WorkflowOperLog bean){
      mapper.insertSelective(bean);
      return bean.getId();
  }

  public Integer update(WorkflowOperLog bean){
      int row = mapper.updateByPrimaryKeySelective(bean);
      return row;
  }

  public WorkflowOperLog get(Integer id){
      return mapper.selectByPrimaryKey(id);
  }

  public List<WorkflowOperLog> list(WorkflowOperLogParam param) throws Exception{
      if(StringUtils.isEmpty(param.getInstId())){
          throw new CorpException(ResultVo.failure("流程实例id不能为空"));
      }
      if(StringUtils.isEmpty(param.getCorpId())){
          throw new CorpException(ResultVo.failure("请求参数不完整"));
      }
      List<WorkflowOperLog> list = mapper.list(param);
      for (WorkflowOperLog operLog : list) {
          String typeName = OperLogEnum.getName(operLog.getOperType());
          operLog.setOperTypeName(typeName);
      }
      return list;
  }

   public int delete(Integer id){
       return mapper.deleteByPrimaryKey(id);
   }
}
