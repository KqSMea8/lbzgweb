package com.lyarc.tp.corp.contract.contract.dao;

import com.lyarc.tp.corp.contract.contract.bean.ContractDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContractDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContractDetail record);

    int insertSelective(ContractDetail record);

    ContractDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContractDetail record);

    int updateByPrimaryKey(ContractDetail record);

    void deleteByContractId(String contractId);
}