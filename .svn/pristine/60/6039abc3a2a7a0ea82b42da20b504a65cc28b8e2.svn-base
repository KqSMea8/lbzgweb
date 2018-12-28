package com.lyarc.tp.corp.timingtask;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lyarc.tp.corp.aftersales.service.ComplaintDisposeService;
import com.lyarc.tp.corp.aftersales.service.ComplaintService;
import com.lyarc.tp.corp.contract.contract.dao.ContractMapper;
import com.lyarc.tp.corp.contract.contract.service.ContractService;
import com.lyarc.tp.corp.contract.shipping.service.ShippingSerivice;
import com.lyarc.tp.corp.outprocess.contract.service.OutContractService;
import com.lyarc.tp.corp.production.service.SelfMaterMakeApplyService;
import com.lyarc.tp.corp.purchase.order.service.PurchaseOrderService;
import com.lyarc.tp.corp.purchase.provider.service.NewProviderMaterialService;
import com.lyarc.tp.corp.purchase.provider.service.ProviderAdjustPriceService;
import com.lyarc.tp.corp.purchase.provider.service.ProviderPayApplyService;
import com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.service.ExternalInspectionApplyService;
import com.lyarc.tp.corp.system.setting.dao.SettingMapper;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;
import com.lyarc.tp.corp.workflow.dao.WorkflowInstEventMapper;
import com.lyarc.tp.corp.workflow.dao.WorkflowOperLogMapper;

/**
 *
 * @description 修改审核流程定时任务，当流程执行完后，会将实例和状态写到时间和流程日志表里，定时去读这两张表的记录 更新业务数据
 * @param
 * @return
 * @author wangting
 * @date 2018/9/3 0003 10:21
 *
 */
@Component
public class ModifyFlowStatusTask { 
	private static Logger logger = LoggerFactory.getLogger(ModifyFlowStatusTask.class);  
	public volatile int running = 0;

	@Autowired
	WorkflowInstEventMapper workflowInstEventMapper;
	@Autowired
	WorkflowOperLogMapper workflowOperLogMapper;
	@Autowired
	ContractMapper contractMapper;
	@Autowired
	SettingMapper settingMapper;
	@Autowired
	ProviderAdjustPriceService providerAdjustPriceService;
	@Autowired
	ContractService contractService;
	@Autowired
	NewProviderMaterialService newProviderMaterialService;
	@Autowired
	PurchaseOrderService purchaseOrderService;
	@Autowired
	ShippingSerivice shippingSerivice;
	@Autowired
	ProviderPayApplyService providerPayApplyService;
	@Autowired
	ComplaintService complaintService;
	@Autowired
	ComplaintDisposeService complaintDisposeService;
	@Autowired  
	SelfMaterMakeApplyService selfMaterMakeApplyService;
	@Autowired
	OutContractService outContractService; 
	@Autowired
	ExternalInspectionApplyService externalInspectionApplyService;
	
	/* 停用中间表，改为直接调用
	// @Scheduled(cron = "0/5 * * * * ?") // 每5秒执行一次
	@Scheduled(cron = "${cronScanAuditEvent}") // 每5秒执行一次
	public void scheduler() {
		 
		if (running == 1) {
			return;
		}
		running = 1;
		try {
			List<WorkflowInstEvent> list = workflowInstEventMapper.queryEventInfo();
			if (!list.isEmpty()) {
				for (WorkflowInstEvent event : list) {
					modifyStatus(event);
				}
				// 更新最后处理id
				WorkflowInstEvent event = list.get(list.size() - 1);
				Integer id = event.getId();
				Setting setting = new Setting();
				setting.setValue(id.toString());
				setting.setName("lastInstId");
				settingMapper.updateByPrimaryKeySelective(setting);

				running = 0;
			}
		} catch (Exception e) {
			logger.error("更新流程状态出错", e);
		} finally {
			running = 0;
		} 
	}
	*/  
	
	// 工作流结束后通知业务模块
	public void modifyStatus(WorkflowInstEndEvent endEvent) { 
		Integer formId = endEvent.getWfId();
		// 业务回调
		if (CommonConst.START_AUDIT.equals(formId)) {// 通用表单 - 开发用
			
		}
		if (CommonConst.CONTRACT_AUDIT_FORM.equals(formId)) {// 合同审核
			contractService.contractAuditCallBack(endEvent);
		}  
		if (CommonConst.CONTRACT_UNDO_FORM.equals(formId)) {// 合同撤销审核
			contractService.contractUndoCallBack(endEvent);
		}
		if (CommonConst.CONTRACT_REVISION_FORM.equals(formId)) {// 合同修订审核
			contractService.contractRevisionCallBack(endEvent);
		}
		if (CommonConst.NEW_MATERIAL_PROVIDER.equals(formId)) {// 新材料供应通知单
			newProviderMaterialService.newProviderMaterialAuditCallBack(endEvent);
		}
		if (CommonConst.PURCHASE_ORDER1.equals(formId) || CommonConst.PURCHASE_ORDER2.equals(formId)
				|| CommonConst.PURCHASE_ORDER3.equals(formId) || CommonConst.PURCHASE_ORDER4.equals(formId)) {// 采购订单
			purchaseOrderService.purchaseOrderAuditCallBack(endEvent);
		}
		if (CommonConst.PROVIDER_PAY_APPLY.equals(formId)) {// 供货商付款申请
			providerPayApplyService.providerPayApplyAuditCallBack(endEvent);
		}
		if (CommonConst.COMPLAINT.equals(formId)) {// 投诉单申请
			complaintService.complaintAuditCallBack(endEvent);
		}
		if (CommonConst.COMPLAINTDISPOSE.equals(formId)) {// 处置单申请
			complaintDisposeService.complaintDisposeAuditCallBack(endEvent);
		}
		if (CommonConst.PROVIDER_ADJUST_PRICE.equals(formId)) {// 调价申请
			providerAdjustPriceService.providerAdjustPriceAuditCallBack(endEvent);
		}
		if (CommonConst.INVOICE.equals(formId)) {// 发货单申请
			shippingSerivice.shippingAuditCallBack(endEvent);
		}
		if (CommonConst.SELFMATERMAKEAPPLY.equals(formId)) {// 自用件申请
			System.out.println(formId);
			selfMaterMakeApplyService.selfMaterMakeApplyAuditCallBack(endEvent);
		}
		if(CommonConst.OUT_CONTRACT.equals(formId)) {//外委加工合同
			outContractService.contractAuditCallBack(endEvent);
		}
		
		// wb code
		if (formId == 50) {// 合同审核
			//ExternalAppplyCallBack(endEvent); 
			externalInspectionApplyService.externalAppplyCallBack(endEvent);
		}
	}
}