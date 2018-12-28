package com.lyarc.tp.corp.contract.inquire.dao;

import com.lyarc.tp.corp.contract.inquire.bean.Inquire;
import com.lyarc.tp.corp.contract.inquire.bean.InquireQueryBean;
import com.lyarc.tp.corp.contract.inquire.dto.InquireDto;
import com.lyarc.tp.corp.system.customer.bean.Customer;
import com.lyarc.tp.corp.system.customer.bean.CustomerQueryBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface InquireMapper {
    int deleteByPrimaryKey(String inquireId);

    int insert(Inquire record);

    int insertSelective(Inquire record);

    Inquire selectByPrimaryKey(String inquireId);

    int updateByPrimaryKeySelective(Inquire record);

    int updateByPrimaryKeyWithBLOBs(Inquire record);

    int updateByPrimaryKey(Inquire record);

    InquireDto selectAndDetail(String inquireId);

    List<Inquire> list(InquireQueryBean queryBean);

    Long count(InquireQueryBean queryBean);

    Integer createSequence(@Param("currentDate") String currentDate);
}