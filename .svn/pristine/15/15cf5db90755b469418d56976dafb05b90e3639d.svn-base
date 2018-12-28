package com.lyarc.tp.corp.workflow.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.workflowact.bean.ProcessFormQueryBean;
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
import com.lyarc.tp.corp.workflow.bean.WorkflowReport;
import com.lyarc.tp.corp.workflow.dto.WorkflowReportDTO;
import com.lyarc.tp.corp.workflow.dto.WorkflowReportGroupDTO;
import com.lyarc.tp.corp.workflow.service.WorkflowReportService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/wf/report")
public class WorkflowReportController {

    @Autowired
    private WorkflowReportService service;
    /**
     * 
     * 添加/编辑报表
     *
     * @param wp
     * @return
     * 
     * @author jianghan
     * @date 2018年6月4日 下午5:27:39
     */
    @RequestMapping(path = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Integer> edit(@RequestBody WorkflowReport wp){
        LoginUser user = UserHolder.getUser();
        String empId = user.getEmpId();
        if(null == wp.getAuthEmpIds() || wp.getAuthEmpIds().equals("")){
            wp.setAuthEmpIds(empId);
        }
        Integer rult = 0;
        if(null!=wp.getId() && wp.getId()!=0){
            rult = service.update(wp);
        }else{
            rult = service.insert(wp);
        }        
        return ResultVo.success(rult);
    }
    /**
     * 
     * 获取表单信息
     *
     * @param formId
     * @return
     * 
     * @author jianghan
     * @date 2018年6月4日 下午5:38:06
     */
    @RequestMapping(path = "/query", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<WorkflowReport>> query(@RequestParam Integer formId){
        List<WorkflowReport> list = service.list(formId);     
        return ResultVo.success(list);
    }
    
    /**
     * 
     * 获取报表详情
     *
     * @param id
     * @return
     * 
     * @author jianghan
     * @date 2018年6月4日 下午5:49:13
     */
    @RequestMapping(path = "/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<WorkflowReport> get(@RequestParam Integer id){
        LoginUser user = UserHolder.getUser();
        WorkflowReport wfr = service.get(id,user.getCorpId());     
        return ResultVo.success(wfr);
    }
    
    /**
     * 
     * 删除报表
     *
     * @param id
     * @return
     * 
     * @author jianghan
     * @date 2018年6月4日 下午5:50:21
     */
    @RequestMapping(path = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Integer> delete(@RequestParam Integer id){
        Integer rult = service.delete(id);     
        return ResultVo.success(rult);
    }
    /**
     * 
     * 根据权限获取报表信息
     *
     * @return
     * 
     * @author jianghan
     * @date 2018年6月8日 上午11:05:24
     */
    @RequestMapping(path = "/auth", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<WorkflowReportGroupDTO>> auth(){
        LoginUser user = UserHolder.getUser();
        String empId = user.getEmpId();
        List<WorkflowReportGroupDTO> rult = new ArrayList<>();
        List<WorkflowReportDTO> list = service.query(empId);
        Map<Integer, List<WorkflowReportDTO>> groupBy = list.stream()
                                                            .collect(Collectors
                                                            .groupingBy(WorkflowReportDTO::getFormId));
        groupBy.forEach((formId,val)->{
            WorkflowReportGroupDTO group = new WorkflowReportGroupDTO();
            group.setFormId(formId);
            group.setReports(val);
            group.setTitle(val.get(0).getFormTitle());
            rult.add(group);
        });
        return ResultVo.success(rult);
    }



    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo rptData(@RequestBody ProcessFormQueryBean queryBean) {
        Map<String, Object> resMap = null;

        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }
        try {
            resMap = service.getReportFormData(queryBean);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.failure(e.getMessage());
        }
        Long count = (Long)resMap.get("count");
        Object data = resMap.get("data");
        return ResultVo.success(PageResultBean.success(count, data));
    }
}
