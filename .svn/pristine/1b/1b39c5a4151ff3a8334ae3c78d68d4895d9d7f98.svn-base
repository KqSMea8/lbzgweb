package com.lyarc.tp.corp.contacts.controller;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.contacts.bean.DeptNode;
import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.bean.QueryEmpBean;
import com.lyarc.tp.corp.contacts.dto.EmpDTO;
import com.lyarc.tp.corp.contacts.form.EmpForm;
import com.lyarc.tp.corp.contacts.service.EmpService;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.user.bean.User;
import com.lyarc.tp.corp.user.bean.UserRole;
import com.lyarc.tp.corp.user.service.UserRoleService;
import com.lyarc.tp.corp.user.service.UserService;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.util.LunarCalendar;
import me.chanjar.weixin.cp.bean.Gender;
import me.chanjar.weixin.cp.bean.WxCpUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 员工
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月15日 10:51:48
 */
@Controller
@RequestMapping(path = "/api/contacts/emp")
@ResponseBody
public class EmpController {

    @Autowired
    private EmpService empService;
//    @Autowired
//    private AttachFileService attachFileService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    
    private static Logger logger = LoggerFactory.getLogger(EmpController.class);

    /**
     * 方法描述 查询员工列表
     * 
     * @param queryEmpBean 查询参数
     * @return ResultVo<List<EmpDTO>>
     *
     * @author huanghaoqi
     * @date 2017年08月15日 12:04:26
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<PageResultBean> list(@RequestBody QueryEmpBean queryEmpBean) {
        Integer page = queryEmpBean.getPage();
        Integer pageSize = queryEmpBean.getPageSize();
        if (page != null && pageSize != null) {
            queryEmpBean.setOffset((page - 1) * pageSize);
        }
        if (queryEmpBean.getStatus() != null) {
            if (-1 == queryEmpBean.getStatus()) {
                queryEmpBean.setStatus(null);
            }
        }
        queryEmpBean.setDistribution(true);
        PageResultBean pageResultBean = empService.list(queryEmpBean);
        return ResultVo.success(pageResultBean);
    }


    /**
     * 方法描述 未分配部门员工
     *
     * @param queryEmpBean 查询参数
     * @return ResultVo<List<EmpDTO>>
     *
     * @author huanghaoqi
     * @date 2017年08月15日 12:04:26
     */
    @RequestMapping(value = "/undistributed",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<PageResultBean> listUndistributed(@RequestBody QueryEmpBean queryEmpBean) {
        Integer page = queryEmpBean.getPage();
        Integer pageSize = queryEmpBean.getPageSize();
        if (page != null && pageSize != null) {
            queryEmpBean.setOffset((page - 1) * pageSize);
        }
        if (queryEmpBean.getStatus() != null) {
            if (-1 == queryEmpBean.getStatus()) {
                queryEmpBean.setStatus(null);
            }
        }
        queryEmpBean.setDeptId(1);
        queryEmpBean.setDistribution(false);
        PageResultBean pageResultBean = empService.list(queryEmpBean);
        return ResultVo.success(pageResultBean);
    }


    /**
     * 方法描述 添加员工
     * 
     * @param empForm 表单对象
     * @return ResultVo
     *
     * @author huanghaoqi
     * @date 2017年08月15日 14:28:39
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public ResultVo add(@RequestBody EmpForm empForm) {
        int count = empService.getExist(empForm.getEmpId());
        if(count > 0){
            return ResultVo.failure(10121, "账号已存在");
        }
        EmployeeBean emp = empService.getByMobile(empForm.getMobile());
        if(null != emp){
            return ResultVo.failure(10122, "手机号已存在");
        }
        if(null != empForm.getEmail() && empForm.getEmail() != ""){
            EmployeeBean emp2 = empService.getByEmail(empForm.getEmail());
            if(null != emp2){
                return ResultVo.failure(10123, "邮箱已存在");
            }
        }
        EmployeeBean employeeBean = JMapperUtils.getDestination(empForm, EmployeeBean.class, EmpForm.class);
//        Integer[] deptIds = empForm.getDeptId();
//        List<String> deptIdList = new ArrayList<>(deptIds.length);
//        Arrays.asList(deptIds).forEach(p -> deptIdList.add(String.valueOf(p)));
//        employeeBean.setDeptId(String.join(",", deptIdList));
        // 获取姓名的拼音
        String trueName = empForm.getTrueName();
        try {
            // 获取姓名的拼音
            String trueNamePinyin = PinyinHelper.convertToPinyinString(trueName, "", PinyinFormat.WITHOUT_TONE);
            employeeBean.setTureNamePinyin(trueNamePinyin);
            // 拼音首字符的简写
            String pinyinAbbreviation = PinyinHelper.getShortPinyin(trueName);
            employeeBean.setPinyinAbbreviation(pinyinAbbreviation);
        } catch (PinyinException e) {
            logger.error("姓名转换拼音出错,异常信息为：" + e.getMessage());
        }
        // 获取农历生日
        if (empForm.getBirthSolar() != null) {
            LocalDate birthLunar = LunarCalendar.solarToLunar(empForm.getBirthSolar());
            employeeBean.setBirthLunar(birthLunar);
        }
        //添加到腾云
        empService.insert(employeeBean);
        //同步到微信
//        WxCpUser wxCpUser = JMapperUtils.getDestination(employeeBean, WxCpUser.class, EmployeeBean.class);
        WxCpUser wxCpUser =getWxCpUser(employeeBean);
        Integer[] deptIds = changeDeptIds(employeeBean.getDeptId());
        wxCpUser.setDepartIds(deptIds);
        if(employeeBean.getSex() == 1){
            wxCpUser.setGender(Gender.MALE);
         }
         else if(employeeBean.getSex() == 2){
             wxCpUser.setGender(Gender.FEMALE);
         }else{
            wxCpUser.setGender(Gender.MALE);
        }
        empService.wxSyncEmp(wxCpUser);
        
        return ResultVo.success();
    }
    private Integer[] changeDeptIds(String deptIdStr){
        String[] split = deptIdStr.split(",");
        List<Integer> deptIdList = new ArrayList<>(split.length);
        Arrays.asList(split).forEach(p -> deptIdList.add(Integer.parseInt(p)));
        Integer[] deptIds = (Integer[])deptIdList.toArray(new Integer[deptIdList.size()]);
        return deptIds;
    }

    private WxCpUser getWxCpUser(EmployeeBean employeeBean){
        WxCpUser wxCpUser = new WxCpUser();
        wxCpUser.setUserId(employeeBean.getEmpId());
        wxCpUser.setName(employeeBean.getTrueName());
        wxCpUser.setMobile(employeeBean.getMobile());
        wxCpUser.setEmail(employeeBean.getEmail());
        wxCpUser.setPosition(employeeBean.getDuty());
        return wxCpUser;
    }
    
    /**
     * 
     * 为入职员工设置账号(可修改)
     *
     * @param trueName
     * @return
     * 
     * @author jianghan
     * @date 2018年3月15日 下午1:51:25
     */
    @RequestMapping(value = "/setEmpId",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<String> setEmpId(@RequestParam("trueName") String trueName) {
        String empId = "";
        try {
            empId = PinyinHelper.convertToPinyinString(trueName, "", PinyinFormat.WITHOUT_TONE);
        } catch (PinyinException e) {
            logger.error("姓名转换拼音出错,异常信息为：" + e.getMessage());
        }
        //查询重复账号
        List<EmployeeBean> records = empService.getListByEmpId(empId);
        if (records.size() > 0) {
            EmployeeBean emp = records.get(0);
            String lastNumer = "1";
            String[] temps = emp.getEmpId().split("_");
            if (temps.length > 1) {
                lastNumer = String.valueOf((Integer.valueOf(temps[1]) + 1));
            }
            empId += "_" + lastNumer;
        }
        return ResultVo.success(empId);
    }

    /**
     * 方法描述 删除员工
     * 
     * @param empIds 员工编号数组
     * @return ResultVo
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:13:21
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public ResultVo delete(@RequestParam("empIds") String[] empIds) {
        LoginUser loginUser = UserHolder.getUser();
        if(empIds.length == 1){
           User user = userService.getByEmpId(StringUtils.join(empIds));
            UserRole userRole = userRoleService.selectUserRole(loginUser.getUserId(), 1);
            if(null != user && userRole != null){
               return ResultVo.failure(10108, "不能删除超级管理员");
           }
            if(loginUser.getEmpId().equals(StringUtils.join(empIds))){
                return ResultVo.failure(10109, "不能删除当前登录人员");
            }
        }            
        empService.delete(empIds);
        
        String userIds = StringUtils.join(empIds,",");
        empService.wxDeleteEmp(userIds);
        
        return ResultVo.success();
    }

    /**
     * 方法描述 更新员工信息
     * 
     * @param empForm 员工表单对象
     * @return ResultVo
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:27:32
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public ResultVo update(@RequestBody EmpForm empForm) {

        EmployeeBean emp = empService.getByMobile( empForm.getMobile());
        if(null != emp && !emp.getEmpId().equals(empForm.getEmpId())){
            return ResultVo.failure(10122, "手机号已存在");
        }
        if(null != empForm.getEmail() && empForm.getEmail() != ""){
            EmployeeBean emp2 = empService.getByEmail(empForm.getEmail());
            if(null != emp2 && !emp2.getEmpId().equals(empForm.getEmpId())){
                return ResultVo.failure(10123, "邮箱已存在");
            }
        }
        
        EmployeeBean employeeBean = JMapperUtils.getDestination(empForm, EmployeeBean.class, EmpForm.class);
        String trueName = empForm.getTrueName();
        try {
            // 获取姓名的拼音
            String trueNamePinyin = PinyinHelper.convertToPinyinString(trueName, "", PinyinFormat.WITHOUT_TONE);
            employeeBean.setTureNamePinyin(trueNamePinyin);
            // 拼音的简写
            String pinyinAbbreviation = PinyinHelper.getShortPinyin(trueName);
            employeeBean.setPinyinAbbreviation(pinyinAbbreviation);
        } catch (PinyinException e) {
            logger.error("姓名转换拼音出错,异常信息为：" + e.getMessage());
        }
        // 获取农历生日
        if (empForm.getBirthSolar() != null) {
            LocalDate birthLunar = LunarCalendar.solarToLunar(empForm.getBirthSolar());
            employeeBean.setBirthLunar(birthLunar);
        }
        employeeBean.setLastUpdate(LocalDateTime.now());
        empService.update(employeeBean);
       
//        WxCpUser wxCpUser = JMapperUtils.getDestination(employeeBean, WxCpUser.class, EmployeeBean.class);
        WxCpUser wxCpUser =getWxCpUser(employeeBean);
        Integer[] deptIds = changeDeptIds(employeeBean.getDeptId());
        wxCpUser.setDepartIds(deptIds);
        if(employeeBean.getSex() == 1){
           wxCpUser.setGender(Gender.MALE);
        }
        else if(employeeBean.getSex() == 2){
            wxCpUser.setGender(Gender.FEMALE);
        }
        else{
            wxCpUser.setGender(Gender.MALE);
        }
        empService.wxSyncEmp(wxCpUser);
        
        return ResultVo.success(null);
    }

    /**
     * 方法描述 更新员工所在部门
     * 
     * @param deptIds 部门编号数组
     * @param empIds 员工编号数组
     * @return ResultVo
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:41:33
     */
    @RequestMapping(value = "/setDept",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo updateDept(String[] empIds, Integer[] deptIds) {
        String deptId = StringUtils.join(deptIds,",");
        empService.updateDept(empIds, deptId);
        //同步更新到微信
        List<String> empIdList = Arrays.asList(empIds);
        empIdList.forEach(empId ->{
            WxCpUser wxCpUser = new WxCpUser();
            wxCpUser.setDepartIds(deptIds);
            wxCpUser.setUserId(empId);
            empService.wxEditEmp(wxCpUser);
        });
        
        return ResultVo.success();
    }

    /**
     * 方法描述 设置员工离职状态
     *
     * @param empIds 员工编号数组
     * @param dimissionStatus 离职状态
     * @return ResultVo
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:41:33
     */
    @RequestMapping(value = "/dimissed",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo dimissed(String[] empIds, Integer dimissionStatus) {
        empService.updateDimissed(empIds, dimissionStatus);
        return ResultVo.success();
    }

    /**
     * 方法描述 获取员工详情
     * 
     * @param empId 员工编号
     * @return ResultVo<EmpDTO>
     *
     * @author huanghaoqi
     * @date 2017年08月15日 17:43:29
     */
    @RequestMapping(value = "/get",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<EmpDTO> get(String empId,Integer id) {
        EmpDTO empDto =  empService.get(empId,id);;
        return ResultVo.success(empDto);
    }
    
    
    /**
     * 
     * 导入用户信息
     *
     * @param response
     * @param request
     * @param fileId 文件ID
     * @param module 模块
     * @param installed 是否安装套件
     * @return
     * 
     * @author jianghan
     * @date 2017年9月19日 下午1:37:19
     */
    @RequestMapping(value = "/import",method = RequestMethod.POST)
    @Transactional
    public ResultVo importEmp(HttpServletResponse response, HttpServletRequest request, @RequestParam("fileId") String fileId,
                              @RequestParam("module") String module, @RequestParam("installed") Boolean installed){
//        String corpId = UserHolder.getUser().getCorpId();
//        AttachFileBean attachFileBean = attachFileService.getDetail(fileId);
//        attachFileBean.setModule(module);
//        attachFileService.update(attachFileBean,fileId.split(","));
//
//        empService.importEmp(response, request, corpId, attachFileBean,installed);
        return ResultVo.success();
    }
    /**
     * 
     * 导出用户信息
     *
     * @param deptIds
     * @return
     * 
     * @author jianghan
     * @date 2017年9月12日 上午10:31:03
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ResultVo exportEmp(HttpServletResponse response, HttpServletRequest request, @RequestParam("deptIds") Integer[] deptIds){
        empService.export(response,request,deptIds);
        return ResultVo.success();
    }
    /**
     * 
     *  查询企业人数，关注人数
     *
     * @param queryEmpBean
     * @return
     * 
     * @author jianghan
     * @date 2017年9月26日 上午11:02:02
     */
    @RequestMapping(value = "/count",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Long> count(@RequestBody QueryEmpBean queryEmpBean){
        queryEmpBean.setDistribution(true);
        Long count  = empService.count(queryEmpBean);
        return ResultVo.success(count);
    }
    /**
     * 
     * 选人组件-查询多个部门的员工
     * 构建部门员工数据
     * @return
     * 
     * @author jianghan
     * @date 2017年10月10日 下午3:10:53
     */
    @RequestMapping(value = "/selectEmp",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<EmpDTO>> build(@RequestParam("deptIds") Integer[] deptIds){
        List<EmpDTO> list =empService.build(deptIds);
        return ResultVo.success(list);
    }
    /**
     * 
     * 选人组件-查询组织架构数据
     * 部门树组件
     * @return
     * 
     * @author jianghan
     * @date 2017年10月12日 上午9:26:01
     */
    @RequestMapping(value = "/buildContacts",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<DeptNode> buildContacts(){
        DeptNode node = empService.buildContacts();
        return ResultVo.success(node);
    }
}
