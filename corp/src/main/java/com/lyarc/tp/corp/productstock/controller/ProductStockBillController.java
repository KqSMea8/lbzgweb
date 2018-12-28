package com.lyarc.tp.corp.productstock.controller;

import java.time.LocalDate;

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
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.productstock.bean.ProductStockBillBean;
import com.lyarc.tp.corp.productstock.bean.ProductStockBillQueryBean;
import com.lyarc.tp.corp.productstock.service.ProductStockBillService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/productStockBill")
public class ProductStockBillController {
	@Autowired
	private ProductStockBillService productStockBillService;

	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public ResultVo<PageResultBean> list(@RequestBody ProductStockBillQueryBean productStockBillQueryBean) {
		PageResultBean pageResultBean = productStockBillService.list(productStockBillQueryBean);
		return ResultVo.success(pageResultBean);
	}

	// 新建
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResultVo<String> add(@Valid @RequestBody ProductStockBillBean productstockBillBean) {
		LoginUser loginUser = UserHolder.getUser();
		productstockBillBean.setCreator(loginUser.getUserId());
		productstockBillBean.setOperator(loginUser.getEmpId());
		productstockBillBean.setBillDate(LocalDate.now());
		productStockBillService.add(productstockBillBean);
		return ResultVo.success(productstockBillBean.getStockBillId());
	}

	// 更新
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResultVo<Integer> update(@Valid @RequestBody ProductStockBillBean productStockBillBean) {
		ProductStockBillBean bill = productStockBillService.selectedObject(productStockBillBean.getStockBillId());
		if (bill == null) {
			return ResultVo.build(400, "该单据不存在");
		}
		if (bill.getStatus() != 0) {
			return ResultVo.build(400, "该单据不修改");
		}
		productStockBillBean.setStatus(0);

		Integer rows = productStockBillService.update(productStockBillBean);
		return ResultVo.success(rows);

	}

	// 详情
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<ProductStockBillBean> get(@RequestParam("stockBillId") String stockBillId) {
		ProductStockBillBean bill = productStockBillService.getBill(stockBillId);
		return ResultVo.success(bill);
	}

	// 删除
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResultVo<Integer> delete(@Valid @RequestBody ProductStockBillBean productStockBillBean) {
		ProductStockBillBean productStockBill = productStockBillService
				.selectedObject(productStockBillBean.getStockBillId());
		if (productStockBill == null) {
			return ResultVo.build(400, "该单据不存在");
		}
		if (productStockBill.getStatus() != 0) {
			return ResultVo.build(400, "该单据不能删除");
		}

		Integer rows = productStockBillService.delete(productStockBill.getStockBillId());
		return ResultVo.success(rows);
	}

	// 单据提交审核
	@RequestMapping(path = "/send", method = RequestMethod.POST)
	public ResultVo<Integer> submit(@RequestParam("stockBillId") String stockBillId) {
		if (stockBillId == null) {
			return ResultVo.build(400, "订单号不能为空");
		} else {
			ProductStockBillBean productStockBillBean = productStockBillService.selectedObject(stockBillId);// 根据单号，返回仓库对象
			if (null == productStockBillBean) {
				return ResultVo.build(400, "该订单号不存在");// 1订单号已经提交
			} else {
				Integer status = productStockBillBean.getStatus();
				if (status >= 1) {
					return ResultVo.build(400, "该订单已经提交");
				} else {
					Integer rows = productStockBillService.publicStatus(stockBillId);
					return ResultVo.success(rows);
				}
			}
		}
	}

	// 通过
	@RequestMapping(path = "/pass", method = RequestMethod.POST)
	public ResultVo<Integer> pass(@RequestParam("stockBillId") String stockBillId) throws Exception {

		ProductStockBillBean bill = productStockBillService.getBill(stockBillId);
		if (bill == null) {
			return ResultVo.build(400, "订单号不能为空");
		}
		if (bill.getStatus() != 1) {
			return ResultVo.build(400, "该订单不是待审核状态");
		}
		Integer rows = productStockBillService.passStatus(stockBillId);
		return ResultVo.success(rows);
	}

	// 驳回
	@RequestMapping(path = "/reject", method = RequestMethod.POST)
	public ResultVo<Integer> reject(@Valid @RequestBody ProductStockBillBean productStockBillBean) throws Exception {

		ProductStockBillBean bill = productStockBillService.getBill(productStockBillBean.getStockBillId());
		if (bill == null) {
			return ResultVo.build(400, "订单号不能为空");
		}
		if (bill.getStatus() != 1) {
			return ResultVo.build(400, "该订单不是待审核状态");
		}
		Integer rows = productStockBillService.rejectStatus(productStockBillBean.getStockBillId(),productStockBillBean.getAuditRemark());
		return ResultVo.success(rows);
	}
}
