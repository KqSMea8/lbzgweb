package com.lyarc.tp.corp.role.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.role.bean.Role;
import com.lyarc.tp.corp.role.bean.RoleQueryBean;
import com.lyarc.tp.corp.role.dao.RolePowerMapper;
import com.lyarc.tp.corp.role.dto.RoleDTO;
import com.lyarc.tp.corp.role.form.RoleForm;
import com.lyarc.tp.corp.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年09月04日 15:45:47
 */
@Controller
@RequestMapping(value = "/api/role", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 方法描述 添加角色
     * 
     * @param roleForm 表单对象
     * @return ResultVo<Integer>
     *
     * @author huanghaoqi
     * @date 2017年09月04日 15:56:41
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultVo<Integer> add(@RequestBody RoleForm roleForm) {
        LoginUser loginUser = UserHolder.getUser();
        Integer creator = loginUser.getUserId();
        Role roleBeanNew = roleService.getByName(roleForm.getRoleName());
        if (roleBeanNew != null) {
            return ResultVo.build(409, "角色名称已存在");
        }
        roleForm.setCreator(creator);
        Integer id = roleService.add(roleForm);
        return ResultVo.success(id);
    }

    /**
     * 方法描述 修改角色
     * 
     * @param roleForm 表单对象
     * @return ResultVo
     *
     * @author huanghaoqi
     * @date 2017年09月04日 17:38:41
     */
    @RequestMapping("/edit")
    @ResponseBody
    public ResultVo<Integer> edit(@RequestBody RoleForm roleForm) {
        LoginUser loginUser = UserHolder.getUser();
        Integer creator = loginUser.getUserId();
        Role roleBeanNew = roleService.getExistName(roleForm.getRoleName(),roleForm.getRoleId());
        if (roleBeanNew != null) {
            if (!roleBeanNew.getRoleId().equals(roleForm.getRoleId())) {
                return ResultVo.build(409, "角色名称已存在");
            }
        }
        roleForm.setCreator(creator);
//        //删除角色权限映射
//        rolePowerMapper.deleteByPrimaryKey(role.getRoleId());
        roleService.update(roleForm);
        return ResultVo.success();
    }

    /**
     * 方法描述 删除角色
     *
     * @param roleIds 角色ID数组
     * @return ResultVo
     *
     * @author huanghaoqi
     * @date 2017年09月04日 17:46:29
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("roleIds") Integer[] roleIds) {
        LoginUser loginUser = UserHolder.getUser();
        String corpId = loginUser.getCorpId();
        roleService.delete(roleIds);
        return ResultVo.success();
    }

    /**
     * 方法描述 查询角色列表
     * 
     * @param queryBean 查询参数
     * @return ResultVo<PageResultBean<RoleDTO>>
     *
     * @author huanghaoqi
     * @date 2017年09月04日 18:16:04
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<PageResultBean<RoleDTO>> list(@RequestBody RoleQueryBean queryBean) {
        LoginUser loginUser = UserHolder.getUser();
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }
        List<RoleDTO> roleDtoList = roleService.list(queryBean);
        Long count = roleService.count(queryBean);
        PageResultBean<RoleDTO> resultBean = PageResultBean.success(count, roleDtoList);
        return ResultVo.success(resultBean);
    }

    /**
     * 方法描述 获取角色详情
     *
     * @param roleId 角色ID
     * @return ResultVo<RoleDTO>
     *
     * @author huanghaoqi
     * @date 2017年09月04日 18:27:44
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResultVo<RoleDTO> detail(@RequestParam("roleId") Integer roleId) {
        RoleDTO roleDto = roleService.getDetail(roleId);
        return ResultVo.success(roleDto);
    }
}
