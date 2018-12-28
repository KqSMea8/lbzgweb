package com.lyarc.tp.corp.pack.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.pack.bean.PackDetailBean;

@Mapper
public interface PackDetailMapper {
	
	List<PackDetailBean> query(@Param("packId") String packId);
	
	Long count(@Param("packId") String packId);
	
	PackDetailBean get(@Param("id") Integer id);
	
	Integer add(PackDetailBean packDetailBean);
	
	Integer update(PackDetailBean packDetailBean);
	
	Integer del(@Param("id") Integer id);

}
