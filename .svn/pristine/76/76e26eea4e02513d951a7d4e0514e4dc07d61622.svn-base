package com.lyarc.tp.corp.production.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyarc.tp.corp.aftersales.constant.ComplaintConstant;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.common.exception.ServiceException;
import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.production.bean.SelfMaterMakeApplyBean;
import com.lyarc.tp.corp.production.bean.SelfMaterMakeApplyDetail;
import com.lyarc.tp.corp.production.bean.SelfMaterMakeApplyQueryBean;
import com.lyarc.tp.corp.production.dao.SelfMaterMakeApplyDetailMapper;
import com.lyarc.tp.corp.production.dao.SelfMaterMakeApplyMapper;
import com.lyarc.tp.corp.seq.service.SeqService;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.dao.WorkflowOperLogMapper;
import com.lyarc.tp.corp.workflowact.constant.OperLogEnum;

@Service
public class SelfMaterMakeApplyService {
	@Autowired
	private SelfMaterMakeApplyMapper selfMaterMakeApplyMapper;
	@Autowired
	private SeqService seqService;
	@Autowired
	private SelfMaterMakeApplyDetailMapper selfMaterMakeApplyDetailMapper;
	@Autowired
	private WorkflowOperLogMapper workflowOperLogMapper;

	/*
	 * 列表
	 */
	public PageResultBean list(SelfMaterMakeApplyQueryBean selfMaterMakeApplyQueryBean) {
		List<SelfMaterMakeApplyBean> selfMaterMakeApplyQueryBeanList = selfMaterMakeApplyMapper
				.list(selfMaterMakeApplyQueryBean);
		Long count = selfMaterMakeApplyMapper.count(selfMaterMakeApplyQueryBean);
		return PageResultBean.success(count, selfMaterMakeApplyQueryBeanList);
	}

	/*
	 * 获取详情
	 */
	public SelfMaterMakeApplyBean getBill(String selfMaterMakeApplyId) {
		SelfMaterMakeApplyBean bill = selfMaterMakeApplyMapper.getBill(selfMaterMakeApplyId);
		if (bill != null) {
			bill.setSelfMaterMakeApplyId(selfMaterMakeApplyId);
		}
		return bill;
	}

	/*
	 * 添加数据
	 */
	public String add(SelfMaterMakeApplyBean selfMaterMakeApplyBean) {
		selfMaterMakeApplyBean.setStatus(null);
		String selfMaterMakeApplyId = selfMaterMakeApplyBean.getSelfMaterMakeApplyId();
		if (selfMaterMakeApplyId == null || "".equals(selfMaterMakeApplyId)) {
			selfMaterMakeApplyId = this.getNewId();
			selfMaterMakeApplyBean.setSelfMaterMakeApplyId(selfMaterMakeApplyId);
		}
		// 插入主体部分
		int row = selfMaterMakeApplyMapper.insertSelective(selfMaterMakeApplyBean);

		List<SelfMaterMakeApplyDetail> detailList = selfMaterMakeApplyBean.getDetailList();
		detailList.forEach(d -> {
			d.setSelfMaterMakeApplyId(selfMaterMakeApplyBean.getSelfMaterMakeApplyId());
			// 插入明细
			selfMaterMakeApplyDetailMapper.insertSelective(d);
		});
		return selfMaterMakeApplyId;
	}

	public String getNewId() {
		return seqService.getBillId(new Date(), "t_shipping", "");
	}

	/*
	 * 送审
	 */
	public Integer submit(String selfMaterMakeApplyId) {
		return selfMaterMakeApplyMapper.submit(selfMaterMakeApplyId);
	}

	/*
	 * 编辑
	 */
	@Transactional
	public Integer update(SelfMaterMakeApplyBean selfMaterMakeApplyBean) {
		selfMaterMakeApplyBean.setUpdateTime(LocalDateTime.now());
		SelfMaterMakeApplyBean dbselfMaterMakeApplyBean = this
				.getBill(selfMaterMakeApplyBean.getSelfMaterMakeApplyId());
		if (dbselfMaterMakeApplyBean.getStatus() != 0) {
			throw new ServiceException(400, "");
		}
		// 不能修改的字段
		selfMaterMakeApplyBean.setStatus(null);
		selfMaterMakeApplyBean.setWfInstId(null);
		selfMaterMakeApplyBean.setWfInstTime(null);
		int rows = selfMaterMakeApplyMapper.updateByPrimaryKeySelective(selfMaterMakeApplyBean);// 1更新主数据
		String selfMaterMakeApplyId = dbselfMaterMakeApplyBean.getSelfMaterMakeApplyId();
		selfMaterMakeApplyDetailMapper.deleteByselfMaterMakeApplyId(selfMaterMakeApplyId);

		List<SelfMaterMakeApplyDetail> detailList = selfMaterMakeApplyBean.getDetailList();
		detailList.forEach(d -> {
			d.setSelfMaterMakeApplyId(dbselfMaterMakeApplyBean.getSelfMaterMakeApplyId());
			selfMaterMakeApplyDetailMapper.insertSelective(d);
		});
		return rows;
	}

	// 删除
	@Transactional
	public Integer delete(String selfMaterMakeApplyId) {
		Integer id = selfMaterMakeApplyMapper.deleteBySelfMaterMakeApplyId(selfMaterMakeApplyId);
		selfMaterMakeApplyDetailMapper.deleteByselfMaterMakeApplyId(selfMaterMakeApplyId);
		return id;
	}

	// 回调
	public void selfMaterMakeApplyAuditCallBack(WorkflowInstEndEvent endEvent) {
		//获取业务单据
		SelfMaterMakeApplyBean selfMaterMakeApplyBean = selfMaterMakeApplyMapper.selectedObject(endEvent.getBusinessKey());

		//判断业务单据和流程实例关系
		if(selfMaterMakeApplyBean != null && selfMaterMakeApplyBean.getWfInstId().equals(endEvent.getInstId())) {
			if(selfMaterMakeApplyBean.getStatus() == 1) {
				SelfMaterMakeApplyBean selfMaterMakeApply = new SelfMaterMakeApplyBean();
				selfMaterMakeApply.setSelfMaterMakeApplyId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					selfMaterMakeApply.setStatus(2);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					selfMaterMakeApply.setStatus(3);
					selfMaterMakeApply.setWfInstRemark(endEvent.getRemark());
				}
				// 保存
				selfMaterMakeApplyMapper.updateByPrimaryKeySelective(selfMaterMakeApply);
			}
		}
	}

	public Integer updateSelfMaterMakeApply(SelfMaterMakeApplyBean selfMaterMakeApplyBean) {
		return selfMaterMakeApplyMapper.updateByPrimaryKeySelective(selfMaterMakeApplyBean);
	}

}
