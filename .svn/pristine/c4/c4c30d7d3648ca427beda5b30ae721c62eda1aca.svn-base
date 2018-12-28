package com.lyarc.tp.corp.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * 
 * 子账号信息 
 * 
 * @version 1.0 
 * @since JDK1.8 
 * @author zhangjingtao 
 * @company 洛阳艾克科技有限公司 
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved. 
 * @date 2017年9月5日 上午10:00:24
 */
public class GetUserDTO {

	private Integer userId;
	
	/**登录账号*/
	private String loginName;
	
	private String empId;
	
	/**角色id*/
	private String roleIds;
	
	private String nickName;
	
	/**角色名称*/
	private String roleNames;
	
	/**备注*/
	private String remark;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "GetAdminDTO [userId=" + userId + ", loginName=" + loginName + ", empId=" + empId + ", roleIds=" + roleIds + ", nickName=" + nickName + ", roleNames=" + roleNames + ", remark=" + remark + ", createTime=" + createTime + "]";
	}
	

}

