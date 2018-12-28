package com.lyarc.tp.corp.role.dao;

import com.lyarc.tp.corp.role.bean.Role;
import com.lyarc.tp.corp.role.bean.RoleQueryBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId); 

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Role findByName(@Param("roleName") String roleName);

    Role getExistName(@Param("roleName") String roleName ,@Param("roleId") Integer roleId);

    Role findById(@Param("roleId") Integer roleId);

    List<Role> list(RoleQueryBean roleQueryBean);

    Long count(RoleQueryBean roleQueryBean);

    void delete(@Param("roleIds") Integer[] roleIds);

    List<Role> findByUserId(@Param("userId") Integer userId);
}