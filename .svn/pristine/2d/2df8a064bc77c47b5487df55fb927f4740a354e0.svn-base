package com.lyarc.tp.corp.role.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.role.bean.Role;
import com.lyarc.tp.corp.role.bean.RolePower;
import com.lyarc.tp.corp.role.bean.RoleQueryBean;
import com.lyarc.tp.corp.role.dao.RoleMapper;
import com.lyarc.tp.corp.role.dao.RolePowerMapper;
import com.lyarc.tp.corp.role.dto.RoleDTO;
import com.lyarc.tp.corp.role.form.RoleForm;
import com.lyarc.tp.corp.user.bean.UserRole;
import com.lyarc.tp.corp.user.dao.UserMapper;
import com.lyarc.tp.corp.user.dao.UserRoleMapper;
import com.lyarc.tp.corp.util.JMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @param
 * @author wangting
 * @description 角色管理
 * @return
 * @date 2018/7/3 0003 16:04
 */
@Service
public class RoleService {


    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePowerMapper rolePowerMapper;

    /**
     * 方法描述 添加角色
     *
     * @param roleForm
     * @return Integer 主键ID
     * @author huanghaoqi
     * @date 2017年09月04日 15:56:03
     */
    @Transactional
    public Integer add(RoleForm roleForm) {

        Role role = JMapperUtils.getDestination(roleForm, Role.class, RoleForm.class);

        roleMapper.insertSelective(role);
        Integer roleId = role.getRoleId();
        List<RolePower> rpList = new ArrayList<>();
        String powerIds = roleForm.getPowerIds();
        String[] split = powerIds.split(",");
        Set<String> powerSet = new HashSet<String>();
        powerSet.addAll(Arrays.asList(split));
        for (String powerId : powerSet) {
            RolePower rp = new RolePower();
            rp.setRoleId(roleId);
            rp.setPowerId(Integer.parseInt(powerId));
            rp.setCreator(roleForm.getCreator());
            rpList.add(rp);
        }
        rolePowerMapper.insertBatch(rpList);

        return roleId;
    }

    /**
     * 方法描述 修改角色
     *
     * @param roleForm 角色对象
     * @author huanghaoqi
     * @date 2017年09月04日 17:37:20
     */
    @Transactional
    public void update(RoleForm roleForm) {
        Role role = JMapperUtils.getDestination(roleForm, Role.class, RoleForm.class);
        roleMapper.updateByPrimaryKeySelective(role);
        //删除角色权限映射
        rolePowerMapper.deleteByRoleId(role.getRoleId());
        //批量插入角色权限映射
        Integer roleId = role.getRoleId();
        List<RolePower> rpList = new ArrayList<>();
        String powerIds = roleForm.getPowerIds();
        String[] split = powerIds.split(",");
        //去重
        Set<String> powerSet = new HashSet<String>();
        powerSet.addAll(Arrays.asList(split));
        for (String powerId : powerSet) {
            RolePower rp = new RolePower();
            rp.setRoleId(roleId);
            rp.setPowerId(Integer.parseInt(powerId));
            rp.setCreator(roleForm.getCreator());
            rpList.add(rp);
        }
        rolePowerMapper.insertBatch(rpList);
    }

    /**
     * 方法描述 删除角色
     *
     * @param roleIds 角色集合
     * @author huanghaoqi
     * @date 2017年09月04日 17:44:56
     */
    public void delete(Integer[] roleIds) {
        List<UserRole> userRoleList = userRoleMapper.getUserRoleInRoleIds(roleIds);
        if (userRoleList != null && !userRoleList.isEmpty()) {
            throw new CorpException(ResultVo.failure(10401, "选择的角色已经被用户绑定"));
        }
        roleMapper.delete(roleIds);
    }

    /**
     * 方法描述 查询角色列表
     *
     * @param queryBean 查询参数
     * @return PageResultBean<RoleDTO>
     * @author huanghaoqi
     * @date 2017年09月04日 18:08:48
     */
    public List<RoleDTO> list(RoleQueryBean queryBean) {
        List<Role> RoleList = roleMapper.list(queryBean);
        return JMapperUtils.getDestinations(RoleList, RoleDTO.class, Role.class);
    }

    /**
     * 方法描述 获取总数
     *
     * @param queryBean 查询参数
     * @return Long
     * @author huanghaoqi
     * @date 2017年09月04日 18:13:15
     */
    public Long count(RoleQueryBean queryBean) {
        return roleMapper.count(queryBean);
    }

    /**
     * 方法描述 查看角色详情
     *
     * @param roleId 角色ID
     * @return
     * @author huanghaoqi
     * @date 2017年09月04日 18:26:06
     */
    public RoleDTO getDetail(Integer roleId) {
        Role role = roleMapper.findById(roleId);
        RoleDTO roleDTO = JMapperUtils.getDestination(role, RoleDTO.class, Role.class);
        List<RolePower> rolePowers = rolePowerMapper.selectByRoleId(roleId);
        List<String> powerIdList = rolePowers.stream().map(power->power.getPowerId()+"").collect(Collectors.toList());
        String powerIds = powerIdList.stream().collect(Collectors.joining(","));
        roleDTO.setPowerIds(powerIds);
        return roleDTO;
    }

    /**
     * 方法描述 根据名称查询
     *
     * @param roleName 角色名称
     * @return Role
     * @author huanghaoqi
     * @date 2017年09月05日 11:05:02
     */
    public Role getByName(String roleName) {
        return roleMapper.findByName(roleName);
    }

    public Role getExistName(String roleName,Integer roleId) {
        return roleMapper.getExistName(roleName,roleId);
    }

}
