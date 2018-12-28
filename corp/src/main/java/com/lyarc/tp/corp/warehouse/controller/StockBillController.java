package com.lyarc.tp.corp.warehouse.controller;

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
import com.lyarc.tp.corp.warehouse.bean.StockBillBean;
import com.lyarc.tp.corp.warehouse.bean.StockBillQueryBean;
import com.lyarc.tp.corp.warehouse.dto.StockBillDTO;
import com.lyarc.tp.corp.warehouse.form.StockBillForm;
import com.lyarc.tp.corp.warehouse.service.StockBillService;

/**
 * 仓库清单控制类
 * 
 * @author shiyong
 *
 */
@ResponseBody
@Controller
@RequestMapping(path = "/api/stock/bill")
public class StockBillController {
	@Autowired
	private StockBillService stockBillService;

	// 列表查询
	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public ResultVo<PageResultBean> List(@RequestBody StockBillQueryBean bean) {
		Integer page = bean.getPage();
		Integer pageSize = bean.getPageSize();
		if (page != null && pageSize != null) {
			bean.setOffset((page - 1) * pageSize);
		}
		PageResultBean list = stockBillService.list(bean);
		return ResultVo.success(list);
	}

	// 新建
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResultVo<String> add(@Valid @RequestBody StockBillBean stockBillBean) {
		LoginUser loginUser = UserHolder.getUser();
		stockBillBean.setCreator(loginUser.getUserId());
		stockBillBean.setOperator(loginUser.getEmpId());
		stockBillBean.setBillDate(LocalDate.now());
		stockBillService.add(stockBillBean);
		return ResultVo.success(stockBillBean.getStockBillId());
	}

	// 单据提交审核
	@RequestMapping(path = "/submit", method = RequestMethod.POST) 
	public ResultVo<Integer> submit(@RequestParam("stockBillId") String stockBillId) {
		if (stockBillId == null) {
			return ResultVo.build(400, "订单号不能为空");
		} else {
			StockBillBean stockBillBean = stockBillService.selectedObject(stockBillId);// 根据单号，返回仓库对象
			if (null == stockBillBean) {
				return ResultVo.build(400, "该订单号不存在");// 1订单号已经提交
			} else {
				Integer status = stockBillBean.getStatus();
				if (status >= 1) {
					return ResultVo.build(400, "该订单已经提交");
				} else {
					Integer rows = stockBillService.publicStatus(stockBillId);
					return ResultVo.success(rows);
				}
			}
		}
	}

	// 更新
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResultVo<Integer> update(@Valid @RequestBody StockBillBean stockBillBean) {
		StockBillBean bill = stockBillService.selectedObject(stockBillBean.getStockBillId());
		if (bill == null) {
			return ResultVo.build(400, "该单据不存在");
		} 
		if (bill.getStatus() != 0) {
			return ResultVo.build(400, "该单据不修改");
		}
		stockBillBean.setStatus(0);
		
		
		
			Integer rows = stockBillService.update(stockBillBean);
			return ResultVo.success(rows);
		
	}

	// 删除
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResultVo<Integer> delete(@Valid @RequestBody StockBillBean stockBillId) {		
		StockBillBean stockBillBean = stockBillService.selectedObject(stockBillId.getStockBillId());
		if (stockBillBean == null) {
			return ResultVo.build(400, "该单据不存在");
		} 
		if (stockBillBean.getStatus()!=0){
			return ResultVo.build(400, "该单据不能删除");
		}
		Integer rows = stockBillService.delete(stockBillId.getStockBillId());
		stockBillService.deleteByInStockId(stockBillId.getStockBillId());
		return ResultVo.success(rows);
		
	}

	// 详情
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<StockBillBean> get(@RequestParam("stockBillId") String stockBillId) {
		StockBillBean bill = stockBillService.getBill(stockBillId);
		return ResultVo.success(bill);
	}

	// 通过
	@RequestMapping(path = "/audit/pass", method = RequestMethod.POST)
	public ResultVo<Integer> pass(@RequestParam("stockBillId") String stockBillId) throws Exception {
		
		StockBillBean bill=stockBillService.getBill(stockBillId);
		if (bill == null) {
			return ResultVo.build(400, "订单号不能为空");
		} 
		if(bill.getStatus()!=1) {
			return ResultVo.build(400, "该订单不是待审核状态");
		}
		Integer rows = stockBillService.passStatus(stockBillId);
			return ResultVo.success(rows);  
	}

	// 驳回
	@RequestMapping(path = "/audit/reject", method = RequestMethod.POST)
	public ResultVo<Integer> reject(@Valid @RequestBody StockBillBean stockBillBean) throws Exception {
		
		StockBillBean bill=stockBillService.getBill(stockBillBean.getStockBillId());
		if (bill == null) {
			return ResultVo.build(400, "订单号不能为空");
		} 
		if(bill.getStatus()!=1) {
			return ResultVo.build(400, "该订单不是待审核状态");
		}
		Integer rows = stockBillService.rejectStatus(stockBillBean.getStockBillId(),stockBillBean.getAuditRemark());
			return ResultVo.success(rows);  
	}
}
