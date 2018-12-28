package com.lyarc.tp.corp.aftersales.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.aftersales.bean.ComplaintDisposeBean;
import com.lyarc.tp.corp.aftersales.bean.ComplaintDisposeQueryBean;
import com.lyarc.tp.corp.aftersales.service.ComplaintDisposeService;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;

@ResponseBody
@Controller
@RequestMapping(path = "/api/complaintDispose")
public class ComplaintDisposeController {
	@Autowired
	private ComplaintDisposeService complaintDisposeService;

	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public ResultVo<PageResultBean> list(@RequestBody ComplaintDisposeQueryBean complaintDisposeQueryBean) {
		PageResultBean pageResultBean = complaintDisposeService.list(complaintDisposeQueryBean);
		return ResultVo.success(pageResultBean);
	}

	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<ComplaintDisposeBean> get(@RequestParam("disposeId") String disposeId) {
		ComplaintDisposeBean complaintDisposeBean = complaintDisposeService.get(disposeId);
		return ResultVo.success(complaintDisposeBean);
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResultVo<String> add(@Valid @RequestBody ComplaintDisposeBean complaintDisposeBean) {
		LoginUser loginUser = UserHolder.getUser();
		complaintDisposeBean.setCreator(loginUser.getUserId());
		complaintDisposeBean.setStatus(0);
		complaintDisposeBean.setWfInstId(null);
		complaintDisposeService.add(complaintDisposeBean);
		return ResultVo.success(complaintDisposeBean.getDisposeId());
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResultVo<Integer> update(@Valid @RequestBody ComplaintDisposeBean complaintDisposeBean) {
		ComplaintDisposeBean dbComplaintDispose = complaintDisposeService.get(complaintDisposeBean.getDisposeId());
		if (dbComplaintDispose == null) {
			return ResultVo.build(400, "该单据不存在");
		}
		if (dbComplaintDispose.getStatus() != 0) {
			return ResultVo.build(400, "该单据不修改");
		}
		Integer rows = complaintDisposeService.update(complaintDisposeBean);
		return ResultVo.success(rows);
	}

	@RequestMapping(path = "/submit", method = RequestMethod.POST)
	public ResultVo<Integer> submit(@Valid @RequestBody ComplaintDisposeBean complaintDisposeBean) {
		ComplaintDisposeBean dbComplaintDispose = complaintDisposeService.get(complaintDisposeBean.getDisposeId());
		if (dbComplaintDispose == null) {
			return ResultVo.build(400, "该单据不存在");
		}
		if (dbComplaintDispose.getStatus() != 0) {
			return ResultVo.build(400, "该单据不修改");
		}
		Integer rows = complaintDisposeService.submit(complaintDisposeBean);
		return ResultVo.success(rows);
	}
}
