package com.lyarc.tp.corp.contacts.dto;

/**
 * 
 * 封装返回联系人基本信息
 * 
 * @version 1.0 
 * @since JDK1.8
 * @author zhangjingtao 
 * @company 洛阳艾克科技有限公司 
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved. 
 * @date 2017年8月23日 上午10:51:05
 */
public class ContactDTO {

	/**员工id*/
	private String empId;

	
	/**部门id*/
	private String deptId;
	
	/**真实姓名*/
	private String trueName;
	
	/**头像*/
	private String header;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
    /*TODO 2017-08-24 这两个方法的作用？？*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactDTO other = (ContactDTO) obj;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		return true;
	}




}

