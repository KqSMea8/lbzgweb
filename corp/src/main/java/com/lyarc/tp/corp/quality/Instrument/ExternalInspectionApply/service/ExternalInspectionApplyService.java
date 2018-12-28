package com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.activiti.engine.history.HistoricVariableInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.contract.contract.constant.ContractConstant;
import com.lyarc.tp.corp.contract.contract.dto.ContractDto;
import com.lyarc.tp.corp.contract.contract.form.ContractForm;
import com.lyarc.tp.corp.contract.contract.service.ContractService;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderAdjustPriceDto;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean.ExternalInspectionApply;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean.ExternalInspectionApplyDetail;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.bean.ExternalInspectionApplyQueryBean;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.dao.ExternalInspectionApplyDetailMapper;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.dao.ExternalInspectionApplyMapper;
import com.lyarc.tp.corp.quality.Instrument.bean.Instrument;
import com.lyarc.tp.corp.quality.Instrument.dao.InstrumentMapper;
import com.lyarc.tp.corp.quality.Instrument.form.InstrumentForm;
import com.lyarc.tp.corp.seq.service.SeqService;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.dao.WorkflowOperLogMapper;
import com.lyarc.tp.corp.workflowact.constant.OperLogEnum;
/**
 * 	外检申请单业务逻辑层
 */
@Service
public class ExternalInspectionApplyService {
	@Autowired
	private ExternalInspectionApplyMapper externalInspectionApplyMapper;
	@Autowired
	private ExternalInspectionApplyDetailMapper externalInspectionApplyDetailMapper;
	@Autowired
	private InstrumentMapper instrumentMapper;
	@Autowired
	private SeqService seqService;

	/**
	 * 	新建外检申请单
	 */
	public Integer insert(ExternalInspectionApply record) {		
		String externalApplyId = getEiaId();
		record.setExternalApplyId(externalApplyId);
		record.setStatus(1);
		
		int row = externalInspectionApplyMapper.insertSelective(record);
		List<ExternalInspectionApplyDetail> applyDetails=record.getDetailList();
		for (ExternalInspectionApplyDetail externalInspectionApplyDetail : applyDetails) {	
//			externalInspectionApplyDetail.setInstrumentId(getEiaId());
			externalInspectionApplyDetail.setExternalApplyId(externalApplyId);
			externalInspectionApplyDetail.setLastTestingTime(new Date());
			externalInspectionApplyDetail.setDepartment(record.getDepartment());
			externalInspectionApplyDetailMapper.insertSelective(externalInspectionApplyDetail);
			Instrument instrument = instrumentMapper.selectByPrimaryKey(externalInspectionApplyDetail.getInstrumentId());
			if(instrument.getReportTime()!=null) {
			instrument.setReportTime(record.getCreateTime());
			}
			instrument.setStatus(1);
			instrumentMapper.updateAdd(instrument);
		}
		
		return row;
	}
	
	
	/**
	 * 工作流审核
	 */
	public void externalAppplyCallBack(WorkflowInstEndEvent endEvent) {
		//获取业务单据
		Instrument apply = instrumentMapper.selectByPrimaryKey(endEvent.getBusinessKey());
		//判断业务单据和流程实例关系
		if(apply != null && apply.getWfInstId().equals(endEvent.getInstId())) {
			Instrument instrument = new Instrument();
			instrument.setInstrumentId(endEvent.getBusinessKey());
			//通过
			if(endEvent.getCause()==1){
				instrument.setStatus(1);				
			}
			instrumentMapper.updateByPrimaryKeySelective(instrument);
		}
	}
	

	
	
	/**
	 * 	外检申请单管理
	 */
	public List<ExternalInspectionApply> findEIA(ExternalInspectionApplyQueryBean bean) {
		List<ExternalInspectionApply> listEIA = externalInspectionApplyMapper.listExternalInspectionApply(bean);
		return listEIA;
	}

	public Long countEIA(ExternalInspectionApplyQueryBean bean) {
		Long countEIA =  externalInspectionApplyMapper.countExternalInspectionApply(bean);
		return countEIA;
	}
	
	/**
	 * 	流水号
	 */
	 public String getEiaId(){
	    	return seqService.getBillId(new Date(), "t_inquire", "");
	    }
	 
	/**
	 * 	获取外检申请单明细
	 */
	public List<ExternalInspectionApplyDetail> findDetail(String externalApplyId) {
		List<ExternalInspectionApplyDetail> Eiad=externalInspectionApplyDetailMapper.findDetail(externalApplyId);
		return Eiad;
	}

	/**
	 * 	根据外检申请单单号查询
	 */
	public Integer selectedId(String externalApplyId) {
		return externalInspectionApplyDetailMapper.selectedId(externalApplyId);
	}

	/**
	 * 	外检申请单编辑
	 */
	public Integer update(ExternalInspectionApply externalApplyId) {
		Integer row = externalInspectionApplyMapper.updateByPrimaryKeySelective(externalApplyId);
		List<ExternalInspectionApplyDetail>applyDetails=externalApplyId.getDetailList();
		for (ExternalInspectionApplyDetail externalInspectionApplyDetail : applyDetails) {
			externalInspectionApplyDetail.setExternalApplyId(externalApplyId.getExternalApplyId());
			externalInspectionApplyDetail.setLastTestingTime(new Date());
			externalInspectionApplyDetailMapper.updateByPrimaryKeySelective(externalInspectionApplyDetail);
		}
		return row;
	}

/*
	public Integer updateByPrimaryKeySelective(ExternalInspectionApplyDetail record) {
		int rows = externalInspectionApplyDetailMapper.updateByPrimaryKeySelective(record);
		return rows;
		
	}*/

	
	
	
}
