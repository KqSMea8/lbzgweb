package com.lyarc.tp.corp.contacts.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.common.exception.ServiceException;
import com.lyarc.tp.corp.contacts.bean.*;
import com.lyarc.tp.corp.contacts.dao.DeptMapper;
import com.lyarc.tp.corp.contacts.dao.EmpMapper;
import com.lyarc.tp.corp.contacts.dto.EmpDTO;
import com.lyarc.tp.corp.util.ExcelExportUtil;
import com.lyarc.tp.corp.util.JMapperUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.lyarc.tp.corp.util.ExcelExportUtil.Pair;


/**
 * 员工
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月15日 11:56:46
 */
@Service
public class EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private WxCpService wxCpService;

    private static Logger logger = LoggerFactory.getLogger(EmpService.class);
    /**
     * suiteId 通讯录套件ID
     */
    public static final String SUITE_ID_CONSTANT = "tjebfa5de716921682";

    /**
     * 方法描述 获取员工列表
     * 
     * @param queryEmpBean 查询对象
     * @return List<EmpDTO>
     *
     * @author huanghaoqi
     * @date 2017年08月15日 14:40:16
     */
    public PageResultBean list(QueryEmpBean queryEmpBean) {
        List<DeptBean> deptBeans = deptMapper.list();
        List<EmployeeBean> employeeBeans = empMapper.list(queryEmpBean);
        // 构建部门树
        DeptNode rootNode = DeptTree.build(deptBeans, employeeBeans);
        if (rootNode != null) {
            DeptNode deptNode = rootNode.getByDeptId(queryEmpBean.getDeptId());
            if (deptNode != null) {
                List<EmployeeBean> employeeBeanList = deptNode.listEmployeeBeans();
                // 过滤为分配部门人员
                Boolean distribution = queryEmpBean.getDistribution();
                if (distribution != null) {
                    if (!distribution) {
                        employeeBeanList = employeeBeanList.stream().filter(employeeBean -> {
                            String[] deptIds = employeeBean.getDeptId().split(",");
                            return deptIds.length == 1 && Arrays.binarySearch(deptIds, "1") >= 0;
                        }).collect(Collectors.toList());
                    }
                }
                // 按拼音排序
                employeeBeanList.stream().sorted(Comparator.comparing(EmployeeBean::getTureNamePinyin)).collect(Collectors.toList());
                // 员工数量
                Integer count = employeeBeanList.size();
                // 进行分页
                Integer offset = queryEmpBean.getOffset();
                Integer pageSize = queryEmpBean.getPageSize();
                Integer size = employeeBeanList.size();
                if (pageSize != null && offset != null) {
                    if (size < (offset + pageSize)) {
                        employeeBeanList = employeeBeanList.subList(offset, size);
                    } else {
                        employeeBeanList = employeeBeanList.subList(offset, offset + pageSize);
                    }
                }
                // bean转换dto
                List<EmpDTO> empDtos = setEmpProperty(employeeBeanList, deptBeans);
                return PageResultBean.success((long) count, empDtos);
            }
        }
        return PageResultBean.success(0L, new ArrayList<EmpDTO>(0));
    }
    /**
     * 
     * 构建部门员工数据
     *
     * @param
     * @return
     * 
     * @author jianghan
     * @date 2017年10月10日 下午3:11:31
     */
    public List<EmpDTO> build(Integer[] deptIds){
        List<EmployeeBean> employeeBeanList = new ArrayList<EmployeeBean>();
        List<DeptBean> deptBeans = deptMapper.list();
        for (int i = 0; i < deptIds.length; i++) {
            QueryEmpBean queryEmpBean = new QueryEmpBean();
            queryEmpBean.setDimission(0);
            queryEmpBean.setDeptId(deptIds[i]);
            List<EmployeeBean> employeeBeans = empMapper.list(queryEmpBean);
            // 构建部门树
            DeptNode rootNode = DeptTree.build(deptBeans, employeeBeans);
            if (rootNode != null) {
                DeptNode deptNode = rootNode.getByDeptId(queryEmpBean.getDeptId());
                if (deptNode != null) {
                    employeeBeanList.addAll(deptNode.listEmployeeBeans());
                }
            }
        }
        // 多个部门员工去重处理
        List<EmployeeBean> unique = employeeBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(EmployeeBean::getEmpId))), ArrayList::new));
        List<EmpDTO> empDtos = setEmpProperty(unique, deptBeans);
        
        return empDtos;
    }
    /**
     * 
     * 构建联系人组件架构数据
     *
     * @return
     * 
     * @author jianghan
     * @date 2017年10月12日 上午9:27:15
     */
    public DeptNode buildContacts(){
        List<DeptBean> deptBeans = deptMapper.list();
        QueryEmpBean queryEmpBean = new QueryEmpBean();
        queryEmpBean.setDimission(0);
        queryEmpBean.setDeptId(1);
        List<EmployeeBean> employeeBeans = empMapper.list(queryEmpBean);
     // 构建部门树
        DeptNode rootNode = DeptTree.build(deptBeans, employeeBeans);
        DeptNode deptNode = null;
        if (rootNode != null) {
            deptNode = rootNode.getByDeptId(1);
        }
        return deptNode;
    }



    /**
     * 方法描述 查询总数
     * 
     * @param queryEmpBean 查询参数对象
     * @return Long
     *
     * @author huanghaoqi
     * @date 2017年08月17日 12:52:58
     */
    public Long count(QueryEmpBean queryEmpBean) {
        return empMapper.count(queryEmpBean);
    }

    /**
     * 方法描述 添加员工
     * 
     * @param employeeBean 添加对象
     * 
     * @author huanghaoqi
     * @date 2017年08月15日 14:41:35
     */

    public void insert(EmployeeBean employeeBean) {
        empMapper.insert(employeeBean);
    }

    /**
     * 方法描述 删除员工
     * 
     * @param empIds 员工编号数组
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:11:48
     */
    public void delete(String[] empIds) {
        empMapper.delete(empIds);
    }

    /**
     * 方法描述 更新员工信息
     * 
     * @param employeeBean 员工对象
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:26:58
     */
    public void update(EmployeeBean employeeBean) {
        empMapper.update(employeeBean);
    }


    /**
     * 方法描述 更新员工所在部门
     * 
     * @param empIds 员工编号数组
     * @param deptId 部门编号数组
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:39:49
     */
    public void updateDept(String[] empIds, String deptId) {
        empMapper.updateDept(empIds, deptId);
    }

    /**
     * 方法描述 更新员工离职状态
     *
     * @param empIds 员工编号数组
     * @param dimissedStatus 离职状态
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:39:49
     */
    public void updateDimissed(String[] empIds, Integer dimissedStatus) {
        empMapper.updateDimissed(empIds, dimissedStatus);
    }

    /**
     * 方法描述 获取员工详情
     * 
     * @param empId 员工编号
     * @return EmpDTO
     *
     * @author huanghaoqi
     * @date 2017年08月15日 17:41:50
     */
    public EmpDTO get(String empId,Integer id) {
        EmployeeBean employeeBean = new EmployeeBean();
        if(StringUtils.isEmpty(empId) && id== null){
            throw new ServiceException(ResultVo.failure("请求参数不完整"));
        }
        if(StringUtils.isNotEmpty(empId)){
            employeeBean = empMapper.get(empId);
        }else if(id != null){
            employeeBean = empMapper.getById(id);
        }

        EmpDTO empDto = JMapperUtils.getDestination(employeeBean, EmpDTO.class, EmployeeBean.class);
        List<DeptBean> deptBeans = deptMapper.list();
        // 设置部门名称
        List<String> deptNameList = new ArrayList<>(5);
        String deptId = empDto.getDeptId();
        if (deptId != null && !"".equals(deptId)) {
            int[] deptIds = Arrays.stream(deptId.split(",")).mapToInt(Integer::parseInt).toArray();
            deptBeans.forEach(deptBean -> {
                int isContain = Arrays.binarySearch(deptIds, deptBean.getDeptId());
                if (isContain >= 0) {
                    deptNameList.add(deptBean.getDeptName());
                }
            });
            empDto.setDeptName(String.join(",", deptNameList.toArray(new String[deptNameList.size()])));
        }
        // 设置性别名称
        Integer sex = empDto.getSex();
        String sexName = SexEnum.statusOf(sex).getTypeName();
        empDto.setSexName(sexName);
        return empDto;
    }

    /**
     * 
     * 判断账户是否已存在
     *
     * @param empId
     * @return
     * 
     * @author jianghan
     * @date 2017年9月12日 上午10:33:18
     */
    public Integer getExist(String empId) {
        return empMapper.getExist(empId);
    }

    /**
     * 方法描述 查询公司下的员工
     * 
     * @return List<EmployeeBean>
     *
     * @author huanghaoqi
     * @date 2017年09月27日 17:52:53
     */
    public List<EmployeeBean> listEmp() {
        return empMapper.getEmpByEmpIdArray(null);
    }

    /**
     * 
     * 导入员工信息
     *
     * @param response
     * @param request
     * @param attachFileBean
     * 
     * @author jianghan
     * @date 2017年9月14日 上午9:43:59
     */
