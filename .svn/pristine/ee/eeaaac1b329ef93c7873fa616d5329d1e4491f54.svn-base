package com.lyarc.tp.corp.purchase.provider.controller;

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
import com.lyarc.tp.corp.purchase.provider.bean.ProviderPayApplyQueryBean;

import com.lyarc.tp.corp.purchase.provider.dto.ProviderPayApplyDto;

import com.lyarc.tp.corp.purchase.provider.form.ProviderPayApplyForm;
import com.lyarc.tp.corp.purchase.provider.service.ProviderPayApplyService;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;

@ResponseBody
@Controller
@RequestMapping("/api/providerPayApply")
public class ProviderPayApplyController {

	@Autowired
	private ProviderPayApplyService providerPayApplyService;

	@RequestMapping("/list")
	@ResponseBody
	public ResultVo<PageResultBean> list(@RequestBody ProviderPayApplyQueryBean queryBean){
		Integer page = queryBean.getPage();
		Integer pageSize = queryBean.getPageSize();
		if (page != null && pageSize != null) {
			queryBean.setOffset((page - 1) * pageSize);
		}
		PageResultBean providerPayApplyList = providerPayApplyService.list(queryBean);
		return ResultVo.success(providerPayApplyList);
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResultVo<Integer> add(@Valid @RequestBody ProviderPayApplyForm providerPayApplyForm){
		Integer id=providerPayApplyService.add(providerPayApplyForm);
		return ResultVo.success(id);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResultVo<Integer> update(@Valid @RequestBody ProviderPayApplyForm providerPayApplyForm){
		Integer id=providerPayApplyService.selectedId(providerPayApplyForm.getPaId());
		if(id==null) {
			return ResultVo.build(400,"该单号不存在");
		}else {
			Integer rows = providerPayApplyService.update(providerPayApplyForm);
			return ResultVo.success(rows);
		}
	}

	@RequestMapping("/submit")
	@ResponseBody
	public ResultVo submit(@RequestBody ProviderPayApplyForm providerPayApplyForm) {
		ProviderPayApplyDto providerPayApply=providerPayApplyService.selectedObject(providerPayApplyForm.getPaId());
		if(providerPayApply.getStatus()==0){
			providerPayApplyService.submit(providerPayApplyForm);
			return ResultVo.success();
		}else {
			return ResultVo.build(400, "该条记录已提交");
		}
	}

	@RequestMapping("/get")
	@ResponseBody
	public ResultVo<ProviderPayApplyDto> get(@RequestParam("paId") String paId){
		if(paId==null) {
			return ResultVo.build(400, "单号不能为空");
		}else {
			Integer id=providerPayApplyService.selectedId(paId);
			if(id==null) {
				return ResultVo.build(400, "该订单号不存在");
			}else {
				ProviderPayApplyDto providerPayApplyDto=providerPayApplyService.getInStock(paId);
				return ResultVo.success(providerPayApplyDto);
			}
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Integer> delete(@RequestParam("paId") String paId) {
		Integer id = providerPayApplyService.selectedId(paId);
		if (id == null) {
			return ResultVo.build(400, "该订单号不存在");
		} else {
			Integer rows = providerPayApplyService.delete(paId);
			return ResultVo.success(rows);
		}
	}
}
