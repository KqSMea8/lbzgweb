package com.lyarc.tp.corp.contacts.bean;

/**
 * 
 * 员工关注状态
 * 
 * @version 1.0
 * @since JDK1.8
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年9月13日 上午9:06:38
 */
public enum StatusEnum {
    UNFOLLOW(0, "未关注"),
    FOLLOW(1, "已关注");
    private Integer type;
    private String typeName;

    StatusEnum(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public static StatusEnum statusOf(Integer status) {
        if (status == null) {
            return UNFOLLOW;
        }
        switch (status) {
            case 0:
                return UNFOLLOW;
            case 1:
                return FOLLOW;
        }
        return UNFOLLOW;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
