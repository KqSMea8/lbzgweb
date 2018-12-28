package com.lyarc.tp.corp.outprocess.contract.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.outprocess.contract.bean.OutContractDetailBean;
import com.lyarc.tp.corp.outprocess.contract.dao.OutContractDetailMapper;

@Service
public class OutContractDetailService {
	
	@Autowired
	OutContractDetailMapper outContractDetailMapper;
	
	@SuppressWarnings("rawtypes")
	public PageResultBean query(String contractId) {
		List<OutContractDetailBean> outContractDetailList = outContractDetailMapper.query(contractId);
		Long outContractDetailCount = outContractDetailMapper.count(contractId);
		return PageResultBean.success(outContractDetailCount, outContractDetailList);
	}
	
	public OutContractDetailBean get(Integer id) {
		return outContractDetailMapper.get(id);
	}
	
	public Integer update(OutContractDetailBean outContractDetailBean) {
		outContractDetailBean.setUpdateTime(new Date());
		return outContractDetailMapper.update(outContractDetailBean);
	}
	
	public Integer del(Integer id) {
		return outContractDetailMapper.del(id);
	}

}
