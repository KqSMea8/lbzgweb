package com.lyarc.tp.corp.contract.contract.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.contract.contract.bean.ContractDetail;
import com.lyarc.tp.corp.contract.contract.bean.ContractQueryBean;
import com.lyarc.tp.corp.contract.contract.constant.ContractConstant;
import com.lyarc.tp.corp.contract.contract.dao.ContractDetailMapper;
import com.lyarc.tp.corp.contract.contract.dao.ContractMapper;
import com.lyarc.tp.corp.seq.service.SeqService;
import com.lyarc.tp.corp.system.material.bean.Material;
import com.lyarc.tp.corp.system.material.bean.MaterialQueryBean;
import com.lyarc.tp.corp.system.material.dao.MaterialMapper;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.dao.WorkflowOperLogMapper;
import com.lyarc.tp.corp.workflowact.constant.OperLogEnum;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ContractService {
	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private ContractDetailMapper contractDetailMapper;
	@Autowired
	private WorkflowOperLogMapper workflowOperLogMapper;
	@Autowired
	private MaterialMapper materialMapper;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private SeqService seqService;

	public List<Contract> list(ContractQueryBean queryBean) {
		List<Contract> contractList = contractMapper.list(queryBean);
		return contractList;
	}

	public Long count(ContractQueryBean queryBean) {
		Long count =  contractMapper.count(queryBean);
		return count;
	}

	@Transactional
	public Integer delete(String contractId) {
		//删除明细
		contractDetailMapper.deleteByContractId(contractId);
		int rows = contractMapper.deleteByPrimaryKey(contractId);
		return rows;
	}

	@Transactional
	public Integer update(Contract contract) {
		contract.setUpdateTime(LocalDateTime.now());
		int rows = contractMapper.updateByPrimaryKeySelective(contract);
		String contractId = contract.getContractId();
		//删除明细
		contractDetailMapper.deleteByContractId(contractId);

		//插入明细
		List<ContractDetail> detailList = contract.getDetailList();
		detailList.forEach(d->{
			d.setContractId(contractId);
			contractDetailMapper.insertSelective(d);
		});
		return rows;
	}

	@Transactional
	public Integer updateContract(Contract contract) {
		int rows = contractMapper.updateByPrimaryKeySelective(contract);
		return rows;
	}

	@Transactional
	public Integer add(Contract contract) {
		String contractId = getContractId();
		contract.setContractId(contractId);
		contract.setStatus(0);
		int row = contractMapper.insertSelective(contract);

		//插入明细
		List<ContractDetail> detailList = contract.getDetailList();
		detailList.forEach(d->{
			d.setContractId(contractId);
			if(StringUtils.isEmpty(d.getMaterId())) {
				d.setMaterId(contract.getCustomerId()+"-"+d.getDrawing());
			}
			MaterialQueryBean queryBean = new MaterialQueryBean();
			queryBean.setMaterId(d.getMaterId());
			Material material=materialMapper.selectMaterial(queryBean);
			if(material==null) {
				material=new Material();
				material.setMaterId(d.getMaterId());
				material.setDrawing(d.getDrawing());
				material.setCustomerId(contract.getCustomerId());
				material.setCustomerName(contract.getCustomerName());
				material.setType("2");
				material.setMaterName(d.getMaterName());
				material.setUnit(d.getUnit());
				material.setCateId(0);
				material.setCreator(d.getCreator());
				materialMapper.insertSelective(material);
			}
			contractDetailMapper.insertSelective(d);
		});
		return row;
	}

	private String getContractId() {
		return seqService.getBillId(new Date(), "t_contract", "");
	}

	/**
	 *
	 * @description 获取询价单详情
	 * @param
	 * @return
	 * @author wangting
	 * @date 2018/7/27 0027 16:39
	 *
	 */
	public Contract getContract(String contractId) {
		Contract contract = contractMapper.selectAndDetail(contractId);
		return contract;
	}

	/**
	 *
	 * @description 合同评审审核回调
	 * @param
	 * @return
	 * @author rx
	 * @date 2018/8/16 0016 16:33
	 *
	 */
	public void contractAuditCallBack(WorkflowInstEndEvent endEvent){
		//获取业务单据
		Contract contract = contractMapper.selectByPrimaryKey(endEvent.getBusinessKey());

		//判断业务单据和流程实例关系
		if(contract != null && contract.getWfInstId().equals(endEvent.getInstId())) {
			if(contract.getStatus() == 1) {
				Contract contractBean = new Contract();
				contractBean.setContractId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					contractBean.setStatus(2);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					contractBean.setStatus(3);
					contractBean.setWfInstRemark(endEvent.getRemark());
				}
				// 保存
				contractMapper.updateByPrimaryKeySelective(contractBean);
			}
		}
	}

	/**
	 *
	 * @description 合同修订审核回调
	 * @param
	 * @return
	 * @author wangting
	 * @date 2018/8/16 0016 16:33
	 *
	 */
	/**
	 * @param event
	 */
	public void contractRevisionCallBack(WorkflowInstEndEvent endEvent){
		Contract contract = contractMapper.selectByPrimaryKey(endEvent.getBusinessKey());

		if(contract != null && contract.getRevisionWfInstId().equals(endEvent.getInstId())) {
			if(contract.getRevisionStatus() == 1) {
				Contract contractBean = new Contract();
				contractBean.setContractId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					contractBean.setRevisionStatus(2);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					contractBean.setRevisionStatus(3);
					contractBean.setRevisionWfInstRemark(endEvent.getRemark());
				}
				//获取修订后的对象
				HistoricVariableInstance businessObj = historyService.createHistoricVariableInstanceQuery().processInstanceId(endEvent.getInstId()).variableName("field_businessObj_1")
						.singleResult();
				if(businessObj != null){
					String value = businessObj.getValue().toString();
					Contract modifyContract = JSON.parseObject(value, new TypeReference<Contract>() {});

					Contract dbContract = this.getContract(modifyContract.getContractId());

					dbContract.setCustomerId(modifyContract.getCustomerId());
					dbContract.setContractNo(modifyContract.getContractNo());
					dbContract.setSignDate(modifyContract.getSignDate());
					dbContract.setSignAddress(modifyContract.getSignAddress());
					dbContract.setIsForeign(modifyContract.getIsForeign());
					dbContract.setHasProcedure(modifyContract.getHasProcedure());
					dbContract.setAmount(modifyContract.getAmount());
					dbContract.setDeliveryAddress(modifyContract.getDeliveryAddress());
					dbContract.setSales(modifyContract.getSales());
					dbContract.setInDate(modifyContract.getInDate());
					dbContract.setCurrency(modifyContract.getCurrency());
					dbContract.setFiles(modifyContract.getFiles());
					dbContract.setRemark(modifyContract.getRemark());
					dbContract.setDetailList(modifyContract.getDetailList());

					update(dbContract);
				}
				// 保存
				contractMapper.updateByPrimaryKeySelective(contractBean);
			}
		}
	}
	/**
	 *
	 * @description 合同撤销审核回调
	 * @param
	 * @return
	 * @author wangting
	 * @date 2018/8/16 0016 16:33
	 *
	 */
	public void contractUndoCallBack(WorkflowInstEndEvent endEvent){
		Contract contract = contractMapper.selectByPrimaryKey(endEvent.getBusinessKey());

		if(contract != null && contract.getUndoWfInstId().equals(endEvent.getInstId())) {
			if(contract.getUndoStatus() == 1) {
				Contract contractBean = new Contract();
				contractBean.setContractId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					contractBean.setUndoStatus(2);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					contractBean.setUndoStatus(3);
					contractBean.setUndoWfInstRemark(endEvent.getRemark());
				}
				// 保存
				contractMapper.updateByPrimaryKeySelective(contractBean);
			}
		}
	}
}
