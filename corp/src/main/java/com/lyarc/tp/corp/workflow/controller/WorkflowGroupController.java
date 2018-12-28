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
import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.service.EmpService;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.workflow.bean.WorkflowGroup;
import com.lyarc.tp.corp.workflow.dto.WorkflowGroupListDTO;
import com.lyarc.tp.corp.workflow.service.WorkflowGroupService;

/**
 * 
 * 自定义角色
 * 
 * @version 1.0
 * @since JDK1.8
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2018 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2018年5月22日 下午2:33:59
 */
@ResponseBody
@Controller
@RequestMapping(path = "/api/wf/group")
public class WorkflowGroupController {

    @Autowired
    private WorkflowGroupService service;
    
    /**
     * 
     * 新建角色
     *
     * @param wfGroup
     * @return
     * 
     * @author jianghan
     * @date 2018年5月22日 下午2:37:25
     */
    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Integer> create(@RequestBody WorkflowGroup wfGroup){
        LoginUser user = UserHolder.getUser();
        wfGroup.setCorpId(user.getCorpId());
        Integer rult = service.insert(wfGroup); 
        return ResultVo.success(rult);
    }
    /**
     * 
     * 编辑角色
     *
     * @param wfGroup
     * @return
     * 
     * @author jianghan
     * @date 2018年5月22日 下午3:14:11
     */
    @RequestMapping(path = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Integer> update(@RequestBody WorkflowGroup wfGroup){
        LoginUser user = UserHolder.getUser();
        wfGroup.setCorpId(user.getCorpId());
        wfGroup.setUpdateTime(LocalDateTime.now());
        Integer rult = service.update(wfGroup);
        return ResultVo.success(rult);
    }
    /**
     * 
     * 删除角色
     *
     * @param id
     * @return
     * 
     * @author jianghan
     * @date 2018年5月22日 下午3:39:28
     */
    @RequestMapping(path = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo delete(@RequestParam Integer id){
        service.delete(id);
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
     * @date 2018年5月22日 下午4:02:10
     */
    @RequestMapping(path = "/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<WorkflowGroup> get(@RequestParam Integer id){
        WorkflowGroup group = service.get(id);
        return ResultVo.success(group);
    }
    /**
     * 
     * 角色列表
     *
     * @return
     * 
     * @author jianghan
     * @date 2018年5月22日 下午4:26:39
     */
    @RequestMapping(path = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<WorkflowGroupListDTO>> list(){
        LoginUser user = UserHolder.getUser();
        WorkflowGroup wfGroup = new WorkflowGroup();
        wfGroup.setCorpId(user.getCorpId());
        List<WorkflowGroupListDTO> list = service.list(wfGroup);
        return ResultVo.success(list);
    }

}
