package com.lyarc.tp.corp.contract.contract.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.contract.contract.bean.ContractProject;
import com.lyarc.tp.corp.contract.contract.bean.ContractProjectQueryBean;
import com.lyarc.tp.corp.contract.contract.constant.ContractConstant;
import com.lyarc.tp.corp.contract.contract.dto.ContractProjectDto;
import com.lyarc.tp.corp.contract.contract.form.ContractProjectForm;
import com.lyarc.tp.corp.contract.contract.service.ContractProjectService;
import com.lyarc.tp.corp.login.form.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@ResponseBody
@Controller
@RequestMapping(path = "/api/contractProject")
public class ContractProjectController {

    @Autowired
    private ContractProjectService contractProjectService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<PageResultBean> list(@RequestBody ContractProjectQueryBean queryBean) {
        //LoginUser user = UserHolder.getUser();
        //Integer userId = user.getUserId();
        //String deptId = user.getEmpInfo().getDeptId();
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }
        List<ContractProjectDto> contractList = contractProjectService.list(queryBean);
        Long count = contractProjectService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, contractList));
    }

    @RequestMapping("/waitChange/list")
    @ResponseBody
    public ResultVo<PageResultBean> waitChangeList(@RequestBody ContractProjectQueryBean queryBean) {
        //LoginUser user = UserHolder.getUser();
        //Integer userId = user.getUserId();
        //String deptId = user.getEmpInfo().getDeptId();
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }
        List<ContractProjectDto> contractList = contractProjectService.waitChangeList(queryBean);
        Long count = contractProjectService.waitChangeCount(queryBean);
        return ResultVo.success(PageResultBean.success(count, contractList));
    }

//    @RequestMapping("/add")
//    @ResponseBody
//    public ResultVo<Integer> add(@Valid @RequestBody ContractProjectForm contractProjectForm) {
//        LoginUser loginUser = UserHolder.getUser();
//        contractProjectForm.setCreator(loginUser.getUserId());
//        Integer id = contractProjectService.add(contractProjectForm);
//        return ResultVo.success(id);
//    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultVo<Integer> update(@RequestBody ContractProjectForm contractProjectForm) {
        String contractId = contractProjectForm.getContractId();
        ContractProject existProject = contractProjectService.getContractProjectBean(contractId);
        Integer rows = 0;
        if(existProject == null || StringUtils.isEmpty(existProject.getContractId())){
            LoginUser loginUser = UserHolder.getUser();
            contractProjectForm.setCreator(loginUser.getUserId());
            rows = contractProjectService.add(contractProjectForm);
        }else {
            rows = contractProjectService.update(contractProjectForm);
        }
        return ResultVo.success(rows);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("contractId") String contractId) {
        Integer rows = contractProjectService.delete(contractId);
        return ResultVo.success(rows);
    }

    @RequestMapping("/get")
    @ResponseBody
    public ResultVo<ContractProjectDto> getContractProject(@RequestParam("contractId") String contractId) {
        ContractProjectDto contract = contractProjectService.getContract(contractId);
        return ResultVo.success(contract);
    }

     /**
      *
      * @description 提交审批
      * @param
      * @return
      * @author wangting
      * @date 2018/8/3 0003 13:47
      *
      */
    @RequestMapping("/submit")
    @ResponseBody
    public ResultVo submit(@RequestBody ContractProjectForm contractForm) {
        ContractProject contract = new ContractProject();

        if(StringUtils.isNotEmpty(contractForm.getWfInstId())){
            contract.setWfInstId(contractForm.getWfInstId());
        }
        contract.setContractId(contractForm.getContractId());
        contract.setWfInstTime(LocalDateTime.now());
        contract.setStatus(ContractConstant.VERIFYING);//审核中
        contractProjectService.updateContractProject(contract);
        return ResultVo.success();
    }


    @RequestMapping("/revision/submit")
    @ResponseBody
    public ResultVo submitRevision(@RequestBody ContractProjectForm contractForm) {
        ContractProject contract = new ContractProject();
        if(StringUtils.isNotEmpty(contractForm.getRevisionWfInstId())){
            contract.setRevisionWfInstId(contractForm.getRevisionWfInstId());
        }
        contract.setContractId(contractForm.getContractId());
        contract.setRevisionWfInstTime(LocalDateTime.now());
        contract.setRevisionStatus(ContractConstant.VERIFYING);//审核中
        contractProjectService.updateContractProject(contract);
        return ResultVo.success();
    }

}
