package com.lyarc.tp.corp.outprocess.provider.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.outprocess.provider.bean.OutProviderBean;
import com.lyarc.tp.corp.outprocess.provider.dao.OutProviderMapper;

@Service
public class OutProviderService {
	
	@Autowired
	private OutProviderMapper outProviderMapper;
	
	@SuppressWarnings("rawtypes")
	public PageResultBean query(OutProviderBean outProviderBean) {
		List<OutProviderBean> outProviderList = outProviderMapper.query(outProviderBean);
		Long outProviderCount = outProviderMapper.count(outProviderBean);
		return PageResultBean.success(outProviderCount, outProviderList);
	}
	
	public OutProviderBean get(String providerId) {
		return outProviderMapper.get(providerId);
	}
	
	public Integer add(OutProviderBean outProviderBean) {
		LoginUser loginUser = UserHolder.getUser();
		outProviderBean.setCreator(loginUser.getUserId());
		return outProviderMapper.add(outProviderBean);
	}
	
	public Integer update(OutProviderBean outProviderBean) {
		OutProviderBean oldOutProviderBean = outProviderMapper.get(outProviderBean.getProviderId());
		
		outProviderBean.setCreator(oldOutProviderBean.getCreator());
		outProviderBean.setCreateTime(oldOutProviderBean.getCreateTime());
		outProviderBean.setUpdateTime(new Date());
		outProviderBean.setTmstamp(oldOutProviderBean.getTmstamp());
		
		Integer num = outProviderMapper.update(outProviderBean);
		return num;
	}

}
