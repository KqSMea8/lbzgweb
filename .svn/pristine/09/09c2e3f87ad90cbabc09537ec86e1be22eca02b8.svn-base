package com.lyarc.tp.corp.pack.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.pack.bean.PackBean;
import com.lyarc.tp.corp.pack.bean.PackQueryBean;

@Mapper
public interface PackMapper {
	
	List<PackBean> query(PackQueryBean packQueryBean);
	
	Long count(PackQueryBean packQueryBean);
	
	PackBean get(@Param("packId") String packId);
	
	Integer add(PackBean packBean);
	
	Integer update(PackBean packBean);
	
	Integer del(@Param("packId") String packId);

}
