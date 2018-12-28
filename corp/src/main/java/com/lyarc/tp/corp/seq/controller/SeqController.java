package com.lyarc.tp.corp.seq.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.seq.service.SeqService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/seq")
public class SeqController {

	@Autowired
	private SeqService seqService;

	/**
	 * 生成仓库单据号
	 * @param operType 库存类型：1.入库 2.出库 3.移库 4.调库
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ResultVo<String> add(@RequestParam("operType") Integer operType) {
		String seq = seqService.getStockBillId(operType, new Date());
		return ResultVo.success(seq);
	}

}
