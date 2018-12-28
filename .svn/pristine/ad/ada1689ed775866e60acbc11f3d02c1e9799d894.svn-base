package com.lyarc.tp.corp.system.customer.dao;

import com.lyarc.tp.corp.system.customer.bean.Customer;
import com.lyarc.tp.corp.system.customer.bean.CustomerQueryBean;
import com.lyarc.tp.corp.system.material.dto.CustomerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    int deleteByPrimaryKey(String customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    List<CustomerDto> list(CustomerQueryBean queryBean);

    Long count(CustomerQueryBean queryBean);
}