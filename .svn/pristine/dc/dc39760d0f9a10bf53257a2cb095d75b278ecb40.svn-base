package com.lyarc.tp.corp.purchase.order.service;

import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.purchase.order.bean.PurchaseOrder;
import com.lyarc.tp.corp.purchase.order.bean.PurchaseOrderDetail;
import com.lyarc.tp.corp.purchase.order.bean.PurchaseOrderQueryBean;
import com.lyarc.tp.corp.purchase.order.constant.PurchaseOrderConstant;
import com.lyarc.tp.corp.purchase.order.dao.PurchaseOrderDetailMapper;
import com.lyarc.tp.corp.purchase.order.dao.PurchaseOrderMapper;
import com.lyarc.tp.corp.purchase.order.dto.PurchaseOrderDto;
import com.lyarc.tp.corp.purchase.order.form.PurchaseOrderDetailForm;
import com.lyarc.tp.corp.purchase.order.form.PurchaseOrderForm;
import com.lyarc.tp.corp.seq.service.SeqService;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.dao.WorkflowOperLogMapper;
import com.lyarc.tp.corp.workflowact.constant.OperLogEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseOrderService {
	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;
	@Autowired
	private PurchaseOrderDetailMapper purchaseOrderDetailMapper;
	@Autowired
	private WorkflowOperLogMapper workflowOperLogMapper;
	@Autowired
	private SeqService seqService;

	public List<PurchaseOrderDto> list(PurchaseOrderQueryBean queryBean) {
		List<PurchaseOrderDto> purchaseOrderList = purchaseOrderMapper.list(queryBean);
		return purchaseOrderList;
	}

	public Long count(PurchaseOrderQueryBean queryBean) {
		Long count =  purchaseOrderMapper.count(queryBean);
		return count;
	}

	@Transactional
	public Integer delete(String purchaseOrderId) {
		//删除明细
		purchaseOrderDetailMapper.deleteByPurchaseOrderId(purchaseOrderId);
		int rows = purchaseOrderMapper.deleteByPrimaryKey(purchaseOrderId);
		return rows;
	}

	@Transactional
	public Integer update(PurchaseOrderForm purchaseOrderForm) {
		PurchaseOrder purchaseOrder = JMapperUtils.getDestination(purchaseOrderForm, PurchaseOrder.class, PurchaseOrderForm.class);
		purchaseOrder.setUpdateTime(LocalDateTime.now());
		int rows = purchaseOrderMapper.updateByPrimaryKeySelective(purchaseOrder);
		String purchaseOrderId = purchaseOrder.getPurchaseOrderId();
		//删除明细
		purchaseOrderDetailMapper.deleteByPurchaseOrderId(purchaseOrderId);

		//插入明细
		List<PurchaseOrderDetailForm> detailList = purchaseOrderForm.getDetailList();
		detailList.forEach(d->{
			d.setPurchaseOrderId(purchaseOrderId);
			PurchaseOrderDetail purchaseOrderDetail = JMapperUtils.getDestination(d, PurchaseOrderDetail.class, PurchaseOrderDetailForm.class);
			purchaseOrderDetailMapper.insertSelective(purchaseOrderDetail);
		});
		return rows;
	}
	//
	//    @Transactional
	//    public Integer update(PurchaseOrderDto purchaseOrderDto) {
	//        PurchaseOrder purchaseOrder = JMapperUtils.getDestination(purchaseOrderDto, PurchaseOrder.class, PurchaseOrderDto.class);
	//        purchaseOrder.setUpdateTime(LocalDateTime.now());
	//        int rows = purchaseOrderMapper.updateByPrimaryKeySelective(purchaseOrder);
	//        String purchaseOrderId = purchaseOrder.getPurchaseOrderId();
	//        //删除明细
	//        purchaseOrderDetailMapper.deleteByPurchaseOrderId(purchaseOrderId);
	//
	//        //插入明细
	//        List<PurchaseOrderDetailDto> detailList = purchaseOrderDto.getDetailList();
	//        detailList.forEach(d->{
	//            d.setPurchaseOrderId(purchaseOrderId);
	//            PurchaseOrderDetail purchaseOrderDetail = JMapperUtils.getDestination(d, PurchaseOrderDetail.class, PurchaseOrderDetailDto.class);
	//            purchaseOrderDetailMapper.insertSelective(purchaseOrderDetail);
	//        });
	//        return rows;
	//    }

	@Transactional
	public Integer updatePurchaseOrder(PurchaseOrder purchaseOrder) {
		int rows = purchaseOrderMapper.updateByPrimaryKeySelective(purchaseOrder);
		return rows;
	}

	@Transactional
	public Integer add(PurchaseOrderForm purchaseOrderForm) {
		PurchaseOrder purchaseOrder = JMapperUtils.getDestination(purchaseOrderForm, PurchaseOrder.class, PurchaseOrderForm.class);

		String purchaseOrderId = getPurchaseOrderId();
		purchaseOrder.setPurchaseOrderId(purchaseOrderId);
		purchaseOrder.setStatus(0);
		int row = purchaseOrderMapper.insertSelective(purchaseOrder);

		//插入明细
		List<PurchaseOrderDetailForm> detailList = purchaseOrderForm.getDetailList();
		detailList.forEach(d->{
			d.setPurchaseOrderId(purchaseOrderId);
			PurchaseOrderDetail purchaseOrderDetail =
					JMapperUtils.getDestination(d, PurchaseOrderDetail.class, PurchaseOrderDetailForm.class);
			purchaseOrderDetail.setPurchaseOrderId(purchaseOrderId);
			purchaseOrderDetailMapper.insertSelective(purchaseOrderDetail);
		});
		return row;
	}

	public String getPurchaseOrderId(){
		return seqService.getBillId(new Date(), "t_purchase_order", "C");
	}

	/**
	 *
	 * @description 获取采购订单单详情
	 * @param
	 * @return
	 * @author wangting
	 * @date 2018/7/27 0027 16:39
	 *
	 */
	public PurchaseOrderDto getPurchaseOrder(String purchaseOrderId) {
		PurchaseOrderDto purchaseOrder = purchaseOrderMapper.selectAndDetail(purchaseOrderId);
		return purchaseOrder;
	}

	/**
	 *
	 * @description 采购订单审核回调
	 * @param
	 * @return
	 * @author wangting
	 * @date 2018/8/16 0016 16:33
	 *
	 */
	public void purchaseOrderAuditCallBack(WorkflowInstEndEvent endEvent){
		//获取业务单据
		PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(endEvent.getBusinessKey());

		//判断业务单据和流程实例关系
		if(purchaseOrder != null && purchaseOrder.getWfInstId().equals(endEvent.getInstId())) {
			if(purchaseOrder.getStatus() == 1) {
				PurchaseOrder purchaseOrderBean = new PurchaseOrder();
				purchaseOrderBean.setPurchaseOrderId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					purchaseOrderBean.setStatus(2);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					purchaseOrderBean.setStatus(3);
					purchaseOrderBean.setWfInstRemark(endEvent.getRemark());
				}
				// 保存
				purchaseOrderMapper.updateByPrimaryKeySelective(purchaseOrderBean);
			}
		}
	}

}
