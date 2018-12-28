package com.lyarc.tp.corp.outprocess.contract.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.outprocess.contract.bean.OutContractBean;
import com.lyarc.tp.corp.outprocess.contract.bean.OutContractQueryBean;
import com.lyarc.tp.corp.outprocess.contract.service.OutContractService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/outcontract")
public class OutContractController {
	
	@Autowired
	OutContractService outContractService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/query", method = RequestMethod.POST)
	public ResultVo<PageResultBean> query(@RequestBody OutContractQueryBean outContractQueryBean) {
		PageResultBean pageResultBean = outContractService.query(outContractQueryBean);
		return ResultVo.success(pageResultBean);
	}
	
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<OutContractBean> get(@RequestParam("contractId") String contractId) {
		return ResultVo.success(outContractService.get(contractId));
	}
	
	@RequestMapping(path = "/getPrevious", method = RequestMethod.POST)
	public ResultVo<OutContractBean> getPrevious() {
		return ResultVo.success(outContractService.getPrevious());
	}
	
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResultVo<Integer> add(@RequestBody OutContractBean outContractBean) {
		return ResultVo.success(outContractService.add(outContractBean));
	}
	
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResultVo<Integer> update(@RequestBody OutContractBean outContractBean) {
		return ResultVo.success(outContractService.update(outContractBean));
	}
	
	@RequestMapping(path = "/del", method = RequestMethod.POST)
	public ResultVo<Integer> del(@RequestBody OutContractBean outContractBean) {
		return ResultVo.success(outContractService.del(outContractBean.getContractId()));
	}
	
	@RequestMapping(path = "/submit", method = RequestMethod.POST)
	public ResultVo<Integer> submit(@Valid @RequestBody OutContractBean outContractBean) {
		OutContractBean oldOutContract = outContractService.get(outContractBean.getContractId());
		if (oldOutContract == null) {
			return ResultVo.build(400, "该单据不存在");
		}
		if (oldOutContract.getStatus() != 0) {
			return ResultVo.build(400, "该单据不修改");
		}
		Integer rows = outContractService.submit(outContractBean);
		return ResultVo.success(rows);
	}

}
