package com.lyarc.tp.corp.purchase.provider.controller;


import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPrice;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceLog;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceQueryBean;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderAdjustPriceDto;
import com.lyarc.tp.corp.purchase.provider.form.ProviderAdjustPriceForm;
import com.lyarc.tp.corp.purchase.provider.service.ProviderAdjustPriceLogService;
import com.lyarc.tp.corp.purchase.provider.service.ProviderAdjustPriceService;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;


@ResponseBody
@Controller
@RequestMapping("/api/providerAdjustPrice")
public class ProviderAdjustPriceController {
	@Autowired
	private ProviderAdjustPriceService providerAdjustPriceService;
	@Autowired
	private ProviderAdjustPriceLogService providerAdjustPriceLogService;
	
	@RequestMapping("/list")
	@ResponseBody
	public ResultVo<PageResultBean> list(@RequestBody ProviderAdjustPriceQueryBean queryBean){
		Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }
        PageResultBean providerAdjustPriceList = providerAdjustPriceService.list(queryBean);
        return ResultVo.success(providerAdjustPriceList);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ResultVo<Integer> add(@Valid @RequestBody ProviderAdjustPriceForm providerAdjustPriceForm){
		Integer id=providerAdjustPriceService.add(providerAdjustPriceForm);
		return ResultVo.success(id);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ResultVo<Integer> update(@Valid @RequestBody ProviderAdjustPriceForm providerAdjustPriceForm){
		Integer id=providerAdjustPriceService.selectedId(providerAdjustPriceForm.getPmapId());
		if(id==null) {
			return ResultVo.build(400,"该调价单号不存在");
		}else {
			Integer rows = providerAdjustPriceService.update(providerAdjustPriceForm);
			return ResultVo.success(rows);
		}
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public ResultVo<ProviderAdjustPriceDto> get(@RequestParam("pmapId") String pmapId){
		if(pmapId==null) {
			return ResultVo.build(400, "调价单号不能为空");
		}else {
			Integer id=providerAdjustPriceService.selectedId(pmapId);
			if(id==null) {
				return ResultVo.build(400, "该订单号不存在");
			}else {
				ProviderAdjustPriceDto providerAdjustPriceDto=providerAdjustPriceService.getInStock(pmapId);
				return ResultVo.success(providerAdjustPriceDto);
			}
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Integer> delete(@Valid @RequestBody ProviderAdjustPriceForm pmapId) {
		Integer id = providerAdjustPriceService.selectedId(pmapId.getPmapId());
		if (id == null) {
			return ResultVo.build(400, "该订单号不存在");
		} else {
			Integer rows = providerAdjustPriceService.delete(pmapId.getPmapId());
			providerAdjustPriceService.deleteByInStockId(pmapId.getPmapId());
			return ResultVo.success(rows);
		}
	}
	
	@RequestMapping("/submit")
    @ResponseBody
    public ResultVo submit(@RequestBody ProviderAdjustPriceForm providerAdjustPriceForm) {
		ProviderAdjustPriceDto providerAdjustPrice=providerAdjustPriceService.selectedObject(providerAdjustPriceForm.getPmapId());
		if(providerAdjustPrice.getStatus()==0){
        	providerAdjustPriceService.submit(providerAdjustPriceForm);
        	return ResultVo.success();
       }else {
        	return ResultVo.build(400, "该条记录已提交");
        }
    }
}
