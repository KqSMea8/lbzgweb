package com.lyarc.tp.corp.workflow.form;

import com.lyarc.tp.corp.common.bean.BaseQueryBean;

/**
  *
  * @description
  * @param
  * @return
  * @author wangting
  * @date 2018/5/18 0018 14:48
  *
  */
public class WorkflowDraftParam extends BaseQueryBean {

    /**
     * 员工ID
     */
    private String empId;
    /**
     * 企业ID
     */
    private String corpId;

    /**
     * lastid 用于移动端分页
     */
    private String lastId;


     public String getEmpId() {
         return empId;
     }

     public void setEmpId(String empId) {
         this.empId = empId;
     }

     public String getCorpId() {
         return corpId;
     }

     public void setCorpId(String corpId) {
         this.corpId = corpId;
     }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }
}
