package com.lyarc.tp.corp.contract.contract.service;

import com.lyarc.tp.corp.common.util.CommonUtil;
import com.lyarc.tp.corp.contract.contract.bean.*;
import com.lyarc.tp.corp.contract.contract.dao.ContractDetailMapper;
import com.lyarc.tp.corp.contract.contract.dao.ContractMapper;
import com.lyarc.tp.corp.contract.contract.dao.ContractProjectDetailMapper;
import com.lyarc.tp.corp.contract.contract.dao.ContractProjectMapper;
import com.lyarc.tp.corp.contract.contract.dto.ContractDto;
import com.lyarc.tp.corp.contract.contract.dto.ContractProjectDto;
import com.lyarc.tp.corp.contract.contract.form.ContractDetailForm;
import com.lyarc.tp.corp.contract.contract.form.ContractForm;
import com.lyarc.tp.corp.contract.contract.form.ContractProjectDetailForm;
import com.lyarc.tp.corp.contract.contract.form.ContractProjectForm;
import com.lyarc.tp.corp.system.material.dao.MaterialMapper;
import com.lyarc.tp.corp.util.JMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContractProjectService {
    @Autowired
    private ContractProjectMapper contractProjectMapper;
    @Autowired
    private ContractProjectDetailMapper cpdMapper;

    @Autowired
    private MaterialMapper materialMapper;

    public List<ContractProjectDto> list(ContractProjectQueryBean queryBean) {
        List<ContractProjectDto> contractList = contractProjectMapper.list(queryBean);
        return contractList;
    }

    public Long count(ContractProjectQueryBean queryBean) {
        Long count =  contractProjectMapper.count(queryBean);
        return count;
    }
    public List<ContractProjectDto> waitChangeList(ContractProjectQueryBean queryBean) {
        List<ContractProjectDto> contractList = contractProjectMapper.waitChangeList(queryBean);
        return contractList;
    }

    public Long waitChangeCount(ContractProjectQueryBean queryBean) {
        Long count =  contractProjectMapper.waitChangeCount(queryBean);
        return count;
    }

    @Transactional
    public Integer delete(String contractId) {
        //删除明细
        cpdMapper.deleteByContractId(contractId);
        int rows = contractProjectMapper.deleteByPrimaryKey(contractId);
        return rows;
    }

    @Transactional
    public Integer update(ContractProjectForm contractProjectForm) {
        ContractProject contractProject = JMapperUtils.getDestination(contractProjectForm, ContractProject.class, ContractProjectForm.class);
        contractProject.setUpdateTime(LocalDateTime.now());
        int rows = contractProjectMapper.updateByPrimaryKeySelective(contractProject);
        String contractId = contractProject.getContractId();
        //删除明细
        cpdMapper.deleteByContractId(contractId);

        //插入明细
        List<ContractProjectDetailForm> detailList = contractProjectForm.getDetailList();
        detailList.forEach(d->{
            d.setContractId(contractId);
            ContractProjectDetail contractProjectDetail = JMapperUtils.getDestination(d, ContractProjectDetail.class, ContractProjectDetailForm.class);
            cpdMapper.insertSelective(contractProjectDetail);
        });

        return rows;
    }

    @Transactional
    public Integer updateContractProject(ContractProject contract) {
        int rows = contractProjectMapper.updateByPrimaryKeySelective(contract);
        return rows;
    }

    @Transactional
    public Integer add(ContractProjectForm contractProjectForm) {
        ContractProject contractProject = JMapperUtils.getDestination(contractProjectForm, ContractProject.class, ContractProjectForm.class);
        contractProject.setUpdateTime(LocalDateTime.now());
        int row = contractProjectMapper.insertSelective(contractProject);

        //插入明细
        List<ContractProjectDetailForm> detailList = contractProjectForm.getDetailList();
        detailList.forEach(d->{
            ContractProjectDetail contractDetail =
                    JMapperUtils.getDestination(d, ContractProjectDetail.class, ContractProjectDetailForm.class);
            cpdMapper.insertSelective(contractDetail);
        });
        return row;
    }

    private String getContractId() {
        return CommonUtil.createUUID();
    }

 /**
  *
  * @description 获取合同项目档案详情
  * @param
  * @return
  * @author wangting
  * @date 2018/8/13 0013 16:26
  *
  */
    public ContractProjectDto getContract(String contractId) {
        ContractProjectDto contract = contractProjectMapper.selectAndDetail(contractId);
        return contract;
    }

    public ContractProject getContractProjectBean(String contractId) {
        ContractProject contract = contractProjectMapper.selectByPrimaryKey(contractId);
        return contract;
    }

}
