package com.lyarc.tp.corp.outprocess.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.outprocess.contract.bean.OutContractDetailBean;
import com.lyarc.tp.corp.outprocess.contract.service.OutContractDetailService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/outcontractdetail")
public class OutContractDetailController {
	
	@Autowired
	OutContractDetailService outContractDetailService;
	
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<OutContractDetailBean> get(@RequestParam("id") Integer id) {
		return ResultVo.success(outContractDetailService.get(id));
	}
	
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResultVo<Integer> update(@RequestBody OutContractDetailBean outContractDetailBean) {
		return ResultVo.success(outContractDetailService.update(outContractDetailBean));
	}
	
	@RequestMapping(path = "/del", method = RequestMethod.POST)
	public ResultVo<Integer> del(@RequestParam("id") Integer id) {
		return ResultVo.success(outContractDetailService.del(id));
	}

}
