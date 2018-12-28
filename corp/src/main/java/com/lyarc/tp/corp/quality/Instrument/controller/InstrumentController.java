package com.lyarc.tp.corp.quality.Instrument.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.quality.Instrument.bean.Instrument;
import com.lyarc.tp.corp.quality.Instrument.bean.InstrumentQueryBean;
import com.lyarc.tp.corp.quality.Instrument.form.InstrumentForm;
import com.lyarc.tp.corp.quality.Instrument.service.InstrumentService;
import com.lyarc.tp.corp.warehouse.bean.StockBillDetail;
import com.lyarc.tp.corp.warehouse.bean.StockBillQueryBean;

/**
 * 计量器具
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping("/api/instrument")
public class InstrumentController {
	@Autowired
	private InstrumentService instrumentService;
	/**
	 *	首页分页
	 */
	@RequestMapping(value = "/list",method = {RequestMethod.POST, RequestMethod.GET})/*,method=RequestMethod.POST path=*/
    @ResponseBody
    public ResultVo<PageResultBean> listInstrument(@RequestBody InstrumentQueryBean queryBean) {
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
        	queryBean.setOffset((page - 1) * pageSize);
        }

        List<Instrument>  instrumentList = instrumentService.list(queryBean);
        Long count = instrumentService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, instrumentList));
    }
	
	/**
	 * 修改
	 * @param instrumentForm
	 * @return
	 */
	 @RequestMapping(value = "/update",method = {RequestMethod.POST, RequestMethod.GET})
	    @ResponseBody
	    public ResultVo<Integer> update(@RequestBody Instrument instrument) {
	        Integer rows = instrumentService.updateByPmaryKey(instrument);
	        return ResultVo.success(rows);
	    }
	

	
	/**
	 * 根据计量器具编号查询
	 */
	@RequestMapping(value = "/select",method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResultVo<List<Instrument>> selectId(@RequestBody Instrument instrumentId){
		List<Instrument> listInstrument = instrumentService.selectId(instrumentId);
		return ResultVo.success(listInstrument);
	}
	 
	/**
	 * 添加计量器具
	 */
	@RequestMapping(value = "/add",method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResultVo<Integer> add(@RequestBody InstrumentForm InstrumentForm) {
		InstrumentForm.setStatus(2);
		int row = instrumentService.add(InstrumentForm);
		return ResultVo.success(row);
	}
	
	/**
	 * 导入物料
	 */
	@RequestMapping(value = "/find",method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResultVo<PageResultBean> find(@RequestBody InstrumentQueryBean bean){
		Integer page = bean.getPage();
		Integer pageSize = bean.getPageSize();
		if (page != null && pageSize != null) {
			bean.setOffset((page - 1) * pageSize);
		}		
		List<StockBillDetail> listStockBillDetail = instrumentService.find(bean);
		Long counts = instrumentService.countSBD(bean);
		return ResultVo.success(PageResultBean.success(counts, listStockBillDetail));
	}
	
	/**
	 * 报废状态变更
	 */
	@RequestMapping(value = "/updateUseCase",method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResultVo<Integer> updateUseCase(@RequestBody Instrument record){
		Integer row = instrumentService.updateUseCase(record);
		return ResultVo.success(row);
	}
}
