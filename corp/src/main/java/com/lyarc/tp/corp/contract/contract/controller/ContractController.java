package com.lyarc.tp.corp.contract.contract.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.contract.contract.bean.ContractQueryBean;
import com.lyarc.tp.corp.contract.contract.constant.ContractConstant;
import com.lyarc.tp.corp.contract.contract.form.ContractForm;
import com.lyarc.tp.corp.contract.contract.service.ContractService;
import com.lyarc.tp.corp.login.form.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@ResponseBody
@Controller
@RequestMapping(path = "/api/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;
    
    //外贸部（随便先弄了一个测试）
    private static String FOREIGN_DEPT = "54";

    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<PageResultBean> list(@RequestBody ContractQueryBean queryBean) {
        LoginUser user = UserHolder.getUser();
        //Integer userId = user.getUserId();
        String deptId = user.getEmpInfo().getDeptId();
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }
        //外贸部门 可查询所有外贸合同
        if(FOREIGN_DEPT.equals(deptId)){
            queryBean.setForeignFlag(1);
        }else {
            //暂时去掉数据权限方便测试
//            queryBean.setCreator(userId);
        }

        List<Contract> contractList = contractService.list(queryBean);
        Long count = contractService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, contractList));
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultVo<Integer> add(@Valid @RequestBody Contract contract) {
        LoginUser loginUser = UserHolder.getUser();
        contract.setCreator(loginUser.getUserId());
        Integer id = contractService.add(contract);
        return ResultVo.success(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultVo<Integer> update(@RequestBody Contract contract) {
        Integer rows = contractService.update(contract);
        return ResultVo.success(rows);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("contractId") String contractId) {
        Integer rows = contractService.delete(contractId);
        return ResultVo.success(rows);
    }

    @RequestMapping("/get")
    @ResponseBody
    public ResultVo<Contract> getContract(@RequestParam("contractId") String contractId) {
        Contract contract = contractService.getContract(contractId);
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
    public ResultVo submit(@RequestBody ContractForm contractForm) {
//        ContractDto contract = contractService.getContract(contractId);
//        return ResultVo.success(contract);
        Contract contract = new Contract();

        if(StringUtils.isNotEmpty(contractForm.getWfInstId())){
            contract.setWfInstId(contractForm.getWfInstId());
        }
        contract.setContractId(contractForm.getContractId());
        contract.setWfInstTime(LocalDateTime.now());
        contract.setStatus(ContractConstant.VERIFYING);//审核中
        contractService.updateContract(contract);
        return ResultVo.success();
    }


    @RequestMapping("/revision/submit")
    @ResponseBody
    public ResultVo submitRevision(@RequestBody ContractForm contractForm) {
        Contract contract = new Contract();
        if(StringUtils.isNotEmpty(contractForm.getRevisionWfInstId())){
            contract.setRevisionWfInstId(contractForm.getRevisionWfInstId());
        }
        contract.setContractId(contractForm.getContractId());
        contract.setRevisionWfInstTime(LocalDateTime.now());
        contract.setRevisionStatus(ContractConstant.VERIFYING);//审核中
        contractService.updateContract(contract);
        return ResultVo.success();
    }

    @RequestMapping("/undo/submit")
    @ResponseBody
    public ResultVo submitUndo(@RequestBody ContractForm contractForm) {
        Contract contract = new Contract();

        if(StringUtils.isNotEmpty(contractForm.getUndoWfInstId())){
            contract.setUndoWfInstId(contractForm.getUndoWfInstId());
        }
        contract.setContractId(contractForm.getContractId());
        contract.setUndoWfInstTime(LocalDateTime.now());
        contract.setUndoStatus(ContractConstant.VERIFYING);//审核中
        contractService.updateContract(contract);
        return ResultVo.success();
    }

}
