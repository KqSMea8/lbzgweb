package com.lyarc.tp.corp.arg.dao;

import com.lyarc.tp.corp.arg.bean.Arg;
import com.lyarc.tp.corp.arg.bean.ArgQueryBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Arg record);

    int insertSelective(Arg record);

    Arg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Arg record);

    int updateByPrimaryKey(Arg record);

    List<Arg> list(ArgQueryBean queryBean);
}