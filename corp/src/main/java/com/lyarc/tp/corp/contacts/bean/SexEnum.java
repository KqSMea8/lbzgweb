package com.lyarc.tp.corp.contacts.bean;

/**
 * 性别类型
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月21日 09:38:24
 */
public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女");
    private Integer type;
    private String typeName;

    SexEnum(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public static SexEnum statusOf(Integer status) {
        if (status == null) {
            return MALE;
        }
        switch (status) {
            case 1:
                return MALE;
            case 2:
                return FEMALE;
        }
        return MALE;
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
