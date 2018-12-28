package com.lyarc.tp.corp.quality.certification.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.quality.certification.bean.QualifiedReport;

@Mapper
public interface QualifiedReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(QualifiedReport record);

    int insertSelective(QualifiedReport record);

    List<QualifiedReport> selectByPrimaryKeys();
    
    int updateByPrimaryKeySelective(QualifiedReport record);
    int updByCertificateId(QualifiedReport record);
    int updateByPrimaryKey(QualifiedReport record);


	List<String> listAllC(QualifiedReport queryBean);
	
	Long counts(QualifiedReport bean);

}