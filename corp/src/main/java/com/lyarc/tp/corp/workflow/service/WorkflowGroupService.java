package com.lyarc.tp.corp.workflow.service;

import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.dao.EmpMapper;
import com.lyarc.tp.corp.workflow.bean.WorkflowDraft;
import com.lyarc.tp.corp.workflow.bean.WorkflowGroup;
import com.lyarc.tp.corp.workflow.dao.WorkflowDraftMapper;
import com.lyarc.tp.corp.workflow.dao.WorkflowGroupMapper;
import com.lyarc.tp.corp.workflow.dto.WorkflowGroupListDTO;
import com.lyarc.tp.corp.workflow.form.WorkflowDraftParam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @description
 * @param
 * @return
 * @author wangting
 * @date 2018/5/18 0018 17:11
 *
 */
@Service
public class WorkflowGroupService {

	@Autowired
	private WorkflowGroupMapper mapper;

	@Autowired
	private EmpMapper empMapper;

	public Integer insert(WorkflowGroup bean){
		mapper.insertSelective(bean);
		return bean.getId();
	}

	public Integer update(WorkflowGroup bean){
		int row = mapper.updateByPrimaryKeySelective(bean);
		return row;
	}

	public WorkflowGroup get(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	public List<WorkflowGroupListDTO> list(WorkflowGroup param){
		List<WorkflowGroupListDTO> list = mapper.list(param);
		list.forEach(item -> {
			String empIds = item.getEmpIds();
			if(null != empIds && !empIds.isEmpty()){
				String[] empIdArr = StringUtils.split(empIds,",");
				List<EmployeeBean> empList = empMapper.getEmpByEmpIdArray(empIdArr);
				item.setEmpList(empList);
			}
			item.setEmpNames(getEmpIds(param.getCorpId(),item.getEmpIds()));
		}
				);
		return list;
	}

	/**
	 *
	 * 根据ID获取用户信息
	 *
	 * @param corpId
	 * @param empIds
	 * @return
	 *
	 * @author jianghan
	 * @date 2018年5月31日 下午3:41:58
	 */
	private String getEmpIds(String corpId, String empIds) {
		if(null == empIds || empIds.isEmpty()){
			return "";
		}
		String[] empIdArr = StringUtils.split(empIds,",");
		List<EmployeeBean> employeeBeanList = empMapper.getEmpByEmpIdArray(empIdArr);
		List<String> empNames = employeeBeanList.stream().map(EmployeeBean::getTrueName).collect(Collectors.toList());
		return StringUtils.join(empNames,",");
	}

	public int delete(Integer id){
		return mapper.deleteByPrimaryKey(id);
	}
}
