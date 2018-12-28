package com.lyarc.tp.corp.login.bean;

/**
 * 登陆后获取到的corpinfo
 * 
 * @version 1.0
 * @since JDK1.8
 * @author lidoudou
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年9月6日 下午4:27:17
 */
public class CorpInfo {


    /** 企业编号 */
    private String corpid;

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    @Override
    public String toString() {
        return "CorpInfo [corpid=" + corpid + "]";
    }

}
