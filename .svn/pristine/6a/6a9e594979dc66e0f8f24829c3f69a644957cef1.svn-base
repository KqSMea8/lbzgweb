package com.lyarc.tp.corp.workflow.controller;

import java.time.LocalDateTime;
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
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.workflow.bean.WorkflowApp;
import com.lyarc.tp.corp.workflow.dto.WorkflowAppListDTO;
import com.lyarc.tp.corp.workflow.form.WorkflowAppInfoForm;
import com.lyarc.tp.corp.workflow.form.WorkflowAuthForm;
import com.lyarc.tp.corp.workflow.form.WorkflowMenuForm;
import com.lyarc.tp.corp.workflow.service.WorkflowAppService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/wf/app")
public class WorkflowAppController {

    @Autowired
    private WorkflowAppService service;
    /**
     * 
     * 创建应用
     *
     * @param wf
     * @return
     * 
     * @author jianghan
     * @date 2018年4月24日 下午3:16:00
     */
    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Integer> create(@RequestBody WorkflowApp wf){
        LoginUser loginUser = UserHolder.getUser();
        String corpId = loginUser.getCorpId();
        String useScope = loginUser.getEmpId();
        wf.setUseScope(useScope);
        wf.setCorpId(corpId);
        Integer rult = service.create(wf);
        return ResultVo.success(rult);
    }
    /**
     * 
     * 修改应用基本信息
     *
     * @param form
     * @return
     * 
     * @author jianghan
     * @date 2018年4月24日 下午3:56:51
     */
    @RequestMapping(path = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Integer> update(@RequestBody WorkflowAppInfoForm form){
        WorkflowApp app = JMapperUtils.getDestination(form, WorkflowApp.class, WorkflowAppInfoForm.class);
        app.setUpdateTime(LocalDateTime.now());
        Integer rult = service.update(app);
        return ResultVo.success(rult);
    }
    /**
     * 
     * 设置使用权限
     *
     * @param form
     * @return
     * 
     * @author jianghan
     * @date 2018年4月24日 下午4:16:51
     */
    @RequestMapping(path = "/setAuth", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo setAuth(@RequestBody WorkflowAuthForm form){
        form.setUpdateTime(LocalDateTime.now());
        service.setAuth(form);
        return ResultVo.success();
    }
    /**
     * 
     * 设置菜单
     *
     * @param form
     * @return
     * 
     * @author jianghan
     * @date 2018年4月24日 下午4:18:33
     */
    @RequestMapping(path = "/setMenu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo setMenu(@RequestBody WorkflowMenuForm form){
        form.setUpdateTime(LocalDateTime.now());
        service.setMenu(form);
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
     * @date 2018年4月25日 下午4:18:15
     */
    @RequestMapping(path = "/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<WorkflowApp> get(@RequestParam("id") Integer id){
        WorkflowApp app = service.get(id);
        return ResultVo.success(app);
    }
    /**
     * 
     * 获取应用列表
     *
     * @return
     * 
     * @author jianghan
     * @date 2018年4月26日 上午9:55:54
     */
    @RequestMapping(path = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<WorkflowAppListDTO>> list(){
        LoginUser loginUser = UserHolder.getUser();
        String corpId = loginUser.getCorpId();
        List<WorkflowAppListDTO> list = service.list(corpId);
        return ResultVo.success(list);
    }
    /**
     * 
     * 我的应用
     *
     * @return
     * 
     * @author jianghan
     * @date 2018年6月15日 下午2:50:21
     */
    @RequestMapping(path = "/myApps", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<WorkflowAppListDTO>> myApps(){
        LoginUser loginUser = UserHolder.getUser();
        String corpId = loginUser.getCorpId();
        String empId = loginUser.getEmpId();
        List<WorkflowAppListDTO> list = service.myApps(corpId,empId);
        return ResultVo.success(list);
    }
}
