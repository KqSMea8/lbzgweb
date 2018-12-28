package com.lyarc.tp.corp.aftersales.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyarc.tp.corp.aftersales.bean.ComplaintBean;
import com.lyarc.tp.corp.aftersales.bean.ComplaintDisposeBean;
import com.lyarc.tp.corp.aftersales.bean.ComplaintDisposeQueryBean;
import com.lyarc.tp.corp.aftersales.constant.ComplaintConstant;
import com.lyarc.tp.corp.aftersales.dao.ComplaintDisposeMapper;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.common.exception.ServiceException;
import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.seq.service.SeqService;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.dao.WorkflowOperLogMapper;
import com.lyarc.tp.corp.workflowact.constant.OperLogEnum;

@Service
public class ComplaintDisposeService {
	@Autowired
	private ComplaintDisposeMapper complaintDisposeMapper;
	@Autowired
	private SeqService seqService;
	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private WorkflowOperLogMapper workflowOperLogMapper;

	// 列表
	public PageResultBean list(ComplaintDisposeQueryBean complaintDisposeQueryBean) {
		List<ComplaintDisposeBean> complaintDisposeBean = complaintDisposeMapper.list(complaintDisposeQueryBean);
		Long count = complaintDisposeMapper.count(complaintDisposeQueryBean);
		return PageResultBean.success(count, complaintDisposeBean);
	}

	// 获得
	public ComplaintDisposeBean get(String disposeId) {
		ComplaintDisposeBean complaintDisposeBean = complaintDisposeMapper.selectObject(disposeId);
		return complaintDisposeBean;
	}

	// 添加
	@Transactional
	public String add(ComplaintDisposeBean complaintDisposeBean) {
		String disposeId = complaintDisposeBean.getDisposeId();
		if (disposeId == null || "".equals(disposeId)) {
			disposeId = this.getNewId();
			complaintDisposeBean.setDisposeId(disposeId);
		}
		Integer rows = complaintDisposeMapper.add(complaintDisposeBean);
		ComplaintBean complaintBean = complaintService.get(complaintDisposeBean.getComplaintId());
		complaintBean.setDisposeStatus(1);
		complaintBean.setDisposeId(complaintDisposeBean.getDisposeId());
		complaintService.update(complaintBean);
		return disposeId;
	}

	public String getNewId() {
		return seqService.getBillId(new Date(), "t_complaint_dispose", "");
	}

	// 修改
	public Integer update(ComplaintDisposeBean complaintDisposeBean) {
		complaintDisposeBean.setUpdateTime(new Date());
		ComplaintDisposeBean dbComplaintDispose = this.get(complaintDisposeBean.getDisposeId());
		if (dbComplaintDispose.getStatus() != 0) {
			throw new ServiceException(400, "");
		}
		// 不能修改的字段
		complaintDisposeBean.setStatus(null);
		complaintDisposeBean.setWfInstId(null);
		complaintDisposeBean.setWfInstTime(null);
		Integer rows = complaintDisposeMapper.updateByPrimarySelective(complaintDisposeBean);
		return rows;
	}

	// 提交
	public Integer submit(ComplaintDisposeBean complaintDisposeBean) {
		ComplaintDisposeBean dbcomplaintDispose = complaintDisposeMapper
				.selectObject(complaintDisposeBean.getDisposeId());
		if (StringUtils.isNotEmpty(complaintDisposeBean.getWfInstId())) {
			dbcomplaintDispose.setWfInstId(complaintDisposeBean.getWfInstId());
		}
		dbcomplaintDispose.setWfInstTime(new Date());
		dbcomplaintDispose.setStatus(1);
		Integer rows = complaintDisposeMapper.updateByPrimarySelective(dbcomplaintDispose);
		return rows;
	}

	public void complaintDisposeAuditCallBack(WorkflowInstEndEvent endEvent) {
		//获取业务单据
		ComplaintDisposeBean complaintDisposeBean = complaintDisposeMapper.selectObject(endEvent.getBusinessKey());

		//判断业务单据和流程实例关系
		if(complaintDisposeBean != null && complaintDisposeBean.getWfInstId().equals(endEvent.getInstId())) {
			if(complaintDisposeBean.getStatus() == 1) {
				ComplaintDisposeBean complaintDispose = new ComplaintDisposeBean();
				complaintDispose.setDisposeId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					complaintDispose.setStatus(2);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					complaintDispose.setStatus(3);
					complaintDispose.setWfInstRemark(endEvent.getRemark());
				}
				// 保存
				complaintDisposeMapper.updateByPrimarySelective(complaintDispose);
			}
		}
	}
}
