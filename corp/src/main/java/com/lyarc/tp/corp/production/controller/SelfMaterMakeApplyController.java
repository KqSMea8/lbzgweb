package com.lyarc.tp.corp.production.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import com.lyarc.tp.corp.contract.contract.constant.ContractConstant;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.production.bean.SelfMaterMakeApplyBean;
import com.lyarc.tp.corp.production.bean.SelfMaterMakeApplyQueryBean;
import com.lyarc.tp.corp.production.service.SelfMaterMakeApplyService;

@Controller
@ResponseBody
@RequestMapping(path = "/api/selfMaterMakeApply")
public class SelfMaterMakeApplyController {
	@Autowired
	private SelfMaterMakeApplyService selfMaterMakeApplyService;

	/*
	 * 获取列表
	 */
	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public ResultVo<PageResultBean> list(@RequestBody SelfMaterMakeApplyQueryBean selfMaterMakeApplyQueryBean) {
		PageResultBean pageResultBean = selfMaterMakeApplyService.list(selfMaterMakeApplyQueryBean);
		return ResultVo.success(pageResultBean);
	}

	/*
	 * 获取详情
	 */
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<SelfMaterMakeApplyBean> get(@RequestParam("selfMaterMakeApplyId") String selfMaterMakeApplyId) {
		SelfMaterMakeApplyBean bill = selfMaterMakeApplyService.getBill(selfMaterMakeApplyId);
		return ResultVo.success(bill);
	}

	/*
	 * 增添数据
	 */
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResultVo<String> add(@Valid @RequestBody SelfMaterMakeApplyBean selfMaterMakeApplyBean) {
		LoginUser loginUser = UserHolder.getUser();
		selfMaterMakeApplyBean.setCreator(loginUser.getUserId());
		selfMaterMakeApplyService.add(selfMaterMakeApplyBean);
		return ResultVo.success(selfMaterMakeApplyBean.getSelfMaterMakeApplyId());
	}

	/*
	 * 送审
	 */
	@RequestMapping(path = "/submit", method = RequestMethod.POST)
	public ResultVo submit(@RequestBody SelfMaterMakeApplyBean selfMaterMakeApplyBean) {
		SelfMaterMakeApplyBean selfMaterMakeApply = new SelfMaterMakeApplyBean();
		if (StringUtils.isNotEmpty(selfMaterMakeApplyBean.getWfInstId())) {
			selfMaterMakeApply.setWfInstId(selfMaterMakeApplyBean.getWfInstId());
		}
		selfMaterMakeApply.setSelfMaterMakeApplyId(selfMaterMakeApplyBean.getSelfMaterMakeApplyId());
		selfMaterMakeApply.setWfInstTime(LocalDateTime.now());
		selfMaterMakeApply.setStatus(ContractConstant.VERIFYING);// 审核中
		selfMaterMakeApplyService.updateSelfMaterMakeApply(selfMaterMakeApply);
		return ResultVo.success();
	}

	// 更新
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResultVo<Integer> update(@Valid @RequestBody SelfMaterMakeApplyBean selfMaterMakeApplyBean) {
		SelfMaterMakeApplyBean bean = selfMaterMakeApplyService
				.getBill(selfMaterMakeApplyBean.getSelfMaterMakeApplyId());
		if (bean == null) {
			return ResultVo.build(400, "该单据不存在");
		}
		if (bean.getStatus() != 0) {
			return ResultVo.build(400, "该单据不修改");
		}
		selfMaterMakeApplyBean.setStatus(0);
		Integer rows = selfMaterMakeApplyService.update(selfMaterMakeApplyBean);
		return ResultVo.success(rows);
	}

	@RequestMapping("/delete")
	public ResultVo<Integer> delete(@RequestParam("selfMaterMakeApplyId") String selfMaterMakeApplyId) {
		if (selfMaterMakeApplyId == null) {
			return ResultVo.build(400, "单号不能为空");
		} else {
			SelfMaterMakeApplyBean dbSelfMaterMakeApplyBean = selfMaterMakeApplyService.getBill(selfMaterMakeApplyId);
			if (null == dbSelfMaterMakeApplyBean) {
				return ResultVo.build(400, "该单号不存在");
			} else {
				Integer status = dbSelfMaterMakeApplyBean.getStatus();
				if (status >= 1) {
					return ResultVo.build(400, "该申请单已经提交,不能进行删除");
				} else {
					Integer rows = selfMaterMakeApplyService.delete(selfMaterMakeApplyId);
					return ResultVo.success(rows);
				}
			}
		}

	}

	// @RequestMapping("/call")
	// public ResultVo selfMaterMakeAuditCallBack(@RequestBody WorkflowInstEvent
	// event) {
	// WorkflowInstEvent event1 = new WorkflowInstEvent();
	// event1.setInstId("427668");
	// event1.setBusinessKey("201812130005");
	// selfMaterMakeApplyService.selfMaterMakeApplyAuditCallBack(event1);
	// return ResultVo.success();
	// }
}
