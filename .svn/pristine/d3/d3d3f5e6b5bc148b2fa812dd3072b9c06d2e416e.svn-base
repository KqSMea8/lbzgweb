package com.lyarc.tp.corp.user.controller;

import com.googlecode.jmapper.JMapper;
import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.role.bean.Role;
import com.lyarc.tp.corp.user.bean.User;
import com.lyarc.tp.corp.user.dto.GetUserDTO;
import com.lyarc.tp.corp.user.dto.QueryUserListDTO;
import com.lyarc.tp.corp.user.form.*;
import com.lyarc.tp.corp.user.service.UserRoleService;
import com.lyarc.tp.corp.user.service.UserService;
import com.lyarc.tp.corp.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 子账号管理
 *
 *
 * @version 1.0
 * @since JDK1.8
 * @author zhangjingtao
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年9月4日 下午3:45:08
 */
@ResponseBody
@Controller
@RequestMapping(path = "/api/admin", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    /**
     *
     * 添加子账号
     *
     * @param form
     * @return
     *
     * @author zhangjingtao
     *
     * @date 2017年9月4日 下午3:47:37
     */
    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResultVo<Integer> add(@RequestBody UserAddForm form) {
        String loginName = form.getLoginName();
        GetUserDTO adminDTO = userService.queryByLoginName(loginName);
        if (adminDTO != null) {
            return ResultVo.build(10302, "登录账号已存在");
        }
        if(StringUtils.isNotEmpty(form.getEmpId())) {
            User existUser = userService.getByEmpId(form.getEmpId());
            if (null != existUser) {
                return ResultVo.build(10303, "该员工编号已经被账号" + existUser.getLoginName() + "绑定");
            }
        }
        userService.add(form);
        return ResultVo.success();
    }

    /**
     *
     * 修改子账号
     *
     * @param form
     * @return
     *
     * @author zhangjingtao
     * @date 2017年9月4日 下午5:03:22
     */
    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public ResultVo<Integer> edit(@RequestBody UserAddForm form) {
        Integer id = form.getUserId();
        GetUserDTO adminDTO = userService.get(id);
        if (adminDTO == null) {
            return ResultVo.build(10301, "子账号id错误");
        }
        GetUserDTO adminDTOByLoginName = userService.queryByLoginName(form.getLoginName());
        if (adminDTOByLoginName != null && !adminDTOByLoginName.getUserId().equals(id)) {
            return ResultVo.build(10302, "登录账号已存在");
        }
        if(StringUtils.isNotEmpty(form.getEmpId())){
            User existUser = userService.getExistByEmpId(form.getEmpId(),form.getUserId());
            if(null !=existUser){
                return ResultVo.build(10303, "该员工编号已经被账号" +existUser.getLoginName()+"绑定");
            }
        }
//        User user = setPassword(form);
        userService.edit(form);
        return ResultVo.success();
    }


    /**
     * 
     * 密码加密
     * 
     * @param password
     * @return
     * 
     * @author zhangjingtao
     * @date 2017年9月5日 下午3:53:07
     */
    private static String encodePassword(String password) {
        try {
            password = MD5Util.getMD5(password);
        } catch (Exception e) {
            logger.error("密码加密错误:" + e.getMessage());
        }
        return password;

    }

    /**
     *
     * 获取子账号列表
     *
     * @param form
     * @return
     *
     * @author zhangjingtao
     * @date 2017年9月5日 上午9:58:10
     */
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResultVo<UserListForm> list(@RequestBody QueryUserForm form) {
        Integer page = form.getPage();
        Integer pageSize = form.getPageSize();
        Integer order = form.getOrder();
        Integer requireTotal = form.getRequireTotal();
        String sort = form.getSort();
//        String trueName = form.getTrueName();
        String keyWord = form.getKeyword();

        if (page == null || page < 1) {
            page = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 12;
        }
        // 1 升序 2.降序
        if (order == null) {
            order = 2;
        }
        if (sort == null) {
            sort = "create_time";
        }
        if (requireTotal == null) {
            requireTotal = 1;
        }
        if (StringUtils.isNotBlank(keyWord)) {
            keyWord = keyWord.trim();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyWord);
        UserListForm result = new UserListForm();
        // 获取总记录数
        if (requireTotal == 1) {
            Integer total = userService.count(map);
            result.setTotal(total);
        } else {
            result.setTotal(-1);
        }
        map.put("sort", sort);
        Integer offest = (page - 1) * pageSize;
        map.put("offest", offest);
        map.put("pageSize", pageSize);
        map.put("order", order);
        List<QueryUserListDTO> rows = userService.list(map);
        result.setRows(rows);
        return ResultVo.success(result);
    }

    /**
     *
     * 获取子账号
     *
     * @return
     *
     * @author zhangjingtao
     * @date 2017年9月5日 上午9:58:35
     */
    @RequestMapping(path = "/get", method = RequestMethod.POST)
    public ResultVo<GetUserDTO> get(Integer id) {
        GetUserDTO adminDTO = userService.get(id);
        return ResultVo.success(adminDTO);
    }

    /**
     *
     * 删除某一子账号
     *
     * @param id
     * @return
     *
     * @author zhangjingtao
     * @date 2017年9月5日 上午10:11:57
     */
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ResultVo<Integer> delete(Integer id) {
        userService.delete(id);
        return ResultVo.success();
    }

    /**
     *
     * 批量删除子账号
     *
     * @return
     *
     * @author zhangjingtao
     * @date 2017年9月5日 上午10:16:12
     */
    @RequestMapping(path = "/deleteList", method = RequestMethod.POST)
    public ResultVo<Integer> deletes(@RequestParam(value = "ids") Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        Integer result = userService.removeList(list);
        return ResultVo.success(result);
    }

    /**
     * 修改密码
     *
     *
     * @return
     *
     * @author zhangjingtao
     * @date 2017年9月5日 下午3:36:15
     */
    @RequestMapping(path = "/modifyPwd", method = RequestMethod.POST)
    public ResultVo<Integer> modifyPwd(@RequestBody ModifyPasswordForm form) {

        String password = form.getPassword();
        password = encodePassword(password);
        form.setPassword(password);
        form.setLastUpdate(LocalDateTime.now());
        userService.modifyPwd(form);
        return ResultVo.success();
    }

    /**
     *
     * 启用/禁用 子账户
     *
     * @param form
     * @return
     *
     * @author zhangjingtao
     * @date 2017年9月10日 下午1:58:39
     */
    @RequestMapping(path = "/updateStatus", method = RequestMethod.POST)
    public ResultVo<Integer> updateStatus(@RequestBody UpdateStatusForm form) {
        form.setLastUpdate(LocalDateTime.now());
        Integer status = form.getStatus();
        if (status == 1) {
            form.setStatus(2);
        } else {
            form.setStatus(1);
        }
        userService.updateStatus(form);
        return ResultVo.success();
    }
}
