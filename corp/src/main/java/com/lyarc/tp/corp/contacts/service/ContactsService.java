package com.lyarc.tp.corp.contacts.service;

import com.lyarc.tp.corp.contacts.dao.EmpMapper;
import com.lyarc.tp.corp.contacts.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


/**
 * 
 * 查询基本数据
 * 
 * @version 1.0 
 * @since JDK1.8
 * @author zhangjingtao 
 * @company 洛阳艾克科技有限公司 
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved. 
 * @date 2017年8月23日 上午10:59:48
 */
@Service
public class ContactsService {
	@Autowired
	private EmpMapper empMapper;
	/**
	 * 
	 * 依据员工id列表获取 基本信息
	 * 
	 * @param list
	 * @return 
	 * 
	 * @author zhangjingtao 
	 * @date 2017年8月23日 下午12:03:07
	 */
	public Set<ContactDTO> getBaseInfoByEmpId(List<String> list){
		return empMapper.getBaseInfoByEmpId(list);
	}
	/**
	 * 
	 * 依据部门id,获取基本信息
	 * 
	 * @param deptId
	 * @return 
	 * 
	 * @author zhangjingtao 
	 * @date 2017年8月23日 下午12:03:30
	 */
	public Set<ContactDTO> getBaseInfoByDeptId(String deptId){
		return empMapper.getBaseInfoByDeptId(deptId);
	}

}

