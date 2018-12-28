package com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.contract.contract.constant.ContractConstant;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean.ExternalInspectionApply;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean.ExternalInspectionApplyDetail;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean.ExternalInspectionApplyQueryBean;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.service.ExternalInspectionApplyService;
import com.lyarc.tp.corp.quality.Instrument.bean.Instrument;
import com.lyarc.tp.corp.quality.Instrument.service.InstrumentService;

/**
 * 外检申请单
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(path = "/api/ExternalInspectionApply")
public class ExternalInspectionApplyController {
	@Autowired
	public ExternalInspectionApplyService externalInspectionApplySevice;
	@Autowired
	public InstrumentService instrumentService;
	/**
	 * 新建外检申请单
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public ResultVo<Integer> insert(@RequestBody ExternalInspectionApply record) {
		/*ExternalInspectionApply externalInspectionApply = new ExternalInspectionApply();

        if(StringUtils.isNotEmpty(record.getWfInstId())){
        	externalInspectionApply.setWfInstId(record.getWfInstId());
        }
        externalInspectionApply.setExternalApplyId(record.getExternalApplyId());
        externalInspectionApply.setWfInstTime(LocalDateTime.now());
        externalInspectionApply.setStatus(Constant.VERIFYING);//审核中
        externalInspectionApplySevice.updateExternalInspectionApply(externalInspectionApply);*/
		Integer id = externalInspectionApplySevice.insert(record);
		return ResultVo.success(id);
	}

	/**
	 * 	选择物料
	 */
	@RequestMapping("/findEIA")
	@ResponseBody
	public ResultVo<PageResultBean> find(@RequestBody ExternalInspectionApplyQueryBean bean) {
		Integer page = bean.getPage();
		Integer pageSize = bean.getPageSize();
		if (page != null && pageSize != null) {
			bean.setOffset((page - 1) * pageSize);
		}
		List<ExternalInspectionApply> listEIA = externalInspectionApplySevice.findEIA(bean);
		Long countEIA = externalInspectionApplySevice.countEIA(bean);
		return ResultVo.success(PageResultBean.success(countEIA, listEIA));
	}

	/**
	 * 	外检申请单管理
	 */
	@RequestMapping("/get")
	@ResponseBody
	public ResultVo<List<ExternalInspectionApplyDetail>> get(@RequestParam("externalApplyId") String externalApplyId) {
		if (externalApplyId == null) {
			return ResultVo.build(400, "外检申请单号不能为空");
		} else {
			Integer id = externalInspectionApplySevice.selectedId(externalApplyId);
			if (id == null) {
				return ResultVo.build(400, "X");
			} else {
				List<ExternalInspectionApplyDetail> Eiad = externalInspectionApplySevice.findDetail(externalApplyId);
				return ResultVo.success(Eiad);
			}
		}
	}

	/**
	 *	外检申请单编辑
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResultVo<Integer> update(@RequestBody ExternalInspectionApply externalApplyId) {
		Integer id = externalInspectionApplySevice.update(externalApplyId);
		return ResultVo.success(id);
	}
	
	
	/**
    *
    * @description 提交审批
    * @param
    * @return
    * @author wangting
    * @date 2018/8/3 0003 13:47
    *
    */
  @RequestMapping("/submit")
  @ResponseBody
  public ResultVo submit(@RequestBody Instrument record) {
//      ContractDto contract = contractService.getContract(contractId);
//      return ResultVo.success(contract);
     Instrument instrument = new Instrument();
      if(StringUtils.isNotEmpty(record.getWfInstId())){
    	  instrument.setWfInstId(record.getWfInstId());
      }
      instrument.setInstrumentId(record.getInstrumentId());
      instrument.setWfInstDate(LocalDateTime.now());
      instrument.setStatus(ContractConstant.VERIFYING);//审核中
      instrumentService.updateByPrimaryKeySelective(record);
      return ResultVo.success();
  }

}
