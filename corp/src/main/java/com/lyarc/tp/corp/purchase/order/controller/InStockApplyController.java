package com.lyarc.tp.corp.purchase.order.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.purchase.order.bean.InStockApply;
import com.lyarc.tp.corp.purchase.order.bean.InStockApplyQueryBean;
import com.lyarc.tp.corp.purchase.order.form.InStockApplyForm;
import com.lyarc.tp.corp.purchase.order.form.InStockApplyStatusRejectForm;
import com.lyarc.tp.corp.purchase.order.service.InStockApplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@ResponseBody
@Controller
@RequestMapping(path = "/api/inStockApply")
public class InStockApplyController {

	@Autowired
	private InStockApplyService inStockApplyService;

	@RequestMapping("/list")
	@ResponseBody
	public ResultVo<PageResultBean> list(@RequestBody InStockApplyQueryBean queryBean) {
		Integer page = queryBean.getPage();
		Integer pageSize = queryBean.getPageSize();
		if (page != null && pageSize != null) {
			queryBean.setOffset((page - 1) * pageSize);
		}

		List<InStockApply> inStockApplyList = inStockApplyService.list(queryBean);
		Long count = inStockApplyService.count(queryBean);
		return ResultVo.success(PageResultBean.success(count, inStockApplyList));
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResultVo<Integer> add(@Valid @RequestBody InStockApplyForm inStockApplyForm) {
		LoginUser loginUser = UserHolder.getUser();
		inStockApplyForm.setCreator(loginUser.getUserId());
		inStockApplyForm.setStatus(1);
		Integer id = inStockApplyService.add(inStockApplyForm);
		return ResultVo.success(id);
	}

	@RequestMapping("/get")
	@ResponseBody
	public ResultVo<InStockApply> getInStockApply(@RequestParam("inStockApplyId") String inStockApplyId) {
		InStockApply inStockApply = inStockApplyService.getInStockApply(inStockApplyId);
		return ResultVo.success(inStockApply);
	}

	@RequestMapping("/reject")
	public ResultVo<Integer> reject(@Valid @RequestBody InStockApplyStatusRejectForm form) {
		Integer id = inStockApplyService.selectedId(form.getInStockApplyId());
		if (id == null) {
			return ResultVo.build(400, "该订单号不存在");
		}else {
			InStockApply inStockApply=inStockApplyService.getInStockApply(form.getInStockApplyId());
			Integer status=inStockApply.getStatus();
			if(status != 1) {
				return ResultVo.build(400, "该订单不能驳回");
			}else {
				Integer rows=inStockApplyService.rejectStatus(form);
				return ResultVo.success(rows);
			}
		}
	}
}

