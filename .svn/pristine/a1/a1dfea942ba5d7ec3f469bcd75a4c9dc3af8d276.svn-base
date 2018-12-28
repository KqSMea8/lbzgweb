package com.lyarc.tp.corp.user.form;

import com.googlecode.jmapper.annotations.JMap;
import com.lyarc.tp.corp.user.bean.User;

import java.util.Arrays;

/**
 * 
 * 封装编辑与添加参数
 * 
 * @version 1.0 
 * @since JDK1.8 
 * @author zhangjingtao 
 * @company 洛阳艾克科技有限公司 
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved. 
 * @date 2017年9月4日 下午3:42:32
 */

public class UserAddForm {

	@JMap(attributes={"userId"},classes={User.class})
	private Integer userId;
	
	@JMap(attributes={"nickName"},classes={User.class})
	private String nickName;
	
	/**登录账号*/
	@JMap(attributes={"loginName"},classes={User.class})
	private String loginName;
	
	/**员工编号*/
	@JMap(attributes={"empId"},classes={User.class})
	private String empId;
	
	/**密码*/
	@JMap(attributes={"password"},classes={User.class})
	private String password;
	
	/**角色id*/
	private String roleIds;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	@Override
	public String toString() {
		return "AdminAddForm [userId="+userId+", nickName=" + nickName + ", loginName=" + loginName + ", empId=" + empId + ", password=" + password + ", roleIds=" + roleIds + "]";
	}

	
}