//    @Transactional
//    public void importEmp(HttpServletResponse response, HttpServletRequest request, String corpId, AttachFileBean attachFileBean, Boolean installed) {
//        try {
//            File file = new File(attachFileBean.getFilePath());
//            InputStream stream = file.toURL().openStream();
//            boolean isXls = attachFileBean.getFileNameExt().equals("xls");
//            boolean isXlsx = attachFileBean.getFileNameExt().equals("xlsx");
//            if (!isXls && !isXlsx) {
//                throw new ServiceException(ResultVo.failure("请选择上传Excel文件"));
//            }
//            // 1、读取工作簿
//            Workbook workbook = isXlsx ? new XSSFWorkbook(stream) : new HSSFWorkbook(stream);
//            // 2、读取工作表
//            Sheet sheet = workbook.getSheetAt(0);
//            // 3、读取行
//            if (sheet.getPhysicalNumberOfRows() > 1) {
//                EmployeeBean empbean = null;
//                for (int k = 1; k < sheet.getPhysicalNumberOfRows(); k++) {
//                    // 4、读取单元格
//                    Row row = sheet.getRow(k);
//                    empbean = new EmployeeBean();
//                    // 基本信息
//                    // 姓名
//                    Cell cell0 = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
//                    if ("".equals(cellFormat(cell0))) {
//                        continue;
//                    }
//                    empbean.setTrueName(cellFormat(cell0));
//                    // 账号
//                    Cell cell1 = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
//                    if ("".equals(cellFormat(cell1))) {
//                        continue;
//                    }
//                    empbean.setEmpId(cellFormat(cell1));
//                    // 所在部门
//                    Cell cell2 = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
//                    List<Integer> deptId = getDept(cellFormat(cell2), corpId);
//                    if(deptId!=null){
//                       empbean.setDeptId(StringUtils.join(deptId, ","));
//                    }else{
//                        empbean.setDeptId("1");
//                    }
//                    // 递归查询部门方法
//                    // 性别
//                    Cell cell3 = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
//                    String genderStr = cellFormat(cell3);
//                    empbean.setSex(genderStr.equals("男") ? 1 : 2);
//                    // 职位
//                    Cell cell4 = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
//                    empbean.setDuty(cellFormat(cell4));
//                    // 手机号
//                    Cell cell5 = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
//                    // 手机号需做验证
//                    empbean.setMobile(cellFormat(cell5));
//                    // 邮箱
//                    Cell cell6 = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
//                    empbean.setEmail(cellFormat(cell6));
//                    // 入职日期
//                    Cell cell7 = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
//                    String join = cellFormat(cell7);
//                    LocalDate joinDate = null;
//                    try {
//                        joinDate = LocalDate.parse(join);
//                        empbean.setJoinDate(joinDate);
//                    } catch (Exception e) {
//                        empbean.setJoinDate(joinDate);
//                    }
//                    // 生日
//                    Cell cell8 = row.getCell(8, Row.CREATE_NULL_AS_BLANK);
//                    String birth = cellFormat(cell8);
//                    LocalDate birthSolar = null;
//                    try {
//                        birthSolar = LocalDate.parse(birth);
//                        empbean.setBirthSolar(birthSolar);
//                    } catch (Exception e) {
//                        empbean.setBirthSolar(birthSolar);
//                    }
//                    // 是否关注企业 可忽略？从微信同步的信息
//                   /* Cell cell9 = row.getCell(9, Row.CREATE_NULL_AS_BLANK);
//                    String statusName = cellFormat(cell9);*/
//                    empbean.setStatus(0);
//                    // 所属企业
//                    empbean.setCorpId(corpId);
//                    // 获取姓名的拼音
//                    String trueName = empbean.getTrueName();
//                    // 获取姓名的拼音
//                    String trueNamePinyin = PinyinHelper.convertToPinyinString(trueName, "", PinyinFormat.WITHOUT_TONE);
//                    empbean.setTureNamePinyin(trueNamePinyin);
//                    // 拼音首字符的简写
//                    String pinyinAbbreviation = PinyinHelper.getShortPinyin(trueName);
//                    empbean.setPinyinAbbreviation(pinyinAbbreviation);
//                    // 获取农历生日
//                    if (empbean.getBirthSolar() != null) {
//                        LocalDate birthLunar = LunarCalendar.solarToLunar(empbean.getBirthSolar());
//                        empbean.setBirthLunar(birthLunar);
//                    }
//                    // 生日提醒
//                    empbean.setBirthReminder(0);
//                    // 保存用户
//                    saveEmp(empbean, installed, deptId);
//                }
//                // 将导入结果存入数据库
//            }
//            workbook.close();
//            stream.close();
//        } catch (Exception e) {
//            logger.error("导入用户信息出错,异常信息为：" + e.getMessage());
//            throw new ServiceException(ResultVo.failure("导入用户信息失败"));
//        }
//    }

    /**
     * 
     * 获取部门Ids
     *
     * @param depts
     * @return
     * 
     * @author jianghan
     * @date 2017年9月15日 上午10:49:39
     */
    private List<Integer> getDept(String depts) {
        List<Integer> deptId = new ArrayList<>();
        // 为空时，添加到顶级部门
        if ("".equals(depts)) {
            deptId.add(1);
            return deptId;
        }
        List<DeptBean> deptBeans = deptMapper.list();
        DeptNode rootNode = DeptTree.build(deptBeans, new ArrayList<EmployeeBean>());

        List<String> deptList = Arrays.asList(depts.split("；"));
        deptList.forEach(dept -> {
            List<String> nameList = Arrays.asList(dept.split("/"));
            DeptNode deptNode = getDeptIds(rootNode, nameList, nameList.get(0), nameList.size());
            deptId.add(deptNode.getDept().getDeptId());
        });
        return deptId;
    }

    /**
     * 
     * 递归查询部门ID fastdev/软件部/技术部/开发组
     * 
     * @param node
     * @param nameList
     * @param deptName
     * @param index 循环index
     * @return
     * 
     * @author jianghan
     * @date 2017年9月16日 上午10:05:22
     */
    private DeptNode getDeptIds(DeptNode node, List<String> nameList, String deptName, int index) {
        if (deptName.equals(node.getDept().getDeptName())) {
            if (index == 1) {
                return node;
            } else {
                for (DeptNode deptNode : node.getChildren()) {
                    DeptNode dept = getDeptIds(deptNode, nameList, nameList.get((nameList.size() - index) + 1), index - 1);
                    if (dept != null) {
                        return dept;
                    }
                }
            }
        }
        return null;
    }


    /**
     * 
     * 导入信息保存
     *
     * @param empbean
     * 
     * @author jianghan
     * @date 2017年9月14日 下午2:02:25
     */
