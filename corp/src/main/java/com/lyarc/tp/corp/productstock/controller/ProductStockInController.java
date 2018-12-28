package com.lyarc.tp.corp.productstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.productstock.bean.ProductStockInBean;
import com.lyarc.tp.corp.productstock.bean.ProductStockInQueryBean;
import com.lyarc.tp.corp.productstock.service.ProductStockInService;

@Controller
@ResponseBody
@RequestMapping("/api/productStockIn")
public class ProductStockInController {
	@Autowired
	private ProductStockInService productStockInService;

	// 列表查询
	@RequestMapping("/list")
	@ResponseBody
	public ResultVo<PageResultBean> List(@RequestBody ProductStockInQueryBean bean) {
		Integer page = bean.getPage();
		Integer pageSize = bean.getPageSize();
		if (page != null && pageSize != null) {
			bean.setOffset((page - 1) * pageSize);
		}
		PageResultBean list = productStockInService.list(bean);
		return ResultVo.success(list);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ResultVo<Integer> add(@RequestBody ProductStockInBean bean){
		Integer rows = productStockInService.add(bean);
		return ResultVo.success(rows);
	}

}
