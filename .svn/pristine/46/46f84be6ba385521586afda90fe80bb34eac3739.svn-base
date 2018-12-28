package com.lyarc.tp.corp.quality.certification.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.quality.certification.bean.CastingCertificate;
import com.lyarc.tp.corp.quality.certification.bean.ContractDetailQueryBean;
@Mapper
public interface CastingCertificateMapper {
    int deleteByPrimaryKey(@Param("certificateId") String certificateId);

    int insert(CastingCertificate record);

    List<CastingCertificate> selectByPrimaryKeys();

    int updateByPrimaryKeySelective(CastingCertificate record);

    int updateByPrimaryKey(CastingCertificate record);

	Integer insertSelective(CastingCertificate record);

	List<ContractDetailQueryBean> list(ContractDetailQueryBean bean);

	Long count(ContractDetailQueryBean bean);
	
	Long findLine(ContractDetailQueryBean line);

	List<ContractDetailQueryBean> selcetById(ContractDetailQueryBean bean);

	ContractDetailQueryBean selectByAllKey(String contractId);

//	List<CastingCertificate> selectByPrimaryKey(String certificateId);

	CastingCertificate selectByPrimaryKey(CastingCertificate bean);
	
	int updStatusC(CastingCertificate record);
}