package com.lyarc.tp.corp.outprocess.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.outprocess.provider.bean.OutProviderBean;

@Mapper
public interface OutProviderMapper {
	
	// 数据
	List<OutProviderBean> query(OutProviderBean outProviderBean);
	
	// 数量
	long count(OutProviderBean outProviderBean);

	// 添加
	Integer add(OutProviderBean outProviderBean);

	// 查询
	OutProviderBean get(@Param("providerId") String providerId);

	// 更新
	Integer update(OutProviderBean outProviderBean);

}
