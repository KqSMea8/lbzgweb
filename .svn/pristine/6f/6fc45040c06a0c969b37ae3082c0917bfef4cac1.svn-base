package com.lyarc.tp.corp.purchase.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.purchase.provider.bean.ProviderInquire;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderInquireQueryBean;
import com.lyarc.tp.corp.purchase.provider.dao.ProviderInquireMapper;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderInquireDto;



@Service
public class ProviderInquireService {
	@Autowired
	private ProviderInquireMapper providerInquireMapper;
	
	 public List<ProviderInquireDto> list(ProviderInquireQueryBean queryBean) {
	        List<ProviderInquireDto> providerInquireList = providerInquireMapper.list(queryBean);
	        return providerInquireList;
	 }
	 
	 public Long count(ProviderInquireQueryBean queryBean) {
		Long count=providerInquireMapper.count(queryBean);
		return count; 
	 }
	 
	 public ProviderInquire getProviderInquire(Integer id) {
		 ProviderInquire providerInquire=providerInquireMapper.selectByPrimaryKey(id);
		 return providerInquire;
	 }
	 
	 public Integer add(ProviderInquire providerInquire) {
		int rows=providerInquireMapper.insertSelective(providerInquire);
		return rows;	 
	 }
	 
	 public Integer update(ProviderInquire providerInquire) {
		 int rows=providerInquireMapper.updateByPrimaryKey(providerInquire);
		 return rows;
	 }
	 
	 public Integer delete(Integer id) {
		 int rows=providerInquireMapper.deleteByPrimaryKey(id);
		 return rows;
	 }
	 
}
