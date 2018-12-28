package com.lyarc.tp.corp.contract.contract.dao;

import com.lyarc.tp.corp.contract.contract.bean.ContractProject;
import com.lyarc.tp.corp.contract.contract.bean.ContractProjectQueryBean;
import com.lyarc.tp.corp.contract.contract.dto.ContractProjectDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractProjectMapper {
    int deleteByPrimaryKey(String contractId);

    int insert(ContractProject record);

    int insertSelective(ContractProject record);

    ContractProject selectByPrimaryKey(String contractId);

    int updateByPrimaryKeySelective(ContractProject record);

    int updateByPrimaryKey(ContractProject record);


    List<ContractProjectDto> list(ContractProjectQueryBean queryBean);

    Long count(ContractProjectQueryBean queryBean);

    ContractProjectDto selectAndDetail(String contractId);

    List<ContractProjectDto> waitChangeList(ContractProjectQueryBean queryBean);

    Long waitChangeCount(ContractProjectQueryBean queryBean);
}