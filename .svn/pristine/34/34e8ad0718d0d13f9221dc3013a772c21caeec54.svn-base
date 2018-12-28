package com.lyarc.tp.corp.contacts.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.contacts.dto.ContactDTO;
import com.lyarc.tp.corp.contacts.form.ContactsForm;
import com.lyarc.tp.corp.contacts.service.ContactsService;
import com.lyarc.tp.corp.login.form.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * 
 * 获取人员信息（基础接口)
 * 
 * @version 1.0
 * @since JDK1.8
 * @author zhangjingtao
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月23日 上午10:44:31
 */
@Controller
@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ContactsController {

	@Autowired
	private ContactsService contactsService;

	/**
	 * 
	 * 方法描述 获取人员信息
	 * 
	 * @param form 封装查询人员条件 
	 * @return
	 * 
	 * @author zhangjingtao
	 * @date 2017年8月23日 上午11:02:23
	 */
	@RequestMapping(value = "/api/contacts/getBaseInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo<Set<ContactDTO>> getBaseInfo(@RequestBody ContactsForm form) {
        LoginUser loginUser = UserHolder.getUser();
		String empIds = form.getEmpIds();
		List<String> empList = null;
		if (StringUtils.isNoneBlank(empIds)) {
			String[] strs = empIds.split(",");
			empList = Arrays.asList(strs);
		}
		Set<ContactDTO> result = new HashSet<>();
		if (empList != null && !empList.isEmpty()) {
			result = contactsService.getBaseInfoByEmpId(empList);
		}
		String deptIds = form.getDeptIds();
		List<String> deptList = null;
		if (StringUtils.isNoneBlank(deptIds)) {
			String[] strs = deptIds.split(",");
			deptList = Arrays.asList(strs);
		}
		if (deptList != null && !deptList.isEmpty()) {
			for (String deptId : deptList) {
				Set<ContactDTO> list1 = contactsService.getBaseInfoByDeptId(deptId);
				if (result == null) {
					result = new LinkedHashSet<>();
					result.addAll(list1);
				}
				result.addAll(list1);
			}
		}
		return ResultVo.success(result);
	}

}
