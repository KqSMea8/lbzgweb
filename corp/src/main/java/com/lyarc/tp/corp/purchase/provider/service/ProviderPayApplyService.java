package com.lyarc.tp.corp.purchase.provider.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.contract.shipping.bean.ShippingBean;
import com.lyarc.tp.corp.contract.shipping.constant.ShippingConstant;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderPayApply;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderPayApplyQueryBean;
import com.lyarc.tp.corp.purchase.provider.dao.ProviderPayApplyMapper;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderPayApplyDto;
import com.lyarc.tp.corp.purchase.provider.form.ProviderPayApplyForm;
import com.lyarc.tp.corp.seq.service.SeqService;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.dao.WorkflowOperLogMapper;
import com.lyarc.tp.corp.workflowact.constant.OperLogEnum;

@Service
public class ProviderPayApplyService {

	@Autowired
	private ProviderPayApplyMapper providerPayApplyMapper;
	@Autowired
	private SeqService seqService;
	@Autowired
	private WorkflowOperLogMapper workflowOperLogMapper;

	public PageResultBean list(ProviderPayApplyQueryBean queryBean) {
		List<ProviderPayApply> providerPayApplyList = providerPayApplyMapper.list(queryBean);
		Long count=providerPayApplyMapper.count(queryBean);
		return PageResultBean.success(count, providerPayApplyList);
	}

	public Integer add(ProviderPayApplyForm providerPayApplyForm ) {
		ProviderPayApply providerPayApply=JMapperUtils.getDestination(providerPayApplyForm, ProviderPayApply.class,ProviderPayApplyForm.class);
		String paId=getPaId();
		providerPayApply.setPaId(paId);
		providerPayApply.setStatus(0);
		int row =providerPayApplyMapper.insertSelective(providerPayApply);
		return row;
	}

	public String getPaId() {// 单号调用存储过程，按一定规则，新生成
		return seqService.getBillId(new Date(), "t_provider_pay_apply", "R");
	}

	public Integer update(ProviderPayApplyForm providerPayApplyForm) {
		ProviderPayApply providerPayApply=JMapperUtils.getDestination(providerPayApplyForm,ProviderPayApply.class, ProviderPayApplyForm.class);
		providerPayApply.setUpdateTime(new Date());
		int rows=providerPayApplyMapper.updateByPrimaryKeySelective(providerPayApply);

		return rows;
	}

	public Integer selectedId(String paId) {
		return providerPayApplyMapper.selectedId(paId);
	}

	public ProviderPayApplyDto selectedObject(String paId) {
		return providerPayApplyMapper.selectedObject(paId);
	}

	public Integer submit(ProviderPayApplyForm providerPayApplyForm) {
		int rows = providerPayApplyMapper.submit(providerPayApplyForm);
		return rows;	
	}

	public ProviderPayApplyDto getInStock(String paId) {
		ProviderPayApplyDto providerPayApplyDto=providerPayApplyMapper.selectedObject(paId);
		return providerPayApplyDto;
	}

	public Integer delete(String pmapId) {
		return providerPayApplyMapper.delete(pmapId);
	}

	public void providerPayApplyAuditCallBack(WorkflowInstEndEvent endEvent){
		//获取业务单据
		ProviderPayApplyDto providerPayApplyDto = providerPayApplyMapper.selectedObject(endEvent.getBusinessKey());

		//判断业务单据和流程实例关系
		if(providerPayApplyDto != null && providerPayApplyDto.getWfInstId().equals(endEvent.getInstId())) {
			if(providerPayApplyDto.getStatus() == 1) {
				ProviderPayApply providerPayApply = new ProviderPayApply();
				providerPayApply.setPaId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					providerPayApply.setStatus(2);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					providerPayApply.setStatus(3);
					providerPayApply.setWfInstRemark(endEvent.getRemark());
				}
				// 保存
				providerPayApplyMapper.updateByPrimaryKeySelective(providerPayApply);
			}
		}
	}
}
