package com.lyarc.tp.corp.workflowact.constant;

 /**
  *
  * @description 操作日志状态
  * @param
  * @return
  * @author wangting
  * @date 2018/5/30 0030 14:41
  *
  */

public enum OperLogEnum {
    SEND("send","发送"),
    SUBMIT("submit", "提交"),
    RECALL("recall", "撤回"),
    TERMINATION("termination", "终止"),
    REVOKE("revoke", "撤销"),
    BACK("back", "回退");

    private String type;
    private String typeName;

    OperLogEnum(String type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

     public static String getName(String type) {
         switch (type) {
             case "send":
                 return SEND.getTypeName();
             case "submit":
                 return SUBMIT.getTypeName();
             case "recall":
                 return RECALL.getTypeName();
             case "termination":
                 return TERMINATION.getTypeName();
             case "revoke":
                 return REVOKE.getTypeName();
             case "back":
                 return BACK.getTypeName();
         }
         return SEND.getTypeName();
     }

     public String getType() {
         return type;
     }

     public void setType(String type) {
         this.type = type;
     }

     public String getTypeName() {
         return typeName;
     }

     public void setTypeName(String typeName) {
         this.typeName = typeName;
     }
 }