//    @Transactional
//    private void saveEmp(EmployeeBean empbean, Boolean installed, List<Integer> deptId) {
//        Integer count = getExist(empbean.getCorpId(), empbean.getEmpId());
//        // 判断是否安装通讯录套件
//        if (installed) {
//            WxCpUser wxCpUser = JMapperUtils.getDestination(empbean, WxCpUser.class, EmployeeBean.class);
//            WxCpService cpService = SuiteContext.me().getWxCpService(SUITE_ID_CONSTANT, empbean.getCorpId());
//            Integer[] deptIds = deptId.toArray(new Integer[0]);
//            wxCpUser.setDepartIds(deptIds);
//            // 判断账号是否已存在
//            if (count > 0) {
//                update(empbean);
//                try {
//                    cpService.getUserService().update(wxCpUser);
//                } catch (WxErrorException e) {
//                    logger.error("微信同步员工出错,异常信息为：" + e.getMessage());
//                }
//            } else {
//                insert(empbean);
//                try {
//                    cpService.getUserService().create(wxCpUser);
//                } catch (WxErrorException e) {
//                    logger.error("微信同步员工出错,异常信息为：" + e.getMessage());
//                }
//            }
//        } else {
//            if (count > 0) {
//                update(empbean);
//            }
//        }
//    }

    /**
     * 
     * 导入文档格式化
     *
     * @param cell
     * @return
     * 
     * @author jianghan
     * @date 2017年9月14日 上午11:07:35
     */
    private String cellFormat(Cell cell) {
        DecimalFormat df = new DecimalFormat("#");
        if (cell == null){
            return "";
        }
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    return HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
                }
                return df.format(cell.getNumericCellValue());
            case XSSFCell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case XSSFCell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case XSSFCell.CELL_TYPE_BLANK:
                return "";
            case XSSFCell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() + "";
            case XSSFCell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue() + "";
        }
        return "";
    }

    /**
     * 
     * 导出信息
     *
     * @param response
     * @param request
     * @param deptIds
     * 
     * @author jianghan
     * @date 2017年9月13日 上午11:32:27
     */
    public void export(HttpServletResponse response, HttpServletRequest request,Integer[] deptIds) {
        List<EmployeeBean> employeeBeanList = new ArrayList<EmployeeBean>();
        List<DeptBean> deptBeans = deptMapper.list();
        for (int i = 0; i < deptIds.length; i++) {
            QueryEmpBean queryEmpBean = new QueryEmpBean();
            queryEmpBean.setDimission(0);
            queryEmpBean.setDeptId(deptIds[i]);
            List<EmployeeBean> employeeBeans = empMapper.list(queryEmpBean);
            // 构建部门树
            DeptNode rootNode = DeptTree.build(deptBeans, employeeBeans);
            if (rootNode != null) {
                DeptNode deptNode = rootNode.getByDeptId(queryEmpBean.getDeptId());
                if (deptNode != null) {
                    employeeBeanList.addAll(deptNode.listEmployeeBeans());
                }
            }
        }
        // 多个部门员工去重处理
        List<EmployeeBean> unique = employeeBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(EmployeeBean::getEmpId))), ArrayList::new));
        List<EmpDTO> empDtos = setEmpProperty(unique, deptBeans);
        List<ExcelExportUtil.Pair> titles = new ArrayList<ExcelExportUtil.Pair>();
        titles.add(new Pair("trueName", "姓名(必填项)"));
        titles.add(new Pair("empId", "账号(必填项)"));
        titles.add(new Pair("deptPath", "部门(必填项)"));
        titles.add(new Pair("sexName", "性别"));
        titles.add(new Pair("duty", "职位"));
        titles.add(new Pair("mobile", "手机号(必填项)"));
        titles.add(new Pair("email", "邮箱"));
        titles.add(new Pair("joinDate", "入职时间"));
        titles.add(new Pair("birthSolar", "员工生日"));
        titles.add(new Pair("statusName", "是否关注企业"));

        ExcelExportUtil.exportByRecord(response, request, "通讯录员工信息", titles, empDtos);
    }

    /**
     * 方法描述 将bean转为DTO，并设置相应字段属性
     * 
     * @param employeeBeanList 员工集合
     * @param deptBeans 部门集合
     * @return List<EmpDTO>
     *
     * @author huanghaoqi
     * @date 2017年08月31日 10:15:25
     */
    private List<EmpDTO> setEmpProperty(List<EmployeeBean> employeeBeanList, List<DeptBean> deptBeans) {
        Map<String, String> map = employeeBeanList.stream().collect(Collectors.toMap(EmployeeBean::getEmpId, EmployeeBean::getDeptId, (key1, key2) -> key1));
        List<EmpDTO> empDtos = JMapperUtils.getDestinations(employeeBeanList, EmpDTO.class, EmployeeBean.class);
        empDtos.forEach(empDTO -> {
            // 设置部门名称
            List<String> deptNameList = new ArrayList<>(5);
            String empId = empDTO.getEmpId();
            String deptId = map.get(empId);
            if (StringUtils.isNotEmpty(deptId)) {
                int[] deptIds = Arrays.stream(deptId.split(",")).mapToInt(Integer::parseInt).toArray();
                deptBeans.forEach(deptBean -> {
                    int isContain = Arrays.binarySearch(deptIds, deptBean.getDeptId());
                    if (isContain >= 0) {
                        deptNameList.add(deptBean.getDeptName());
                    }
                });
                empDTO.setDeptName(String.join(",", deptNameList.toArray(new String[deptNameList.size()])));
                empDTO.setDeptPath(setDeptList(deptIds, deptBeans));
            }
            // 设置性别名称
            Integer sex = empDTO.getSex();
            String sexName = SexEnum.statusOf(sex).getTypeName();
            empDTO.setSexName(sexName);
            // 设置关注状态
            String statusName = StatusEnum.statusOf(empDTO.getStatus()).getTypeName();
            empDTO.setStatusName(statusName);
        });
        return empDtos;
    }

    /**
     * 
     * 设置导出部门路径
     *
     * @param deptIds
     * @param deptBeans
     * @return
     * 
     * @author jianghan
     * @date 2017年9月16日 下午3:22:45
     */
    private String setDeptList(int[] deptIds, List<DeptBean> deptBeans) {
        List<String> result = new ArrayList<String>();
        DeptNode rootNode = DeptTree.build(deptBeans, new ArrayList<EmployeeBean>());
        for (int i = 0; i < deptIds.length; i++) {
            String path = getDeptPath(deptIds[i], rootNode);
            result.add(path);
        }
        return String.join("；", result);
    }

    /**
     * 
     * 递归获取部门路径
     *
     * @param deptId
     * @param rootNode
     * @return
     * 
     * @author jianghan
     * @date 2017年9月16日 下午3:00:22
     */
    private String getDeptPath(Integer deptId, DeptNode rootNode) {
        // 1.获取当前节点
        DeptNode node = rootNode.getByDeptId(deptId);
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            if (node.getDept().getParentId() != 0) {
                sb.append("/");
            }
            sb.append(node.getDept().getDeptName());
            String name = getDeptPath(node.getDept().getParentId(), rootNode);
            return name.concat(sb.toString());
        } else {
            return "";
        }

    }

    /**
     * 方法描述 根据用户姓名或拼音缩写模糊查询
     *
     * @param likeContent
     * @param deptId
     * @return
     * 
     * @author lidoudou
     * @date 2017年9月27日 下午5:55:53
     */
    public List<EmployeeBean> listByNameOrPinyin(String likeContent, Integer deptId) {
        return empMapper.listByNameOrPinyin(likeContent, deptId);
    }

    /**
     * 通过企业编号以及用户编号获取用户信息
     *
     * @param empId
     * @return
     * 
     * @author lidoudou
     * @date 2017年9月29日 下午5:33:56
     */
    public EmployeeBean getByEmpId(String empId) {
        return empMapper.get(empId);

    }
    
    /**
     * 
     * 根据empId获取有重复的账号的员工
     *
     * @param empId
     * @return
     * 
     * @author jianghan
     * @date 2018年3月15日 上午11:59:53
     */
    public List<EmployeeBean> getListByEmpId(@Param("empId") String empId){
        return empMapper.getListByEmpId( empId);
    }

    /**
     * 通过企业编号以及手机号获取
     *
     * @param mobile
     * @return
     * 
     * @author lidoudou
     * @date 2017年9月29日 下午5:52:36
     */
    public EmployeeBean getByMobile(String mobile) {
        return empMapper.getByMobile(mobile);
    }
    /**
     * 
     * 查询是否存在相同的手机号或邮箱
     *
     * @param email
     * @return
     * 
     * @author jianghan
     * @date 2018年3月20日 下午5:09:32
     */
    public EmployeeBean getByEmail(String email){
        return empMapper.getByEmail(email);
    }
    
    /**
     * 方法描述 根据用户ID数组获取信息
     * 
     * @param empIds 用户ID
     * @return List<EmpDTO>
     *
     * @author huanghaoqi
     * @date 2017年08月24日 16:07:54
     */
    public List<EmployeeBean> getEmpByEmpIdArray(String[] empIds){
        return empMapper.getEmpByEmpIdArray(empIds);
    }


    /**
     *
     * 微信更新员工
     *
     * @param wxCpUser
     *
     * @author jianghan
     * @date 2017年9月19日 下午3:07:30
     */
    public void wxSyncEmp(WxCpUser wxCpUser) {
        WxCpService cpService = wxCpService;
        try {
            WxCpUser wxUser = getWxUser(wxCpUser.getUserId());
            // 判断是否存在微信通讯录中
            if (null != wxUser) {
                cpService.getUserService().update(wxCpUser);
            } else {
                cpService.getUserService().create(wxCpUser);
            }
        } catch (WxErrorException e) {
            logger.error("微信同步员工出错,异常信息为：" + e.getMessage());
            throw new ServiceException(ResultVo.failure("企业微信同步员工失败"));
        }
    }

    public void wxEditEmp(WxCpUser wxCpUser) {
        WxCpService cpService = wxCpService;
        try {
            cpService.getUserService().update(wxCpUser);
        } catch (WxErrorException e) {
            logger.error("微信同步员工出错,异常信息为：" + e.getMessage());
            throw new ServiceException(ResultVo.failure("企业微信同步员工失败"));
        }
    }

    /**
     *
     * 读取wx成员详情
     *
     * @param userId
     * @return
     *
     * @author jianghan
     * @date 2017年9月19日 下午2:56:04
     */
    public WxCpUser getWxUser(String userId) {
        WxCpService cpService = wxCpService;
        try {
            return cpService.getUserService().getById(userId);
        } catch (WxErrorException e) {
            logger.error("微信同步员工出错,异常信息为：" + e.getMessage());
        }
        return null;
    }

    /**
     *
     * 微信同步删除员工
     *
     * @param userIds
     *
     * @author jianghan
     * @date 2017年9月14日 下午1:42:38
     */
    public void wxDeleteEmp(String userIds) {
        WxCpService cpService = wxCpService;
        try {
            WxCpUser wxUser = getWxUser(userIds);
            // 判断是否存在微信通讯录中
            if (null != wxUser) {
                cpService.getUserService().delete(userIds);
            }
        } catch (WxErrorException e) {
            logger.error("微信同步员工出错,异常信息为：" + e.getMessage());
        }
    }
    
    public List<EmployeeBean> getEmpsByDept(Integer deptId) {
    	List<EmployeeBean> employeeBean = empMapper.getEmpsByDept(deptId);
    	return employeeBean;
    }
}
