package com.lyarc.tp.corp.role.dao;

import com.lyarc.tp.corp.role.bean.RolePower;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePowerMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByRoleId(Integer roleId);

    int insert(RolePower record);

    int insertBatch(List<RolePower> list);

    int insertSelective(RolePower record);

    RolePower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePower record);

    int updateByPrimaryKey(RolePower record);

    List<RolePower> selectByRoleId(Integer roleId);
}