package com.lyarc.tp.corp.productstock.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.productstock.bean.ProductStock;
import com.lyarc.tp.corp.productstock.bean.ProductStockQueryBean;
import com.lyarc.tp.corp.productstock.service.ProductStockService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/productStock")
public class ProductStockController {
	@Autowired
	private ProductStockService productStockService;

	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public ResultVo<PageResultBean> list(@RequestBody ProductStockQueryBean productStockQueryBean) {
		PageResultBean pageResultBean = productStockService.list(productStockQueryBean);
		return ResultVo.success(pageResultBean);
	}

	// 查询
	@RequestMapping("/batch")
	@ResponseBody
	public ResultVo<Integer> batch(@Valid @RequestBody List<ProductStock> productStockList) {
		Integer id = productStockService.addOrUpdate(productStockList);
		return ResultVo.success(id);
	}

}
