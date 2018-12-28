package com.lyarc.tp.corp.pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.pack.bean.PackBean;
import com.lyarc.tp.corp.pack.bean.PackQueryBean;
import com.lyarc.tp.corp.pack.service.PackService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/pack")
public class PackController {
	
	@Autowired
	PackService packService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/query", method = RequestMethod.POST)
	public ResultVo<PageResultBean> query(@RequestBody PackQueryBean packQueryBean) {
		PageResultBean pageResultBean = packService.query(packQueryBean);
		return ResultVo.success(pageResultBean);
	}
	
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<PackBean> get(@RequestParam("packId") String packId) {
		return ResultVo.success(packService.get(packId));
	}
	
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResultVo<Integer> add(@RequestBody PackBean packBean) {
		return ResultVo.success(packService.add(packBean));
	}
	
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResultVo<Integer> update(@RequestBody PackBean packBean) {
		return ResultVo.success(packService.update(packBean));
	}
	
	@RequestMapping(path = "/updateStatus", method = RequestMethod.POST)
	public ResultVo<Integer> updateStatus(@RequestBody PackBean packBean) {
		return ResultVo.success(packService.updateStatus(packBean));
	}
	
	@RequestMapping(path = "/del", method = RequestMethod.POST)
	public ResultVo<Integer> del(@RequestBody PackBean packBean) {
		return ResultVo.success(packService.del(packBean.getPackId()));
	}

}
