package com.lyarc.tp.corp.workflow.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.workflow.bean.WorkflowForm;
import com.lyarc.tp.corp.workflow.dto.WorkflowFormListDTO;
import com.lyarc.tp.corp.workflow.form.WorkflowFormFlow;
import com.lyarc.tp.corp.workflow.form.WorkflowFormParam;
import com.lyarc.tp.corp.workflow.service.WorkflowFormService;
import com.lyarc.tp.corp.workflowact.bean.ActProcess;
import com.lyarc.tp.corp.workflowact.service.ActivitiService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/wf/form")
public class WorkflowFormController {

    @Autowired
    private WorkflowFormService service;
    
    @Autowired
    private ActivitiService activitiService;
    /**
     * 
     * 创建表单
     *
     * @param form
     * @return
     * 
     * @author jianghan
     * @date 2018年4月25日 上午11:47:55
     */
    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Integer> create(@RequestBody WorkflowForm form){
        LoginUser user = UserHolder.getUser();
        String corpId = user.getCorpId();
        form.setCorpId(corpId);
        form.setFlowTitle(form.getTitle());
        form.setUseScope(user.getEmpId());
        Integer rult = service.insert(form);
        return ResultVo.success(rult);
    }
    /**
     * 
     * 编辑表单
     *
     * @param form
     * @return
     * 
     * @author jianghan
     * @date 2018年4月25日 上午11:50:42
     */
    @RequestMapping(path = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo update(@RequestBody WorkflowForm form){
        LoginUser user = UserHolder.getUser();
        String corpId = user.getCorpId();
        form.setUpdateTime(LocalDateTime.now());
        service.update(form);
        ActProcess process = new ActProcess();
        process.setCorpId(corpId);
        process.setFormId(form.getId());
        try {
            activitiService.buildBpmnModel(process);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultVo.success();
    } 
    /**
     * 
     * 设置流程
     *
     * @param form
     * @return
     * 
     * @author jianghan
     * @date 2018年4月25日 上午11:51:19
     */
    @RequestMapping(path = "/setFlow", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo setFlow(@RequestBody WorkflowFormFlow form){
        form.setUpdateTime(LocalDateTime.now());
        service.setFlow(form);
        return ResultVo.success();
    }
    
    /**
     * 
     * 获取详情
     *
     * @param id
     * @return
     * 
     * @author jianghan
     * @date 2018年4月25日 上午11:51:19
     */
    @RequestMapping(path = "/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo get(@RequestParam("id") Integer id){
        WorkflowForm form = service.get(id);
        return ResultVo.success(form);
    }
    /**
     * 
     * 表单列表
     *
     * @param param
     * @return
     * 
     * @author jianghan
     * @date 2018年4月26日 上午11:48:06
     */
    @RequestMapping(path = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<WorkflowFormListDTO>> list(@RequestBody WorkflowFormParam param){
        LoginUser user = UserHolder.getUser();
        String corpId = user.getCorpId();
        param.setCorpId(corpId);
        List<WorkflowFormListDTO> list = service.list(param);
        return ResultVo.success(list);
    }
    /**
     * 
     * 我的表单
     *
     * @param param
     * @return
     * 
     * @author jianghan
     * @date 2018年6月15日 下午3:02:11
     */
    @RequestMapping(path = "/myForms", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<WorkflowFormListDTO>> myForms(@RequestBody WorkflowFormParam param){
        LoginUser user = UserHolder.getUser();
        param.setCorpId(user.getCorpId());
        param.setEmpId(user.getEmpId());
        List<WorkflowFormListDTO> list = service.myForms(param);
        return ResultVo.success(list);
    }
    /**
     * 
     * 为应用设置表单
     *
     * @param param
     * @return
     * 
     * @author jianghan
     * @date 2018年5月28日 下午5:44:12
     */
    @RequestMapping(path = "/setForm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo setForm(@RequestBody WorkflowFormParam param){
        Integer[] ids = param.getIds();
        service.setForm(param.getAppId(), param.getIds());
        return ResultVo.success();
    }

     /**
      *
      * @description
      * @param
      * @return
      * @author wangting
      * @date 2018/6/22 0022 13:59
      *
      */
    @RequestMapping(path = "/updateFormProperty", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo updateFormProperty(@RequestBody WorkflowForm form){
        form.setUpdateTime(LocalDateTime.now());
        service.update(form);
        return ResultVo.success();
    }
}
