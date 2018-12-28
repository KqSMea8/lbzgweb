package com.lyarc.tp.corp.user.service;

import com.lyarc.tp.corp.role.bean.Role;
import com.lyarc.tp.corp.user.bean.User;
import com.lyarc.tp.corp.user.bean.UserRole;
import com.lyarc.tp.corp.user.dao.UserRoleMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
  *
  * @description
  * @param
  * @return
  * @author wangting
  * @date 2018/7/3 0003 14:16
  *
  */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public UserRole selectUserRole(Integer userId,Integer roleId){
        UserRole userRole = userRoleMapper.selectUserRole(userId, roleId);
        return userRole;
    }
    
    public List<User> selectUserByRole(Integer roleId) {
        return userRoleMapper.selectUserByRole(roleId);
    }

}
