package com.lyarc.tp.corp.user.dao;

import com.lyarc.tp.corp.role.bean.Role;
import com.lyarc.tp.corp.role.bean.RolePower;
import com.lyarc.tp.corp.user.bean.User;
import com.lyarc.tp.corp.user.bean.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByUserId(@Param("userId") Integer userId);

    int deleteByUserIds(List<Integer> list);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    UserRole selectUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRole> getUserRoleInRoleIds(@Param("roleIds") Integer[] roleIds);

    int insertBatch(List<UserRole> list);
    
    List<User> selectUserByRole(@Param("roleId") Integer roleId);
}