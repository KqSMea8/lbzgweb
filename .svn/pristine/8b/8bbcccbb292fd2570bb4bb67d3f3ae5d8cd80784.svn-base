package com.lyarc.tp.corp.warehouse.controller;


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

import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.warehouse.bean.StorageTempQueryBean;
import com.lyarc.tp.corp.warehouse.dto.StorageTempDto;
import com.lyarc.tp.corp.warehouse.form.StorageTempForm;
import com.lyarc.tp.corp.warehouse.service.StorageTempService;

@ResponseBody
@Controller
@RequestMapping(path="/api/storageTemp")
public class StorageTempController {
	@Autowired
	private StorageTempService storageTempService;
	
	@RequestMapping("/list")
    @ResponseBody
    public ResultVo<PageResultBean> list(@RequestBody StorageTempQueryBean queryBean) {
		Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }
        List<StorageTempDto> storageTempList = storageTempService.list(queryBean);
        Long count = storageTempService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, storageTempList));
    }
	

	@RequestMapping("/add")
	@ResponseBody
	 public ResultVo<Integer> add(@Valid @RequestBody StorageTempForm storageTempForm) {
        StorageTempQueryBean storageTempQueryBean =
        		JMapperUtils.getDestination(storageTempForm,StorageTempQueryBean.class ,StorageTempForm.class);
        Integer id=storageTempService.add(storageTempQueryBean);
        return ResultVo.success(id);
    }
	
	@RequestMapping("/update")
	@ResponseBody
	 public ResultVo<Integer> update(@Valid @RequestBody StorageTempForm storageTempForm) {
        StorageTempQueryBean storageTempQueryBean =
        		JMapperUtils.getDestination(storageTempForm,StorageTempQueryBean.class ,StorageTempForm.class);
        storageTempQueryBean.setUpdateTime(new Date());
        Integer rows=storageTempService.update(storageTempQueryBean);
        return ResultVo.success(rows);
    }
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Integer> delete(@RequestParam("id") Integer id) {
        Integer rows = storageTempService.delete(id);
        return ResultVo.success(rows);
    }
}


















