package com.lyarc.tp.corp.pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.pack.bean.PackDetailBean;
import com.lyarc.tp.corp.pack.dao.PackDetailMapper;

@Service
public class PackDetailService {
	
	@Autowired
	private PackDetailMapper packDetailMapper;
	
	@SuppressWarnings("rawtypes")
	public PageResultBean query(String packId){
		List<PackDetailBean> packDetailList = packDetailMapper.query(packId);
		Long count = packDetailMapper.count(packId);
		return PageResultBean.success(count, packDetailList);
	}
	
	public PackDetailBean get(Integer packId) {
		PackDetailBean packDetailBean = packDetailMapper.get(packId);
		return packDetailBean;
	}
	
}
