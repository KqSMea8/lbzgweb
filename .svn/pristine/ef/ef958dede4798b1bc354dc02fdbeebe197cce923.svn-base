package com.lyarc.tp.corp.contacts.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.ServiceException;
import com.lyarc.tp.corp.contacts.bean.DeptBean;
import com.lyarc.tp.corp.contacts.dao.DeptMapper;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * 部门 service层
 * 
 * @version 1.0
 * @since JDK1.7
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月15日 上午10:49:38
 */
@Service
public class DeptService {

    @Autowired
    private WxCpService wxCpService;

    @Autowired
    private DeptMapper mapper;
    
    private static Logger logger = LoggerFactory.getLogger(DeptService.class);

    /**
     * 
     * 获取部门列表
     *
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 上午11:59:34
     */
    public List<DeptBean> list() {
        return mapper.list();
    }

    /**
     * 
     * 添加子部门 添加到通讯录同时同步到企业微信中
     * 
     * @param bean
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 上午11:59:04
     */
    public Integer add(DeptBean bean) {
        return mapper.add(bean);
    }

    /**
     * 
     * 修改部门信息
     *
     * @param bean
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 下午2:25:52
     */
    public Integer edit(DeptBean bean) {
        return mapper.edit(bean);
    }

    /**
     * 
     * 删除部门
     *
     * @param deptId
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 下午2:33:31
     */
    public Integer delete(Integer deptId) {
        return mapper.delete(deptId);
    }


    /**
     * 
     * 是否有子部门
     *
     * @return
     * 
     * @author jianghan
     * @date 2017年8月16日 上午9:18:07
     */
    public Integer havaDepts(Integer deptId) {
        return mapper.havaDepts(deptId);
    }
    
    /**
     * 
     * 查询部门下是否有员工数据
     *
     * @param deptId
     * @return
     * 
     * @author jianghan
     * @date 2017年9月10日 下午2:09:45
     */
    public Integer empCountByDept(Integer deptId){
        return mapper.empCountByDept(deptId);
    }
    
    public Integer getDeptByDeptName(String deptName,Integer deptId){
        return mapper.getDeptByName(deptName,deptId);
    }
    /**
     * 
     * 依据部门ids获取部门列表
     * 
     * @param list
     * @param corpId
     * @return 
     * 
     * @author zhangjingtao 
     * @date 2017年9月12日 下午1:05:08
     */
    public List<DeptBean> deptByDeptIds(List<Integer> list,String corpId){
		return mapper.deptByDeptIds(list);
    }

    /**
     *
     * 微信添加部门
     *
     * @param depart
     *
     * @author jianghan
     * @date 2017年9月16日 上午9:07:42
     */
    public Integer wxAddDept(WxCpDepart depart){
        try {
            return wxCpService.getDepartmentService().create(depart);
        } catch (Exception e) {
            logger.error("微信添加部门出错,异常信息为：" + e.getMessage(),e);
            throw new ServiceException(ResultVo.failure("微信添加部门失败"));
        }
    }

    /**
     *
     * 微信修改部门
     *
     * @param depart
     *
     * @author jianghan
     * @date 2017年9月19日 下午3:17:04
     */
    public void wxEditDept(WxCpDepart depart){
        try {
            wxCpService.getDepartmentService().update(depart);
        } catch (WxErrorException e) {
            logger.error("微信同步部门出错,异常信息为：" + e.getMessage());
            throw new ServiceException(ResultVo.failure("获取微信部门失败"));

        }
    }
    /**
     *
     * 微信删除部门
     *
     * @param deptId
     *
     * @author jianghan
     * @date 2017年9月19日 下午3:17:30
     */
    public void wxDeleteDept(Integer deptId){
        try {
            wxCpService.getDepartmentService().delete(deptId);
        } catch (WxErrorException e) {
            logger.error("微信同步部门出错,异常信息为：" + e.getMessage());
            throw new ServiceException(ResultVo.failure("获取微信部门失败"));
        }
    }

    public DeptBean getDeptById(Integer deptId) {
        DeptBean deptBean = mapper.getDeptById(deptId);
        return deptBean;
    }
}
