package com.lyarc.tp.corp.system.customer.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.contract.contract.bean.ContractQueryBean;
import com.lyarc.tp.corp.contract.contract.dao.ContractMapper;
import com.lyarc.tp.corp.contract.inquire.bean.Inquire;
import com.lyarc.tp.corp.contract.inquire.bean.InquireQueryBean;
import com.lyarc.tp.corp.contract.inquire.dao.InquireMapper;
import com.lyarc.tp.corp.system.customer.bean.Customer;
import com.lyarc.tp.corp.system.customer.bean.CustomerQueryBean;
import com.lyarc.tp.corp.system.customer.dao.CustomerMapper;
import com.lyarc.tp.corp.system.material.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private InquireMapper inquireMapper;
    @Autowired
    private ContractMapper contractMapper;

    public List<CustomerDto> list(CustomerQueryBean queryBean) {
        List<CustomerDto> customerList = customerMapper.list(queryBean);
        return customerList;
    }

    public Long count(CustomerQueryBean queryBean) {
       Long count =  customerMapper.count(queryBean);
       return count;
    }

    public Integer delete(String customerId) {
        //校验客户是否发生业务数据
        InquireQueryBean inquireQueryBean = new InquireQueryBean();
        inquireQueryBean.setCustomerId(customerId);
        List<Inquire> list = inquireMapper.list(inquireQueryBean);
        if(list != null && list.size() > 0){
            throw new CorpException(ResultVo.failure("该客户已发生业务，无法删除"));
        }
        //查询合同
        ContractQueryBean contractQueryBean = new ContractQueryBean();
        contractQueryBean.setCustomerId(customerId);
        List<Contract> contractList = contractMapper.list(contractQueryBean);
        if(contractList != null && contractList.size() > 0){
            throw new CorpException(ResultVo.failure("该客户已发生业务，无法删除"));
        }

        int rows = customerMapper.deleteByPrimaryKey(customerId);
        return rows;
    }

    public Integer update(Customer customer) {
        int rows = customerMapper.updateByPrimaryKeySelective(customer);
        return rows;
    }

    public Integer add(Customer customer) {
        //校验id 是否重复
        String customerId = customer.getCustomerId();
        Customer existCustomer = customerMapper.selectByPrimaryKey(customerId);
        if(existCustomer != null){
            throw new CorpException(ResultVo.failure("往来户id已存在"));
        }
        int row = customerMapper.insertSelective(customer);
        return row;
    }

    public Customer getCustomer(String customerId) {
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        return customer;
    }

}
