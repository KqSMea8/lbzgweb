package com.lyarc.tp.corp.purchase.provider.service;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.contract.shipping.constant.ShippingConstant;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPrice;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceDetail;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceLog;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderAdjustPriceQueryBean;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderPayApply;
import com.lyarc.tp.corp.purchase.provider.dao.ProviderAdjustPriceDetailMapper;
import com.lyarc.tp.corp.purchase.provider.dao.ProviderAdjustPriceMapper;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderAdjustPriceDetailDto;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderAdjustPriceDto;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderPayApplyDto;
import com.lyarc.tp.corp.purchase.provider.form.ProviderAdjustPriceDetailForm;
import com.lyarc.tp.corp.purchase.provider.form.ProviderAdjustPriceForm;
import com.lyarc.tp.corp.seq.service.SeqService;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.dao.WorkflowOperLogMapper;
import com.lyarc.tp.corp.workflowact.constant.OperLogEnum;

@Service
public class ProviderAdjustPriceService {

	@Autowired
	private ProviderAdjustPriceMapper providerAdjustPriceMapper;
	@Autowired
	private ProviderAdjustPriceDetailMapper providerAdjustPriceDetailMapper;
	@Autowired
	private SeqService seqService;
	@Autowired
	private WorkflowOperLogMapper workflowOperLogMapper;
	@Autowired
	private ProviderAdjustPriceLogService providerAdjustPriceLogService;


	public PageResultBean list(ProviderAdjustPriceQueryBean queryBean) {
		List<ProviderAdjustPrice> providerAdjustPriceList = providerAdjustPriceMapper.list(queryBean);
		Long count=providerAdjustPriceMapper.count(queryBean);
		return PageResultBean.success(count, providerAdjustPriceList);
	}

	public Integer add(ProviderAdjustPriceForm providerAdjustPriceForm ) {
		ProviderAdjustPrice providerAdjustPrice=JMapperUtils.getDestination(providerAdjustPriceForm, ProviderAdjustPrice.class,ProviderAdjustPriceForm.class);
		String pmapId=getPmapId();
		providerAdjustPrice.setPmapId(pmapId);
		providerAdjustPrice.setStatus(0);
		int row =providerAdjustPriceMapper.insertSelective(providerAdjustPrice);

		List<ProviderAdjustPriceDetailForm> providerAdjustPriceDetailForms=providerAdjustPriceForm.getDetailList();
		providerAdjustPriceDetailForms.forEach(d ->{
			ProviderAdjustPriceDetail providerAdjustPriceDetail=JMapperUtils.getDestination(d,ProviderAdjustPriceDetail.class,ProviderAdjustPriceDetailForm.class);
			providerAdjustPriceDetail.setPmapId(pmapId);
			providerAdjustPriceDetailMapper.insertSelective(providerAdjustPriceDetail);

		});	 
		return row;
	}


	public String getPmapId() {// 单号调用存储过程，按一定规则，新生成
		return seqService.getBillId(new Date(), "t_provider", "R");
	}

	public Integer update(ProviderAdjustPriceForm providerAdjustPriceForm) {
		ProviderAdjustPrice providerAdjustPrice=JMapperUtils.getDestination(providerAdjustPriceForm,ProviderAdjustPrice.class, ProviderAdjustPriceForm.class);
		providerAdjustPrice.setUpdateTime(new Date());
		int rows=providerAdjustPriceMapper.updateByPrimaryKeySelective(providerAdjustPrice);
		String pmapId=providerAdjustPrice.getPmapId();

		providerAdjustPriceDetailMapper.deleteByInStockId(pmapId);

		List<ProviderAdjustPriceDetailForm> providerAdjustPriceDetailForms=providerAdjustPriceForm.getDetailList();
		providerAdjustPriceDetailForms.forEach(d ->{
			ProviderAdjustPriceDetail providerAdjustPriceDetail=JMapperUtils.getDestination(d,ProviderAdjustPriceDetail.class,ProviderAdjustPriceDetailForm.class);
			providerAdjustPriceDetail.setPmapId(pmapId);
			providerAdjustPriceDetailMapper.insertSelective(providerAdjustPriceDetail);
		});
		return rows;
	}


	public ProviderAdjustPriceDto getInStock(String pmapId) {
		ProviderAdjustPriceDto providerAdjustPriceDto=providerAdjustPriceMapper.selectAndDetail(pmapId);
		return providerAdjustPriceDto;
	}

	public Integer selectedId(String pmapId) {
		return providerAdjustPriceMapper.selectedId(pmapId);
	}

	public ProviderAdjustPriceDto selectedObject(String pmapId) {
		return providerAdjustPriceMapper.selectedObject(pmapId);
	}

	public Integer passStatus(String pmapId) {
		return providerAdjustPriceMapper.passStatus(pmapId);
	}


	public Integer delete(String pmapId) {
		return providerAdjustPriceMapper.delete(pmapId);
	}

	public Integer deleteByInStockId(String pmapId) {
		return providerAdjustPriceDetailMapper.deleteByInStockId(pmapId);
	}

	public Integer submit(ProviderAdjustPriceForm providerAdjustPriceForm) {
		int rows = providerAdjustPriceMapper.submit(providerAdjustPriceForm);
		return rows;	
	}

	public void providerAdjustPriceAuditCallBack(WorkflowInstEndEvent endEvent){
		//获取业务单据
		ProviderAdjustPriceDto providerAdjustPriceDto = providerAdjustPriceMapper.selectAndDetail(endEvent.getBusinessKey());

		//判断业务单据和流程实例关系
		if(providerAdjustPriceDto != null && providerAdjustPriceDto.getWfInstId().equals(endEvent.getInstId())) {
			if(providerAdjustPriceDto.getStatus() == 1) {
				ProviderAdjustPrice providerAdjustPrice = new ProviderAdjustPrice();
				providerAdjustPrice.setPmapId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					providerAdjustPrice.setStatus(2);
					ProviderAdjustPriceLog providerAdjustPriceLog = new ProviderAdjustPriceLog();
					providerAdjustPriceLog.setPmapId(providerAdjustPriceDto.getPmapId());
					providerAdjustPriceLog.setProviderId(providerAdjustPriceDto.getProviderId());
					List<ProviderAdjustPriceDetailDto> providerAdjustPriceDetailDto=providerAdjustPriceDto.getDetailList();
					providerAdjustPriceDetailDto.forEach(d ->{
						providerAdjustPriceLog.setMaterId(d.getMaterId());
						providerAdjustPriceLog.setPrice(d.getAfterPrice());
						providerAdjustPriceLog.setRemark(d.getRemark());
					});
					providerAdjustPriceLogService.add(providerAdjustPriceLog);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					providerAdjustPrice.setStatus(3);
					providerAdjustPrice.setWfInstRemark(endEvent.getRemark());
				}
				// 保存
				providerAdjustPriceMapper.updateByPrimaryKeySelective(providerAdjustPrice);
			}
		}
	}
}
