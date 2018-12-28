package com.lyarc.tp.corp.workflow.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.service.EmpService;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.workflow.dao.WorkflowReportQueryMapper;
import com.lyarc.tp.corp.workflowact.bean.ProcessFormQueryBean;
import com.lyarc.tp.corp.workflowact.constant.WorkFlowConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.workflow.bean.WorkflowReport;
import com.lyarc.tp.corp.workflow.dao.WorkflowReportMapper;
import com.lyarc.tp.corp.workflow.dto.WorkflowReportDTO;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkflowReportService {

    ThreadLocal<Map<String,String>> t1 = new ThreadLocal<>();

    @Autowired
    private WorkflowReportMapper mapper;

    @Autowired
    private EmpService empService;

    @Autowired
    WorkflowReportQueryMapper reportQueryMapper;
    
    public Integer delete(Integer id){
        return mapper.deleteByPrimaryKey(id);
    }

    public Integer insert(WorkflowReport report){
        mapper.insertSelective(report);
        return report.getId();
    }

    public WorkflowReport get(Integer id,String corpId){
        WorkflowReport report = mapper.selectByPrimaryKey(id);
        String empIds = report.getAuthEmpIds();
        if(null != empIds && !empIds.isEmpty()){
            String[] empIdArr = StringUtils.split(empIds,",");
            List<EmployeeBean> empList = empService.getEmpByEmpIdArray(empIdArr);
            report.setEmpList(empList);
        }                     
        
        return report;
    }
    public Integer update(WorkflowReport report){
        return mapper.updateByPrimaryKeySelective(report);
    }

    public List<WorkflowReport> list(Integer formId){
        return mapper.list(formId);
    }
    
    public List<WorkflowReportDTO> query(String empId){
        return mapper.query(empId);
    }
    



    public WorkflowReport getReportById(Integer id,String corpId){
        return mapper.selectReportById(id,corpId);
    }


    /**
     *
     * @description 流程报表查询（form）
     * @param
     * @return
     * @author wangting
     * @date 2018/6/6 0006 14:12
     *
     */
    @Transactional
    public Map<String,Object> getReportFormData(ProcessFormQueryBean queryBean) throws Exception{
        Integer reportId = queryBean.getReportId();
        if(reportId == null){
            throw new CorpException(ResultVo.failure("报表id为空"));
        }
        LoginUser user = UserHolder.getUser();
        String empId = user.getEmpId();
        String corpId = user.getCorpId();
        WorkflowReport workflowReport = getReportById(reportId,corpId);
        if(workflowReport == null){
            throw new CorpException(ResultVo.failure("无报表定义"));
        }

        String empIds = workflowReport.getAuthEmpIds();
        if(StringUtils.isNotEmpty(empIds)){
            String[] split = empIds.split(",");
            List<String> empIdList = Arrays.asList(split);
            if(!empIdList.contains(empId)){
                throw new CorpException(ResultVo.failure("无权查看该报表"));
            }
        }

        Integer formId = workflowReport.getFormId();
        //根据formId 获取
        String processKey = WorkFlowConst.TY_PROCESS_KEY + "_" + formId;


        String datas = workflowReport.getDatas();
        if(StringUtils.isEmpty(datas)){
            throw new CorpException(ResultVo.failure("无展示的数据定义"));
        }

        String[] split = datas.split(",");
        StringBuffer sb = new StringBuffer("");
        sb.append("select t1.*,hi_inst.START_USER_ID_ startUid,hi_inst.START_TIME_ startTime from (");
        sb.append(" select PROC_INST_ID_ as instId ");
        for (String field : split) {
            sb.append(",max(case NAME_ WHEN '"+field+"' THEN TEXT_ ELSE '' END ) as '"+field+"'");
        }
        sb.append("from (");
        sb.append("select *");

        sb.append("  from ACT_HI_VARINST WHERE PROC_INST_ID_ IN ( select PROC_INST_ID_ from ACT_HI_PROCINST WHERE PROC_DEF_ID_ IN (SELECT ID_ FROM ACT_RE_PROCDEF WHERE KEY_='" + processKey + "' ))");
        sb.append(" AND FIND_IN_SET(NAME_,'" + datas + "')");
        sb.append(") t group by PROC_INST_ID_");
        sb.append(" ) t1 left join ACT_HI_PROCINST hi_inst  on t1.instId = hi_inst.PROC_INST_ID_ ");
        if(StringUtils.isNotEmpty(queryBean.getFieldId()) && StringUtils.isNotEmpty(queryBean.getValue())){
            if("date".equals(queryBean.getFieldType()) || "textarea".equals(queryBean.getFieldType())) {
                sb.append(" where ").append(queryBean.getFieldId()).append("  like '%").append(queryBean.getValue()).append("%'");
            }else{
                sb.append(" where ").append(queryBean.getFieldId()).append(" = '").append(queryBean.getValue()).append("'");
            }
        }

        StringBuffer countStr = new StringBuffer("");
        countStr.append(" select count(1) from (")
                .append(sb.toString())
                .append(" ) t");

        sb.append(" ORDER BY START_TIME_ DESC ");
        if(queryBean.getOffset() != null && queryBean.getPageSize() != null){
            sb.append(" limit ").append(queryBean.getOffset()).append(",").append(queryBean.getPageSize());
        }
        //异步执行sql
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 10, 500, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        Future<List<HashMap>> future = executor.submit(new Callable<List<HashMap>>() {
            @Override
            public List<HashMap> call() throws Exception {
                List<HashMap> list = reportQueryMapper.list(sb.toString());
                return list;
            }
        });

        Future<Long> futureCount = executor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                Long count = reportQueryMapper.count(countStr.toString());
                return count;
            }
        });

        List<HashMap> list = future.get();
        //查询员工真实姓名 用于展示
        getEmpMap(corpId);
        list.forEach(map->{
            String startUid = map.get("startUid").toString();
            String userName = getUserName(startUid);
            map.put("startUserName", userName);
        });

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("data", list);
        Long count = futureCount.get();
        resMap.put("count", count);

        return resMap;
    }

    /**
     *
     * @description 获取员工map(empid,trueName)
     * @param
     * @return
     * @author wangting
     * @date 2018/5/28 0028 17:57
     *
     */
    private void getEmpMap(String corpId){

        List<EmployeeBean> employeeBeans = empService.listEmp();
        Map<String, String> map = employeeBeans.stream().collect(Collectors.toMap(EmployeeBean::getEmpId, a -> a.getTrueName(), (k1, k2) -> k1));
        t1.set(map);

    }

    private String getUserName(String userId){
        String res="";
        if(StringUtils.isNotEmpty(userId)){
            String[] split = userId.split(WorkFlowConst.ID_SPLIT);
            if(split != null && split.length == 2){
                Map<String, String> map = t1.get();
                if(map == null){
                    getEmpMap(split[0]);
                    map = t1.get();
                }
                res = map.get(split[1]);
            }
        }
        return res;
    }

}
