package com.lyarc.tp.corp.contract.contract.dao;

import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.contract.contract.bean.ContractQueryBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {
    int deleteByPrimaryKey(String contractId);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(String contractId);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);

    Contract selectAndDetail(String contractId);
    
    List<Contract> list(ContractQueryBean queryBean);

    Long count(ContractQueryBean queryBean);
    
    Integer getUnShippingCompleteCount(String contractId);
}