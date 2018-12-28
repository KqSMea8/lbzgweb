package com.lyarc.tp.corp.purchase.provider.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceLog;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceQueryBean;
import com.lyarc.tp.corp.purchase.provider.service.ProviderAdjustPriceLogService;

@ResponseBody
@Controller
@RequestMapping("/api/providerAdjustPriceLog")
public class ProviderAdjustPriceLogController {
	@Autowired
	private ProviderAdjustPriceLogService providerAdjustPriceLogService;
	
	@RequestMapping("/get")
	@ResponseBody
	public ResultVo<ProviderAdjustPriceLog> get(@RequestParam("providerId") String providerId){
		ProviderAdjustPriceLog providerAdjustPriceLog=providerAdjustPriceLogService.selectedObject(providerId);
		return ResultVo.success(providerAdjustPriceLog);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ResultVo<Integer> add(@Valid @RequestBody ProviderAdjustPriceLog providerAdjustPriceLog){
		Integer id=providerAdjustPriceLogService.add(providerAdjustPriceLog);
		return ResultVo.success(id);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ResultVo<PageResultBean> list(@RequestBody ProviderAdjustPriceLog queryBean){
		Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }
        PageResultBean providerAdjustPriceList = providerAdjustPriceLogService.list(queryBean);
        return ResultVo.success(providerAdjustPriceList);
	}
}
