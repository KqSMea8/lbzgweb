package com.lyarc.tp.corp.aftersales.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.aftersales.bean.ComplaintBean;
import com.lyarc.tp.corp.aftersales.bean.ComplaintQueryBean;
import com.lyarc.tp.corp.aftersales.constant.ComplaintConstant;
import com.lyarc.tp.corp.aftersales.dao.ComplaintMapper;
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
public class ComplaintService {
	@Autowired
	private ComplaintMapper complaintMapper;
	@Autowired
	private SeqService seqService;
	@Autowired
	private WorkflowOperLogMapper workflowOperLogMapper;

	public PageResultBean list(ComplaintQueryBean complaintQueryBean) {
		List<ComplaintBean> complaintBeanList = complaintMapper.list(complaintQueryBean);
		Long count = complaintMapper.count(complaintQueryBean);
		return PageResultBean.success(count, complaintBeanList);
	}

	public ComplaintBean get(String complaintId) {
		ComplaintBean ComplaintBean = complaintMapper.selectObject(complaintId);
		return ComplaintBean;
	}

	public String add(ComplaintBean complaintBean) {
		String complaintId = complaintBean.getComplaintId();
		if (complaintId == null || "".equals(complaintId)) {
			complaintId = this.getNewId();
			complaintBean.setComplaintId(complaintId);
		}
		Integer rows = complaintMapper.add(complaintBean);
		return complaintId;
	}

	public String getNewId() {
		return seqService.getBillId(new Date(), "t_complaint", "");
	}

	// 修改
	public Integer update(ComplaintBean complaintBean) {
		ComplaintBean dbcomplaint = this.get(complaintBean.getComplaintId());
		if (dbcomplaint.getStatus() != 0 && dbcomplaint.getStatus() != 2) {
			throw new ServiceException(400, "不在修改状态");
		}
		// 不能修改的字段
		complaintBean.setCreateTime(null);
		complaintBean.setStatus(null);
		complaintBean.setWfInstId(null);
		complaintBean.setWfInstTime(null);
		Integer rows = complaintMapper.updateByPrimarySelective(complaintBean);
		return rows;
	}

	// 提交
	public Integer submit(ComplaintBean complaintBean) {
		ComplaintBean dbComplaint = complaintMapper.selectObject(complaintBean.getComplaintId());
		if (StringUtils.isNotEmpty(complaintBean.getWfInstId())) {
			dbComplaint.setWfInstId(complaintBean.getWfInstId());
		}
		dbComplaint.setWfInstTime(new Date());
		dbComplaint.setStatus(ComplaintConstant.VERIFYING);
		Integer rows = complaintMapper.updateByPrimarySelective(dbComplaint);
		return rows;
	}

	// 删除
	public Integer delete(String complaintId) {
		Integer rows = complaintMapper.delete(complaintId);
		return rows;
	}

	public void complaintAuditCallBack(WorkflowInstEndEvent endEvent) {
		//获取业务单据
		ComplaintBean complaintBean = complaintMapper.selectObject(endEvent.getBusinessKey());

		//判断业务单据和流程实例关系
		if(complaintBean != null && complaintBean.getWfInstId().equals(endEvent.getInstId())) {
			if(complaintBean.getStatus() == 1) {
				ComplaintBean complaint = new ComplaintBean();
				complaint.setComplaintId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					complaint.setStatus(2);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					complaint.setStatus(3);
					complaint.setWfInstRemark(endEvent.getRemark());
				}
				// 保存
				complaintMapper.updateByPrimarySelective(complaint);
			}
		}
	}
}
