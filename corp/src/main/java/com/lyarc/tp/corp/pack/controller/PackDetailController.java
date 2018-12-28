package com.lyarc.tp.corp.pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.pack.bean.PackDetailBean;
import com.lyarc.tp.corp.pack.service.PackDetailService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/packDetail")
public class PackDetailController {
	
	@Autowired
	PackDetailService packDetailService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/query", method = RequestMethod.POST)
	public ResultVo<PageResultBean> query(@RequestParam("packId") String packId) {
		PageResultBean pageResultBean = packDetailService.query(packId);
		return ResultVo.success(pageResultBean);
	}
	
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<PackDetailBean> get(@RequestParam("id") Integer id) {
		return ResultVo.success(packDetailService.get(id));
	}

}
