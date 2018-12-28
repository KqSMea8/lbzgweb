package com.lyarc.tp.corp.productstock.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.productstock.bean.ProductStock;
import com.lyarc.tp.corp.productstock.service.ProductStockCheckService;
import com.lyarc.tp.corp.productstock.service.ProductStockService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/productStock/check")
public class ProductStockCheckController {
	@Autowired
	private ProductStockService productStockService;
	@Autowired
	private ProductStockCheckService productStockCheckService;

	/**
	 * 批量设置盘点状态
	 */
	@RequestMapping(path = "/batch", method = RequestMethod.POST)
	public ResultVo<Integer> checkBatchStatus(@Valid @RequestBody List<ProductStock> productStocks) {
		Integer id = productStockService.checkBatchStatus(productStocks);
		return ResultVo.success(id);
	}

	/**
	 * 提交盘点录入的数据
	 */
	@RequestMapping(path = "/submit", method = RequestMethod.POST)
	public ResultVo<Integer> checkBatchStatus(@RequestParam("storageId") String storageId) {
		Integer count = productStockService.checkSubmit(storageId);
		return ResultVo.success(count);
	}

	@RequestMapping("/pass")
	public ResultVo<Integer> pass(@RequestParam("storageId") String storageId) {
		Integer status = productStockCheckService.pass(storageId);
		return ResultVo.success(status);
	}
}
