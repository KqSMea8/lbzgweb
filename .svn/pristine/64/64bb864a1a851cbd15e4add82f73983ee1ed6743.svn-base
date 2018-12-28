package com.lyarc.tp.corp.contract.contract.dao;

import com.lyarc.tp.corp.contract.contract.bean.ContractProjectDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContractProjectDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContractProjectDetail record);

    int insertSelective(ContractProjectDetail record);

    ContractProjectDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContractProjectDetail record);

    int updateByPrimaryKey(ContractProjectDetail record);

    void deleteByContractId(String contractId);
}