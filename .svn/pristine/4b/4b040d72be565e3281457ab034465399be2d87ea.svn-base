package com.lyarc.tp.corp.workflow.controller;

import com.alibaba.fastjson.JSON;
import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.workflow.bean.WorkflowDraft;
import com.lyarc.tp.corp.workflow.form.WorkflowDraftParam;
import com.lyarc.tp.corp.workflow.service.WorkflowDraftService;
import jodd.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@ResponseBody
@Controller
@RequestMapping(path = "/api/wf/draft")
public class WorkflowDraftController {

    @Autowired
    private WorkflowDraftService service;
     /** 
      *  
      * @description 创建或编辑草稿
      * @param  
      * @return 
      * @author wangting  
      * @date 2018/5/18 0018 14:59   
      *  
      */ 
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResultVo create(@RequestBody Map parameterMap, HttpServletRequest request){
        LoginUser user = UserHolder.getUser();
        String corpId = user.getCorpId();
        String empId = user.getEmpId();
        WorkflowDraft wfd = new WorkflowDraft();
        wfd.setCorpId(corpId);
        wfd.setEmpId(empId);


        Map<String, Object> formProperties = new HashMap<String, Object>();
        Integer id = null;
        Integer formId = null;
        // 从request中读取参数然后转换
        Set entrySet = parameterMap.entrySet();
        for (Object o : entrySet) {
            Map.Entry entry = (Map.Entry) o;
            String key = entry.getKey().toString();
            if(key.startsWith("fp_")){
                key = key.replaceFirst("fp_", "");
                formProperties.put(key, entry.getValue()==null ? "" : entry.getValue().toString());
            }else if("id".equals(key)){
                id = Integer.parseInt(entry.getValue()==null ? "0" : entry.getValue().toString());
            }else if("formId".equals(key)){
                formId = Integer.parseInt(entry.getValue()==null ? "0" : entry.getValue().toString());
            }
        }
        String fields = JSON.toJSONString(formProperties);
        wfd.setFieldsJson(fields);
        wfd.setFormId(formId);
        Integer rult = 0;
        if(id == null){
            rult = service.insert(wfd);
        }else{
            WorkflowDraft workflowDraft = service.get(id);
            if(empId.equals(workflowDraft.getEmpId()) && corpId.equals(workflowDraft.getCorpId())){
                workflowDraft.setUpdateTime(new Date());
                workflowDraft.setFieldsJson(fields);
                service.update(workflowDraft);
                rult = id;
            }else{
                return ResultVo.failure("无权操作该表单");
            }
        }
        return ResultVo.success(rult);
    }
    
     /**
      *
      * @description 获取草稿记录
      * @param
      * @return
      * @author wangting
      * @date 2018/5/18 0018 15:07
      *
      */
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResultVo get(@RequestParam("id") Integer id){
        WorkflowDraft draft = service.get(id);
        return ResultVo.success(draft);
    }

     /**
      *
      * @description 我的待发送列表
      * @param
      * @return
      * @author wangting
      * @date 2018/5/18 0018 15:07
      *
      */
    @RequestMapping(path = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<WorkflowDraft>> list(){
        LoginUser user = UserHolder.getUser();
        String corpId = user.getCorpId();
        WorkflowDraftParam param = new WorkflowDraftParam();
        param.setCorpId(corpId);
        param.setEmpId(user.getEmpId());
        List<WorkflowDraft> list = service.list(param);
        return ResultVo.success(list);
    }
}
