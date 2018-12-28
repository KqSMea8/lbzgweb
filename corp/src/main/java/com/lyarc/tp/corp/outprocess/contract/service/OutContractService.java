package com.lyarc.tp.corp.outprocess.contract.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.outprocess.contract.bean.OutContractBean;
import com.lyarc.tp.corp.outprocess.contract.bean.OutContractDetailBean;
import com.lyarc.tp.corp.outprocess.contract.bean.OutContractQueryBean;
import com.lyarc.tp.corp.outprocess.contract.constant.OutContractConstant;
import com.lyarc.tp.corp.outprocess.contract.dao.OutContractDetailMapper;
import com.lyarc.tp.corp.outprocess.contract.dao.OutContractMapper;
import com.lyarc.tp.corp.seq.service.SeqService;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;

@Service
public class OutContractService {
	
	@Autowired
	OutContractMapper outContractMapper;
	@Autowired
	OutContractDetailMapper outContractDetailMapper;
    @Autowired
    private SeqService seqService;
    
	@SuppressWarnings("rawtypes")
	public PageResultBean query(OutContractQueryBean outContractQueryBean) {
		List<OutContractBean> outContractList =  outContractMapper.query(outContractQueryBean);
		Long outContractCount = outContractMapper.count(outContractQueryBean);
		return PageResultBean.success(outContractCount, outContractList);
	}
	
	public Integer add(OutContractBean outContractBean) {
		if(null==outContractBean.getProviderId()||"".equals(outContractBean.getProviderId())) {
			return 0;
		}else if(null==outContractBean.getDeliveryAddress()||"".equals(outContractBean.getDeliveryAddress())){
			return 0;
		}
		String contractId = getContractId();
		LoginUser loginUser = UserHolder.getUser();
		outContractBean.setCreator(loginUser.getUserId());
		outContractBean.setContractId(contractId);
		outContractBean.setStatus(0);
		Integer outContractNum = outContractMapper.add(outContractBean);
		
		List<OutContractDetailBean> OutContractDetailList = outContractBean.getOutContractDetailList();
		OutContractDetailList.stream().forEach(e->{
			if(null==e.getMaterName()||"".equals(e.getMaterName())) {
				return;
			}else if(null==e.getDrawing()||"".equals(e.getDrawing())) {
				return;
			}
			e.setContractId(contractId);
			e.setCreator(loginUser.getUserId());
			outContractDetailMapper.add(e);
		});
		
		return outContractNum;
	}
	
	public OutContractBean get(String contractId) {
		OutContractBean outContractBean = outContractMapper.get(contractId);
		outContractBean.setOutContractDetailList(outContractDetailMapper.query(contractId));
		return outContractBean;
	}
	
	public OutContractBean getPrevious() {
		OutContractQueryBean outContractQueryBean = new OutContractQueryBean();
		List<OutContractBean> outContractList =  outContractMapper.query(outContractQueryBean);
		OutContractBean outContractBean;
		if(0<outContractList.size()) {
			outContractBean = outContractList.get(0);
		}else {
			outContractBean = null;
		}
		return outContractBean;
	}
	
	public Integer update(OutContractBean outContractBean) {
		//更新外委合同信息
		if(null==outContractBean.getProviderId()||"".equals(outContractBean.getProviderId())) {
			return 0;
		}else if(null==outContractBean.getDeliveryAddress()||"".equals(outContractBean.getDeliveryAddress())){
			return 0;
		}
		outContractBean.setUpdateTime(new Date());
		Integer outContractNum = outContractMapper.update(outContractBean);
		//获取新的外委合同
		List<OutContractDetailBean> outContractDetailList = outContractBean.getOutContractDetailList();
		//获取旧的外委合同
		List<OutContractDetailBean> oldOutContractDetailList = outContractDetailMapper.query(outContractBean.getContractId());
		//外委合同转MAP
		Map<Integer, OutContractDetailBean> oldMap = new HashMap<Integer, OutContractDetailBean>();
		
		//旧合同数量<新合同数量？添加：删除
		if(oldOutContractDetailList.size()<=outContractDetailList.size()) {
			for (OutContractDetailBean outContractDetailBean : oldOutContractDetailList) {
				oldMap.put(outContractDetailBean.getId(), outContractDetailBean);
			}
			//遍历新外委合同
			for (OutContractDetailBean e : outContractDetailList) {
				//相同的数据进行修改
				if(oldMap.containsKey(e.getId())) {
					e.setUpdateTime(new Date());
					outContractDetailMapper.update(e);
				}else {
					LoginUser loginUser = UserHolder.getUser();
					e.setContractId(outContractBean.getContractId());
					e.setCreator(loginUser.getUserId());
					outContractDetailMapper.add(e);
				}
			}
		}else {
			for (OutContractDetailBean outContractDetailBean : outContractDetailList) {
				oldMap.put(outContractDetailBean.getId(), outContractDetailBean);
			}
			//遍历旧外委合同
			for (OutContractDetailBean e : oldOutContractDetailList) {
				//相同的数据进行修改
				if(oldMap.containsKey(e.getId())) {
					e.setUpdateTime(new Date());
					outContractDetailMapper.update(e);
				}else {
					outContractDetailMapper.del(e.getId());
				}
			}
		}
		return outContractNum;
	}
	
	public Integer del(String contractId) {
		List<OutContractDetailBean> outContractDetailList = outContractDetailMapper.query(contractId);
		for (OutContractDetailBean outContractDetailBean : outContractDetailList) {
			outContractDetailMapper.del(outContractDetailBean.getId());
		}
		return outContractMapper.del(contractId);
	}
	
	public Integer submit(OutContractBean outContractBean) {
		OutContractBean oldOutContractBean = outContractMapper.get(outContractBean.getContractId());
		if (StringUtils.isNotEmpty(outContractBean.getWfInstId())) {
			oldOutContractBean.setWfInstId(outContractBean.getWfInstId());
		}
		oldOutContractBean.setWfInstTime(new Date());
		oldOutContractBean.setStatus(OutContractConstant.VERIFYING);
		Integer rows = outContractMapper.update(oldOutContractBean);
		return rows;
	}
	
	public String getContractId() {
		 return seqService.getBillId(new Date(), "t_out_contract", "");
	}
	
    /**
    *
    * @description 外委加工合同评审审核回调
    * @param
    * @return
    * @author
    * @date 2018年12月18日09:25:26
    *
    */
	public void contractAuditCallBack(WorkflowInstEndEvent endEvent){
		//获取业务单据
		//Contract contract = contractMapper.selectByPrimaryKey(endEvent.getBusinessKey());
		OutContractBean outContractBean = outContractMapper.get(endEvent.getBusinessKey());
		
		//判断业务单据和流程实例关系
		if(outContractBean != null && outContractBean.getWfInstId().equals(endEvent.getInstId())) {
			if(outContractBean.getStatus() == 1) {
				outContractBean.setContractId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					outContractBean.setStatus(2);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					outContractBean.setStatus(3);
				}
				outContractBean.setWfInstRemark(endEvent.getRemark());
				// 保存
				outContractMapper.update(outContractBean);
				//contractMapper.updateByPrimaryKeySelective(contractBean);
			}
		}
	}
}
