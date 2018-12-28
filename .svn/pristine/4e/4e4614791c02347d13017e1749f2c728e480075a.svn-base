package com.lyarc.tp.corp.warehouse.controller;

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
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.warehouse.bean.Stock;
import com.lyarc.tp.corp.warehouse.form.StockForm;
import com.lyarc.tp.corp.warehouse.service.StockCheckService;
import com.lyarc.tp.corp.warehouse.service.StockService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/storage/stock/check")
public class StockCheckController {

	@Autowired
	private StockService stockService;
	@Autowired
	private StockCheckService stockCheckService;

	/**
	 * 批量设置盘点状态
	 */
	@RequestMapping(path = "/batch", method = RequestMethod.POST)
	public ResultVo<Integer> checkBatchStatus(@Valid @RequestBody List<StockForm> stockList) {
		List<Stock> stocks = JMapperUtils.getDestinations(stockList, Stock.class, StockForm.class);
		Integer id = stockService.checkBatchStatus(stocks);
		return ResultVo.success(id);
	}

	/**
	 * 提交盘点录入的数据
	 */
	@RequestMapping(path = "/submit", method = RequestMethod.POST)
	public ResultVo<Integer> checkBatchStatus(@RequestParam("storageId") String storageId) {
		Integer count = stockService.checkSubmit(storageId);
		return ResultVo.success(count);
	}

	@RequestMapping("/pass")
	public ResultVo<Integer> pass(@RequestParam("storageId") String storageId) {
		Integer status = stockCheckService.pass(storageId);
		return ResultVo.success(status);
	}
}
