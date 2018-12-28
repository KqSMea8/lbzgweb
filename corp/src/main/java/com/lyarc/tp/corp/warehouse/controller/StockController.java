package com.lyarc.tp.corp.warehouse.controller;

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
import com.lyarc.tp.corp.warehouse.bean.Stock;
import com.lyarc.tp.corp.warehouse.bean.StockKey;
import com.lyarc.tp.corp.warehouse.bean.StockQueryBean;
import com.lyarc.tp.corp.warehouse.form.StockForm;
import com.lyarc.tp.corp.warehouse.service.StockService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/storage/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@RequestMapping("/list")
	@ResponseBody
	public ResultVo<PageResultBean> list(@RequestBody StockQueryBean stock) {
		PageResultBean pageResultBean = stockService.list(stock);
		return ResultVo.success(pageResultBean);
	}
	
	@RequestMapping("/listMater")
	@ResponseBody
	public ResultVo<PageResultBean> listMater(@RequestBody StockQueryBean stock) {
		PageResultBean pageResultBean = stockService.listMater(stock);
		return ResultVo.success(pageResultBean);
	}

	@RequestMapping("/batch")
	@ResponseBody
	public ResultVo<Integer> batch(@Valid @RequestBody List<StockForm> stockList) {
		List<Stock> stocks = JMapperUtils.getDestinations(stockList, Stock.class, StockForm.class);
		Integer id = stockService.addOrUpdate(stocks);
		return ResultVo.success(id);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Integer> delete(@RequestParam("storageId") String storageId,
			@RequestParam("placeId") String placeId, @RequestParam("materId") String materId) {
		StockKey key = new StockKey();
		key.setPlaceId(placeId);
		key.setStorageId(storageId);
		key.setMaterId(materId);
		Integer rows = stockService.delete(key);
		return ResultVo.success(rows);
	}

}
