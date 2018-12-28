package com.lyarc.tp.corp.workflow.bean;

import java.util.Date;
/**
 * 工作流实例结束时事件,用于通知业务系统
 * @author rx
 *
 */
public class WorkflowInstEndEvent { 
	
    private String instId;

    private Integer cause;;

    private Integer wfId;
    
    /**
     * 操作人
     */
    private Integer operator;
    /**
     * 操作备注,驳回原因
     */
    private String remark;
    
    private String businessKey;
 

    public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}
    /**
     * 操作原因
     * @return 1.正常  2.驳回
     */
	public Integer getCause() {
		return cause;
	}
	/**
	 * 操作原因
	 * @param cause 1.正常  2.驳回
	 */
	public void setCause(Integer cause) {
		this.cause = cause;
	}

	public Integer getWfId() {
		return wfId;
	}

	public void setWfId(Integer wfId) {
		this.wfId = wfId;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }
}