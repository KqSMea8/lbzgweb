package com.lyarc.tp.corp.production.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.production.bean.OutStockApply;
import com.lyarc.tp.corp.production.bean.OutStockApplyQueryBean;
import com.lyarc.tp.corp.production.service.OutStockApplyService;
import com.lyarc.tp.corp.warehouse.bean.StockBillBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@ResponseBody
@Controller
@RequestMapping(path = "/api/outStockApply")
public class OutStockApplyController {

	@Autowired
	private OutStockApplyService outStockApplyService;

	@RequestMapping("/list")
	@ResponseBody
	public ResultVo<PageResultBean> list(@RequestBody OutStockApplyQueryBean queryBean) {
		Integer page = queryBean.getPage();
		Integer pageSize = queryBean.getPageSize();
		if (page != null && pageSize != null) {
			queryBean.setOffset((page - 1) * pageSize);
		}

		List<OutStockApply> outStockApplyList = outStockApplyService.list(queryBean);
		Long count = outStockApplyService.count(queryBean);
		return ResultVo.success(PageResultBean.success(count, outStockApplyList));
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResultVo<Integer> add(@RequestBody OutStockApply outStockApply) {
		LoginUser loginUser = UserHolder.getUser();
		outStockApply.setCreator(loginUser.getUserId());
		outStockApply.setStatus(0);
		Integer id = outStockApplyService.add(outStockApply);
		return ResultVo.success(id);
	}

	// 单据提交审核
	@RequestMapping(path = "/submit", method = RequestMethod.POST)
	public ResultVo<Integer> submit(@RequestParam("outStockApplyId") String outStockApplyId) {
		if (outStockApplyId == null) {
			return ResultVo.build(400, "单号不能为空");
		} else {
			OutStockApply outStockApply = outStockApplyService.getOutStockApply(outStockApplyId);// 根据单号，返回对象
			if (null == outStockApply) {
				return ResultVo.build(400, "该单号不存在");
			} else {
				Integer status = outStockApply.getStatus();
				if (status >= 1) {
					return ResultVo.build(400, "该申请单已经提交");
				} else {
					Integer rows = outStockApplyService.submitStatus(outStockApplyId);
					return ResultVo.success(rows);
				}
			}
		}
	}

	@RequestMapping("/get")
	@ResponseBody
	public ResultVo<OutStockApply> getOutStockApply(@RequestParam("outStockApplyId") String outStockApplyId) {
		if (outStockApplyId == null) {
			return ResultVo.build(400, "单号不能为空");
		} else {
			OutStockApply outStockApply = outStockApplyService.getOutStockApply(outStockApplyId);
			return ResultVo.success(outStockApply);
		}
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResultVo<Integer> update(@RequestBody OutStockApply outStockApply) {
		if (outStockApply.getOutStockApplyId() == null) {
			return ResultVo.build(400, "单号不能为空");
		} else {
			OutStockApply outStockApply1 = outStockApplyService.getOutStockApply(outStockApply.getOutStockApplyId());
			if (null == outStockApply1) {
				return ResultVo.build(400, "该单号不存在");
			} else {
				Integer status = outStockApply1.getStatus();
				if (status >= 1) {
					return ResultVo.build(400, "该申请单已经提交,不能进行编辑");
				} else {
					Integer rows = outStockApplyService.update(outStockApply);
					return ResultVo.success(rows);
				}
			}
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Integer> delete(@RequestParam("outStockApplyId") String outStockApplyId) {
		if (outStockApplyId == null) {
			return ResultVo.build(400, "单号不能为空");
		} else {
			OutStockApply outStockApply = outStockApplyService.getOutStockApply(outStockApplyId);
			if (null == outStockApply) {
				return ResultVo.build(400, "该单号不存在");
			} else {
				Integer status = outStockApply.getStatus();
				if (status >= 1) {
					return ResultVo.build(400, "该申请单已经提交,不能进行删除");
				} else {
					Integer rows = outStockApplyService.delete(outStockApplyId);
					return ResultVo.success(rows);
				}
			}
		}
	}

	@RequestMapping("/reject")
	@ResponseBody
	public ResultVo<Integer> reject(@RequestBody OutStockApply outStockApply) {
		Integer id = outStockApplyService.selectedId(outStockApply.getOutStockApplyId());
		if (id == null) {
			return ResultVo.build(400, "该单号不存在");
		} else {
			outStockApply = outStockApplyService.getOutStockApply(outStockApply.getOutStockApplyId());
			Integer status = outStockApply.getStatus();
			if (status != 1) {
				return ResultVo.build(400, "该申请单不能驳回");
			} else {
				Integer rows = outStockApplyService.rejectStatus(outStockApply);
				return ResultVo.success(rows);
			}
		}
	}
}
