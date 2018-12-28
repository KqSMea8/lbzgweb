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
import com.lyarc.tp.corp.aftersales.bean.ComplaintBean;
import com.lyarc.tp.corp.aftersales.bean.ComplaintQueryBean;
import com.lyarc.tp.corp.aftersales.service.ComplaintService;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;

@ResponseBody
@Controller
@RequestMapping(path = "/api/complaint")
public class ComplaintController {
	@Autowired
	private ComplaintService complaintService;

	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public ResultVo<PageResultBean> list(@RequestBody ComplaintQueryBean complaintQueryBean) {
		PageResultBean pageResultBean = complaintService.list(complaintQueryBean);
		return ResultVo.success(pageResultBean);
	}

	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<ComplaintBean> get(@RequestParam("complaintId") String complaintId) {
		ComplaintBean complaintBean = complaintService.get(complaintId);
		return ResultVo.success(complaintBean);
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResultVo<String> add(@Valid @RequestBody ComplaintBean complaintBean) {
		LoginUser loginUser = UserHolder.getUser();
		complaintBean.setCreator(loginUser.getUserId());
		complaintService.add(complaintBean);
		return ResultVo.success(complaintBean.getComplaintId());
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResultVo<Integer> update(@Valid @RequestBody ComplaintBean complaintBean) {
		ComplaintBean dbcomplaint = complaintService.get(complaintBean.getComplaintId());
		if (dbcomplaint == null) {
			return ResultVo.build(400, "该单据不存在");
		}
		if (dbcomplaint.getStatus() != 0) {
			return ResultVo.build(400, "该单据不修改");
		}
		Integer rows = complaintService.update(complaintBean);
		return ResultVo.success(rows);
	}

	@RequestMapping(path = "/submit", method = RequestMethod.POST)
	public ResultVo<Integer> submit(@Valid @RequestBody ComplaintBean complaintBean) {
		ComplaintBean dbcomplaint = complaintService.get(complaintBean.getComplaintId());
		if (dbcomplaint == null) {
			return ResultVo.build(400, "该单据不存在");
		}
		if (dbcomplaint.getStatus() != 0) {
			return ResultVo.build(400, "该单据不修改");
		}
		Integer rows = complaintService.submit(complaintBean);
		return ResultVo.success(rows);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResultVo<Integer> delete(@Valid @RequestBody ComplaintBean complaintBean) {
		ComplaintBean dbComplaint = complaintService.get(complaintBean.getComplaintId());
		if (dbComplaint == null) {
			return ResultVo.build(400, "该单据不存在");
		}
		if (dbComplaint.getStatus() != 0) {
			return ResultVo.build(400, "该单据不能删除");
		}
		Integer rows = complaintService.delete(complaintBean.getComplaintId());
		return ResultVo.success(rows);
	}
}
