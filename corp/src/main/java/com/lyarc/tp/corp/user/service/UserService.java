package com.lyarc.tp.corp.user.service;

import com.googlecode.jmapper.JMapper;
import com.lyarc.tp.corp.power.dao.PowerMapper;
import com.lyarc.tp.corp.role.bean.Role;
import com.lyarc.tp.corp.role.dao.RoleMapper;
import com.lyarc.tp.corp.user.bean.User;
import com.lyarc.tp.corp.user.bean.UserRole;
import com.lyarc.tp.corp.user.dao.UserMapper;
import com.lyarc.tp.corp.user.dao.UserRoleMapper;
import com.lyarc.tp.corp.user.dto.GetUserDTO;
import com.lyarc.tp.corp.user.dto.QueryUserListDTO;
import com.lyarc.tp.corp.user.form.ModifyPasswordForm;
import com.lyarc.tp.corp.user.form.UpdateStatusForm;
import com.lyarc.tp.corp.user.form.UserAddForm;
import com.lyarc.tp.corp.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PowerMapper powerMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Transactional
    public int add(UserAddForm form) {
        JMapper<User, UserAddForm> jmapper = new JMapper<>(User.class, UserAddForm.class);
        User user = jmapper.getDestination(form);
        String password = form.getPassword();
        String idStr = form.getRoleIds();
        String[] ids = idStr.split(",");
        if (password != null && StringUtils.isNotBlank(password)) {
            password = encodePassword(password);
            user.setPassword(password);
        }
        int i = userMapper.insertSelective(user);

        List<UserRole> userRoleList = new ArrayList<>();
        for (String id : ids) {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(Integer.parseInt(id));
            userRoleList.add(ur);
        }
        userRoleMapper.insertBatch(userRoleList);

        return i;
    }


    @Transactional
    public void edit(UserAddForm form) {
        JMapper<User, UserAddForm> jmapper = new JMapper<>(User.class, UserAddForm.class);
        User user = jmapper.getDestination(form);
        String password = form.getPassword();
        String idStr = form.getRoleIds();
        String[] ids =null;
        if(StringUtils.isNotEmpty(idStr)){
            ids = idStr.split(",");
        }

        if (password != null && StringUtils.isNotBlank(password)) {
            password = encodePassword(password);
            user.setPassword(password);
        }
        //删除角色信息
        userMapper.updateByPrimaryKeySelective(user);

        userRoleMapper.deleteByUserId(user.getUserId());
        List<UserRole> userRoleList = new ArrayList<>();
        if(ids!=null){
            for (String id : ids) {
                UserRole ur = new UserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(Integer.parseInt(id));
                userRoleList.add(ur);
            }
            userRoleMapper.insertBatch(userRoleList);
        }

    }

    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
        userRoleMapper.deleteByUserId(id);
    }

    private String encodePassword(String password) {
        try {
            password = MD5Util.getMD5(password);
        } catch (Exception e) {
            logger.error("密码加密错误:" + e.getMessage(),e);
        }
        return password;
    }



    public User getByLoginNameAndPwd(String loginName, String password) {
        return userMapper.getByLoginNameAndPwd(loginName, password);
    }

     public User getByUserId(Integer userId) {
         return userMapper.selectByPrimaryKey(userId);
     }     

     public void modifyPwd(ModifyPasswordForm form) {
         userMapper.modifyPwd(form);
     }

     public User countByLoginName(String loginName) {
         return userMapper.countByLoginName(loginName);
     }

    public List<String> getPowersByUserId(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        List<String> powerKeys = new ArrayList<>();
        if (null != user) {
            powerKeys = powerMapper.getPowerkeysByUserId(user.getUserId());
        }

        return powerKeys;
    }

    public User getByEmpId(String empId) {
        return userMapper.selectByEmpId(empId);
    }

    public User getExistByEmpId(String empId,Integer userId) {
        return userMapper.selectExistByEmpId(empId,userId);
    }

    public GetUserDTO queryByLoginName(String loginName) {
        return userMapper.queryByLoginName(loginName);
    }


    /**
     *
     * 获取子账号详情
     *
     * @param id
     * @return
     *
     * @author zhangjingtao
     * @date 2017年9月5日 上午10:12:43
     */
    public GetUserDTO get(Integer id) {
        GetUserDTO admin = userMapper.get(id);
        List<Role> roles = roleMapper.findByUserId(id);
        String roleIds = roles.stream().map(role -> role.getRoleId() + "").collect(Collectors.toList()).stream().collect(Collectors.joining(","));
        admin.setRoleIds(roleIds);
        StringBuilder stringBuilder = new StringBuilder();
        if (!roles.isEmpty()) {
            for (Role roleBean : roles) {
                String roleName = roleBean.getRoleName();
                stringBuilder.append(roleName + ",");
            }
            int length = stringBuilder.toString().length();
            String subStr = stringBuilder.toString();
            admin.setRoleNames(subStr.substring(0, length - 1));
        }
        return admin;
    }

    public Integer count(Map<String, Object> map) {
        return userMapper.count(map);
    }



    /**
     *
     * 获取子账号数据列表
     *
     * @return
     *
     * @author zhangjingtao
     * @date 2017年9月4日 下午5:46:21
     */
    public List<QueryUserListDTO> list(Map<String, Object> map) {
        List<QueryUserListDTO> listParam = userMapper.list(map);
        for (QueryUserListDTO param : listParam) {
            List<Role> roles = roleMapper.findByUserId(param.getUserId());
            String roleIds = roles.stream().map(role -> role.getRoleId() + "").collect(Collectors.toList()).stream().collect(Collectors.joining(","));
            param.setRoleIds(roleIds);
            StringBuilder stringBuilder = new StringBuilder();
            if (!roles.isEmpty()) {
                for (Role roleBean : roles) {
                    String roleName = roleBean.getRoleName();
                    stringBuilder.append(roleName + ",");
                }
                int length = stringBuilder.toString().length();
                String subStr = stringBuilder.toString();
                param.setRoleNames(subStr.substring(0, length - 1));
            }
        }
        return listParam;
    }


    /**
     *
     * 批量删除
     *
     * @param ids
     * @return
     *
     * @author zhangjingtao
     * @date 2017年9月5日 上午10:18:54
     */
    public Integer removeList(List<Integer> ids) {
        userRoleMapper.deleteByUserIds(ids);
        return userMapper.removeList(ids);

    }

    /**
     *
     * 启用/禁用 子账户
     *
     * @param form
     *
     * @author zhangjingtao
     * @date 2017年9月10日 下午2:00:05
     */
    public void updateStatus(@RequestBody UpdateStatusForm form) {
        userMapper.updateStatus(form);
    }
}
