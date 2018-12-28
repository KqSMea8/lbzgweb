package com.lyarc.tp.corp.purchase.provider.controller;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderInquire;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderInquireQueryBean;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderInquireDto;
import com.lyarc.tp.corp.purchase.provider.form.ProviderInquireForm;
import com.lyarc.tp.corp.purchase.provider.service.ProviderInquireService;
import com.lyarc.tp.corp.util.JMapperUtils;

@ResponseBody
@Controller
@RequestMapping(path="/api/providerInquire")
public class ProviderInquireController {
	@Autowired
	private ProviderInquireService providerInquireService;
	
	@RequestMapping("/list")
	@ResponseBody
	public ResultVo<PageResultBean> list(@RequestBody ProviderInquireQueryBean queryBean){
		Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }
        List<ProviderInquireDto> providerInquireList = providerInquireService.list(queryBean);
        Long count = providerInquireService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, providerInquireList));
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public ResultVo<ProviderInquire> detail(@RequestParam("id") Integer id){
		ProviderInquire providerInquire=providerInquireService.getProviderInquire(id);
		return ResultVo.success(providerInquire);	
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ResultVo<Integer> add(@Valid @RequestBody ProviderInquireForm providerInquireForm){
		ProviderInquire providerInquire=JMapperUtils.getDestination(providerInquireForm, ProviderInquire.class, ProviderInquireForm.class);
		Integer id=providerInquireService.add(providerInquire);	
		return ResultVo.success(id);	
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ResultVo<Integer> update(@Valid @RequestBody ProviderInquireForm providerInquireForm){
		ProviderInquire providerInquire=JMapperUtils.getDestination(providerInquireForm, ProviderInquire.class, ProviderInquireForm.class);
		providerInquire.setUpdateTime(new Date());
		Integer rows=providerInquireService.update(providerInquire);
		return ResultVo.success(rows);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Integer> delete(@RequestParam("id") Integer id){
		Integer rows=providerInquireService.delete(id);
		return ResultVo.success(rows);
	}
	
}
