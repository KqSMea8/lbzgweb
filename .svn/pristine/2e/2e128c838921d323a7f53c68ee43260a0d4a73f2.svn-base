package com.lyarc.tp.corp.outprocess.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.outprocess.provider.bean.OutProviderBean;
import com.lyarc.tp.corp.outprocess.provider.service.OutProviderService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/outprovider")
public class OutProviderController {

	@Autowired
	OutProviderService outProviderService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/query", method = RequestMethod.POST)
	public ResultVo<PageResultBean> query(@RequestBody OutProviderBean outProviderBean) {
		PageResultBean pageResultBean = outProviderService.query(outProviderBean);
		return ResultVo.success(pageResultBean);
	}

	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<OutProviderBean> get(@RequestParam("shippingId") String providerId) {
		return ResultVo.success(outProviderService.get(providerId));
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResultVo<Integer> add(@RequestBody OutProviderBean outProviderBean) {
		OutProviderBean outProviderBean2 = outProviderService.get(outProviderBean.getProviderId());
		if (null == outProviderBean2) {
			return ResultVo.success(outProviderService.add(outProviderBean));
		} else {
			return ResultVo.build(400, "厂商代码重复");
		}
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResultVo<Integer> update(@RequestBody OutProviderBean outProviderBean) {
		return ResultVo.success(outProviderService.update(outProviderBean));
	}

}
