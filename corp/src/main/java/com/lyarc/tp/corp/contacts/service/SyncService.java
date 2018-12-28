package com.lyarc.tp.corp.contacts.service;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.base.Splitter;
import com.googlecode.jmapper.JMapper;
import com.lyarc.tp.corp.common.DateUtil;
import com.lyarc.tp.corp.contacts.bean.DeptBean;
import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.dao.DeptMapper;
import com.lyarc.tp.corp.contacts.dao.EmpMapper;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 同步信息Service
 * 
 * @version 1.0
 * @since JDK1.7
 * @author lidoudou
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月23日 下午6:04:46
 */
@Service
public class SyncService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    @Qualifier("wxCpService")
    private WxCpService cpService;

    public static Integer syncStatus = 0;

    public static String startTime;

    public static String endTime;

    public static Long threadId;

    public static Long useTime;

    public static String execResult;

    private static Logger logger = LoggerFactory.getLogger(SyncService.class);

    @Async
    @Transactional
    public void syncData(){
        Integer deptCount = 0;
        Integer count = 0;
        synchronized (this) {
            if(syncStatus == 0){
                syncStatus = 1;
                long s = System.currentTimeMillis();
                startTime = DateUtil.getDateStr(new Date(),"yyyy-MM-dd HH:mm:ss");
                threadId = Thread.currentThread().getId();
                try{
                    deptCount = syncDept();
                    count = syncUser();
                }catch (Exception e){
                    logger.error("同步出错",e);
                }finally {
                    syncStatus = 0;
                }
                long e = System.currentTimeMillis();
                useTime = (e - s)/1000;
                endTime = DateUtil.getDateStr(new Date(),"yyyy-MM-dd HH:mm:ss");
                execResult = "同步用户信息" + count + "条，同步部门" + deptCount + "条。";
                syncStatus = 0;
            }
        }
    }

    /**
     * 用户同步
     *
     *
     * @author lidoudou
     * @date 2017年8月23日 下午2:58:40
     */
    @Transactional
    public Integer syncUser() {

        JMapper<EmployeeBean, WxCpUser> jmapper = new JMapper<>(EmployeeBean.class, WxCpUser.class);
        Map<String, EmployeeBean> allUser = new HashMap<>();

        // 获取部门
        List<WxCpDepart> deptWxList = null;
        try {
            deptWxList = cpService.getDepartmentService().list(null);
        } catch (WxErrorException e)
        {
            logger.error("同步获取部门列表出错",e);
        }
        // 循环部门 获取用户信息
        deptWxList.forEach(dept -> {
            if (dept.getId()!=null) {
                Integer departId = dept.getId();
                try {
                    List<WxCpUser> userList = cpService.getUserService().listByDepartment(departId, true, 0);
                    // 处理用户信息 转换为employeebean
                    userList.stream().forEach(user -> {
                        EmployeeBean bean = new EmployeeBean();
                        String userId = user.getUserId();
                        EmployeeBean existBean = allUser.get(userId);
                        bean = convertWxCpUserToEmp(user, bean, jmapper, existBean);
                        allUser.put(userId, bean);
                    });
                } catch (WxErrorException e) {
                    logger.error("获取部门员工出错",e);
                    e.printStackTrace();
                }
            }
        });

        Integer count = allUser.size();
        if (allUser.size() > 0) {
            List<String> allUserIdList = new ArrayList<String>(allUser.keySet());
            List<String> existUserIdList = empMapper.getExistUserId(allUserIdList);
            List<EmployeeBean> insertUserList = new ArrayList<>();
            List<EmployeeBean> updatetUserList = new ArrayList<>();
            List<EmployeeBean> errorUserList = new ArrayList<>();
            existUserIdList.forEach(existUserId -> {
                EmployeeBean empBean = allUser.get(existUserId);
                if (null != empBean) {
                    updatetUserList.add(empBean);
                } else {
                    errorUserList.add(empBean);
                }
                allUser.remove(existUserId);
            });
            insertUserList.addAll(allUser.values());
            if (insertUserList.size() > 0) {
                empMapper.insertBatch(insertUserList);
            }
            if (updatetUserList.size() > 0) {
                empMapper.updateBatch(updatetUserList);
            }
        }
        return count;
    }

    /**
     * 获取部门id
     *
     * @param deptMap
     * @param deptId
     * @return
     * 
     * @author lidoudou
     * @date 2017年9月30日 上午11:22:16
     */
    public DeptBean getDeptIds(Map<Integer, DeptBean> deptMap, Integer deptId) {
        DeptBean dept = deptMap.get(deptId);
        if (null != dept) {
            Integer parentId = dept.getParentId();
            if (parentId.equals(0) || parentId.equals(1)) {
                return dept;
            } else {
                if (null != getDeptIds(deptMap, parentId)) {
                    return dept;
                }
            }
        }
        return null;
    }

    /**
     * 转换WxCpUser对象为EmployeeBean
     *
     * @param user
     * @param bean
     * @param jmapper
     *
     * @author lidoudou
     * @date 2017年9月12日 下午4:20:28
     */
    public EmployeeBean convertWxCpUserToEmp(WxCpUser user, EmployeeBean bean, JMapper<EmployeeBean, WxCpUser> jmapper, EmployeeBean existBean) {
        bean = jmapper.getDestination(user);
        bean.setDeptId(StringUtils.join(user.getDepartIds(), ","));
        if(user.getGender()!=null){
            bean.setSex(Integer.parseInt(user.getGender().getCode()));
        }else{
            bean.setSex(0);
        }

        Integer status = user.getStatus();
        bean.setStatus(0);
        if (status == 1) {
            bean.setStatus(1);
        }
        if (null != existBean) {
            String mobile = existBean.getMobile();
            if (StringUtils.isNotBlank(mobile)) {
                bean.setMobile(mobile);
            }
            String email = existBean.getEmail();
            if (StringUtils.isNotBlank(email)) {
                bean.setEmail(email);
            }
        }
        try {
            String name = user.getName();
            String trueNamePinyin = PinyinHelper.convertToPinyinString(name, "", PinyinFormat.WITHOUT_TONE);
            String sortNamePinYin = PinyinHelper.getShortPinyin(name);
            bean.setTureNamePinyin(trueNamePinyin);
            bean.setPinyinAbbreviation(sortNamePinYin);
        } catch (PinyinException e) {
            logger.error("获取用户信息出错2：用户编号为：" + user.getUserId() + "，异常信息为：" + e.getMessage());
        }
        return bean;
    }
    
    /**
     * 通过corpId 同步部门信息
     *
     * @author lidoudou
     * @date 2017年8月23日 下午5:32:02
     */
    @Transactional
    public Integer syncDept() {

        Map<Integer, DeptBean> allDeptInfo = new HashMap<>();
        // 获取部门
        JMapper<DeptBean, WxCpDepart> jmapper = new JMapper<>(DeptBean.class, WxCpDepart.class);
            // 循环部门 获取部门信息
        try {
            List<WxCpDepart> deptList = cpService.getDepartmentService().list(null);
            deptList.forEach(depart -> {
                // 处理部门信息 转换为deptbean
                DeptBean bean = new DeptBean();
                Integer id = depart.getId();
                bean = jmapper.getDestination(depart);
                allDeptInfo.put(id, bean);
            });
        } catch (WxErrorException e) {
            logger.error("获取部门出错，异常信息为：" + e.getMessage());
        }
        allDeptInfo.values().forEach(dept -> {
            Integer parentId = dept.getParentId();
            if (!parentId.equals(0)) {
                if (null == getDeptIds(allDeptInfo, parentId)) {
                    dept.setParentId(1);
                }
            }
        });


        Integer count = allDeptInfo.size();
        if (allDeptInfo.size() > 0) {
            List<Integer> allDeptIdList = new ArrayList<Integer>(allDeptInfo.keySet());
            List<Integer> existDeptIdList = deptMapper.getExistDeptId(allDeptIdList);
            List<DeptBean> insertDeptList = new ArrayList<>();
            List<DeptBean> updatetDeptList = new ArrayList<>();
            List<DeptBean> errorDeptList = new ArrayList<>();
            existDeptIdList.forEach(existDeptId -> {
                DeptBean deptBean = allDeptInfo.get(existDeptId);
                if (null != deptBean) {
                    updatetDeptList.add(deptBean);
                } else {
                    errorDeptList.add(deptBean);
                }
                allDeptInfo.remove(existDeptId);
            });
            insertDeptList.addAll(allDeptInfo.values());
            if (insertDeptList.size() > 0) {
                deptMapper.insertBatch(insertDeptList);
            }
            if (updatetDeptList.size() > 0) {
                deptMapper.updateBatch(updatetDeptList);
            }
        }
        return count;
    }

}
