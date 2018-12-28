package com.lyarc.tp.corp.contacts.controller;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.contacts.bean.DeptBean;
import com.lyarc.tp.corp.contacts.dto.DeptDTO;
import com.lyarc.tp.corp.contacts.form.DeptForm;
import com.lyarc.tp.corp.contacts.service.DeptService;
import com.lyarc.tp.corp.util.JMapperUtils;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 
 * 部门控制层
 * 
 * @version 1.0
 * @since JDK1.8
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月15日 上午10:38:33
 */
@Controller
public class DeptController {

    @Autowired
    private DeptService service;

    /**
     * 
     * 获取所有部门 企业ID
     * 
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 上午11:33:31
     */
    @RequestMapping(value = "api/contacts/dept/list", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<List<DeptDTO>> list() {
        List<DeptBean> list = service.list();
        List<DeptDTO> dtoList = JMapperUtils.getDestinations(list, DeptDTO.class, DeptBean.class);
        return ResultVo.success(dtoList);
    }

    /**
     * 
     * 添加子部门
     *
     * @param form
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 下午2:28:02
     */
    @RequestMapping(value = "/api/contacts/dept/add", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResultVo<Integer> add(@RequestBody DeptForm form) {
        DeptBean bean = new DeptBean();
        bean.setParentId(form.getDeptId());
        bean.setDeptName(form.getDeptName());
        bean.setSeq(0);
        Integer count = service.getDeptByDeptName(form.getDeptName(),null);
        if(count > 0){
            return ResultVo.build(10113, "添加失败，该部门名称已存在");
        }
        //微信添加部门
//        WxCpDepart depart = JMapperUtils.getDestination(bean, WxCpDepart.class, DeptBean.class);
        WxCpDepart depart = new WxCpDepart();
        depart.setName(bean.getDeptName());
        depart.setOrder(new Long(bean.getSeq()));
        depart.setParentId(bean.getParentId());
        Integer departId = service.wxAddDept(depart);
        //依赖微信生成部门ID
        if(departId!=null){
            bean.setDeptId(departId);
        }
        Integer rult = service.add(bean);        
        return ResultVo.success(rult);
    }

    /**
     * 
     * 修改部门名称
     *
     * @param form
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 下午2:28:16
     */
    @RequestMapping(value = "/api/contacts/dept/edit", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResultVo<Integer> edit(@RequestBody DeptForm form) {
        DeptBean bean = new DeptBean();
        bean.setDeptId(form.getDeptId());
        bean.setDeptName(form.getDeptName());
        Integer count = service.getDeptByDeptName(form.getDeptName(),form.getDeptId());
        if(count > 0){
            return ResultVo.build(10113, "修改失败，该部门名称已存在");
        }
        Integer rult = service.edit(bean);
//        WxCpDepart depart = JMapperUtils.getDestination(bean, WxCpDepart.class, DeptBean.class);
        WxCpDepart depart = new WxCpDepart();
        depart.setName(bean.getDeptName());
        depart.setOrder(new Long(bean.getSeq()));
        depart.setParentId(bean.getParentId());
        service.wxEditDept(depart);
        return ResultVo.success(rult);
    }

    /**
     * 
     * 删除部门
     *
     * @param deptId
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 下午4:28:39
     */
    @RequestMapping(value = "/api/contacts/dept/delete", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResultVo<Integer> delete(Integer deptId) {
        Integer count = service.havaDepts(deptId);
        if (count > 0) {
            return ResultVo.build(10111, "该部门下有子部门，不能删除");
        }
        Integer empCount = service.empCountByDept(deptId);
        if(empCount > 0) {
            return ResultVo.build(10112, "该部门下有员工数据，不能删除");
        }
        Integer rult = service.delete(deptId);

        service.wxDeleteDept(deptId);
        return ResultVo.success(rult);
    }
     
}
