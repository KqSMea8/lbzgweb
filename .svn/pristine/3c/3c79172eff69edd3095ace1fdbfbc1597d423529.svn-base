package com.lyarc.tp.corp.purchase.order.form;

import org.hibernate.validator.constraints.NotEmpty;

import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class InStockApplyStatusRejectForm {

	@NotEmpty(message = "单号不能为空")
	private String inStockApplyId;

	private String remark;// 驳回内容对应数据库字段remark
	private String auditRemark;

	public String getInStockApplyId() {
		return inStockApplyId;
	}

	public void setInStockApplyId(String inStockApplyId) {
		this.inStockApplyId = inStockApplyId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

}
