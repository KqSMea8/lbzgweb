package com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean.ExternalInspectionApply;
import com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.bean.InstrumentInspectionAccept;
import com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.form.InstrumentInspectionAcceptForm;
import com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.service.InstrumentInspectionAcceptService;
/**
 * 外检返厂验收单
 * @author Administrator
 *
 */
@Controller
@RequestMapping(path="api/InstrumentInspectionAccept")
@ResponseBody
public class InstrumentInspectionAcceptController {
	@Autowired
	public InstrumentInspectionAcceptService instrumentInspectionAcceptServic;
	
	/**
	 * 创建外检返厂验收单
	 * @param record
	 * @return
	 */
	@RequestMapping("/insertIIA")
	@ResponseBody
	public ResultVo<Integer> insert(@RequestBody InstrumentInspectionAccept record) {
		int id = instrumentInspectionAcceptServic.insert(record);
		return ResultVo.success(id);
	}
	
	/**
	 * 删除
	 * @param inspectionAcceptId
	 * @return
	 */
	@RequestMapping("/deleteIIA")
	@ResponseBody
	public ResultVo<Integer> delete(String inspectionAcceptId){
		int id = instrumentInspectionAcceptServic.delete(inspectionAcceptId);
		return ResultVo.success(id);
	}
	

}
