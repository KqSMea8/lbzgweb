package com.lyarc.tp.corp.contract.shipping.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.contract.contract.dao.ContractMapper;
import com.lyarc.tp.corp.contract.shipping.bean.ShippingBean;
import com.lyarc.tp.corp.contract.shipping.bean.ShippingQueryBean;
import com.lyarc.tp.corp.contract.shipping.service.ShippingSerivice;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;

@ResponseBody
@Controller
@RequestMapping(path = "/api/shipping")
public class ShippingController {
	@Autowired
	private ShippingSerivice shippingSerivice;
	@Autowired
	private ContractMapper contractMapper;

	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public ResultVo<PageResultBean> list(@RequestBody ShippingQueryBean shippingQueryBean) {

		PageResultBean pageResultBean = shippingSerivice.list(shippingQueryBean);
		return ResultVo.success(pageResultBean);
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResultVo<String> add(@Valid @RequestBody ShippingBean shippingBean) {
		LoginUser loginUser = UserHolder.getUser();
		shippingBean.setCreator(loginUser.getUserId());
		Contract contract = new Contract();
		contract = contractMapper.selectByPrimaryKey(shippingBean.getContractId());
		if (contract.getShippingStatus() == 0) {
			contract.setShippingStatus(1);
			contractMapper.updateByPrimaryKeySelective(contract);
			shippingSerivice.add(shippingBean);
		}
		return ResultVo.success(shippingBean.getShippingId());
	}

	// 更新
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResultVo<Integer> update(@Valid @RequestBody ShippingBean shippingBean) {
		ShippingBean bean = shippingSerivice.selectedObject(shippingBean.getShippingId());
		if (bean == null) {
			return ResultVo.build(400, "该单据不存在");
		}
		if (bean.getStatus() != 0) {
			return ResultVo.build(400, "该单据不修改");
		}
		shippingBean.setStatus(0);
		Integer rows = shippingSerivice.update(shippingBean);
		return ResultVo.success(rows);
	}

	// 详情
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<ShippingBean> get(@RequestParam("shippingId") String shippingId) {
		ShippingBean bill = shippingSerivice.getBill(shippingId);
		return ResultVo.success(bill);
	}

	// 单据提交审核
	@RequestMapping(path = "/submit", method = RequestMethod.POST)
	public ResultVo submit(@RequestBody ShippingBean shippingBean) {
		shippingSerivice.updateShipping(shippingBean);
		return ResultVo.success();
	}
}
