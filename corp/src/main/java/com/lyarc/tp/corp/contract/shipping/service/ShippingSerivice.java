package com.lyarc.tp.corp.contract.shipping.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.common.exception.ServiceException;
import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.contract.contract.dao.ContractMapper;
import com.lyarc.tp.corp.contract.shipping.bean.ShippingBean;
import com.lyarc.tp.corp.contract.shipping.bean.ShippingDetail;
import com.lyarc.tp.corp.contract.shipping.bean.ShippingQueryBean;
import com.lyarc.tp.corp.contract.shipping.constant.ShippingConstant;
import com.lyarc.tp.corp.contract.shipping.dao.ShippingDetailMapper;
import com.lyarc.tp.corp.contract.shipping.dao.ShippingMapper;
import com.lyarc.tp.corp.seq.service.SeqService;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.dao.WorkflowOperLogMapper;
import com.lyarc.tp.corp.workflowact.constant.OperLogEnum;

@Service
public class ShippingSerivice {
	@Autowired
	private ShippingMapper shippingMapper;
	@Autowired
	private SeqService seqService;
	@Autowired
	private ShippingDetailMapper shippingDetailMapper;
	@Autowired
	private WorkflowOperLogMapper workflowOperLogMapper;
	@Autowired
	private ContractMapper contractMapper;

	public PageResultBean list(ShippingQueryBean shippingQueryBean) {
		List<ShippingBean> shippingBeanList = shippingMapper.list(shippingQueryBean);
		Long count = shippingMapper.count(shippingQueryBean);
		return PageResultBean.success(count, shippingBeanList);
	}

	public String add(ShippingBean shippingBean) {
		shippingBean.setStatus(null);
		String shippingId = shippingBean.getShippingId();
		if (shippingId == null || "".equals(shippingId)) {
			shippingId = this.getNewId();
			shippingBean.setShippingId(shippingId);
		}
		// 插入主体部分
		int row = shippingMapper.insertSelective(shippingBean);

		List<ShippingDetail> detailList = shippingBean.getDetailList();
		detailList.forEach(d -> {
			d.setShippingId(shippingBean.getShippingId());
			// 插入明细
			shippingDetailMapper.insertSelective(d);
		});
		return shippingId;
	}

	// 更新单据基本信息
	@Transactional
	public Integer update(ShippingBean shippingBean) {
		shippingBean.setUpdateTime(new Date());
		ShippingBean dbshipping = this.getBill(shippingBean.getShippingId());
		if (dbshipping.getStatus() != 0) {
			throw new ServiceException(400, "");
		}
		// 不能修改的字段
		shippingBean.setStatus(null);
		shippingBean.setWfInstId(null);
		shippingBean.setWfInstTime(null);
		int rows = shippingMapper.updateByPrimaryKeySelective(shippingBean);// 1更新主数据
		String shippingId = dbshipping.getShippingId();
		shippingDetailMapper.deleteByShippingId(shippingId);

		List<ShippingDetail> detailList = shippingBean.getDetailList();
		detailList.forEach(d -> {
			d.setShippingId(dbshipping.getShippingId());
			shippingDetailMapper.insertSelective(d);
		});
		return rows;
	}

	public ShippingBean selectedObject(String shippingId) {
		return shippingMapper.selectedObject(shippingId);
	}

	public String getNewId() {
		return seqService.getBillId(new Date(), "t_shipping", "");
	}

	// 获取详情
	public ShippingBean getBill(String shippingId) {
		ShippingBean bill = shippingMapper.getBill(shippingId);
		if (bill != null) {
			bill.setShippingId(shippingId);
		}
		return bill;
	}

	// 提交
	public Integer updateShipping(ShippingBean shippingBean) {
		ShippingBean shippingBean1 = shippingMapper.selectedObject(shippingBean.getShippingId());
		if (StringUtils.isNotEmpty(shippingBean.getWfInstId())) {
			shippingBean1.setWfInstId(shippingBean.getWfInstId());
		}
		shippingBean1.setWfInstTime(new Date());
		shippingBean1.setStatus(ShippingConstant.VERIFYING);
		int rows = shippingMapper.updateByPrimaryKeySelective(shippingBean1);
		return rows;
	}

	public void shippingAuditCallBack(WorkflowInstEndEvent endEvent) {
		//获取业务单据
		ShippingBean shippingBean = shippingMapper.selectedObject(endEvent.getBusinessKey());

		//判断业务单据和流程实例关系
		if(shippingBean != null && shippingBean.getWfInstId().equals(endEvent.getInstId())) {
			if(shippingBean.getStatus() == 1) {
				ShippingBean shipping = new ShippingBean();
				shipping.setShippingId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					Contract contract = contractMapper.selectByPrimaryKey(shippingBean.getContractId());
					Integer row = contractMapper.getUnShippingCompleteCount(shippingBean.getContractId());
					if (row == 0) {
						shipping.setStatus(2);
						contract.setShippingStatus(2);
						contractMapper.updateByPrimaryKey(contract);
					}
					shipping.setStatus(2);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					shipping.setStatus(3);
					shipping.setWfInstRemark(endEvent.getRemark());
				}
				// 保存
				shippingMapper.updateByPrimaryKeySelective(shipping);
			}
		}
	}
}
