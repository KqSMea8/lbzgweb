package com.lyarc.tp.corp.login.bean;

import java.util.List;

/**
 * 登录后获取到的登录信息
 * 
 * @version 1.0
 * @since JDK1.7
 * @author lidoudou
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年9月6日 下午4:29:15
 */
public class LoginInfo {

    /** 登录用户的类型：1.创建者 2.内部系统管理员 3.外部系统管理员 4.分级管理员 5.成员 */
    private Integer usertype;

    /** 登录用户的信息 */
    private UserInfo userInfo;

    /** 授权方企业信息 */
    private CorpInfo corpInfo;

    /** 该管理员在该提供商中能使用的应用列表，当登录用户为管理员时返回 */
    private List<Agent> agent;

    /** 该管理员拥有的通讯录权限，当登录用户为管理员时返回 */
    private AuthInfo authInfo;

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public CorpInfo getCorpInfo() {
        return corpInfo;
    }

    public void setCorpInfo(CorpInfo corpInfo) {
        this.corpInfo = corpInfo;
    }

    public List<Agent> getAgent() {
        return agent;
    }

    public void setAgent(List<Agent> agent) {
        this.agent = agent;
    }

    public AuthInfo getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }


    /**
     * 该管理员在该提供商中能使用的应用列表，当登录用户为管理员时返回
     * 
     * @version 1.0
     * @since JDK1.7
     * @author lidoudou
     * @company 洛阳艾克科技有限公司
     * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
     * @date 2017年9月6日 下午4:28:14
     */
    class Agent {

        /** 应用id */
        private Integer agentid;

        /** 该管理员对应用的权限：1.管理权限，0.使用权限 */
        private Integer authType;

        public Integer getAgentid() {
            return agentid;
        }

        public void setAgentid(Integer agentid) {
            this.agentid = agentid;
        }

        public Integer getAuthType() {
            return authType;
        }

        public void setAuthType(Integer authType) {
            this.authType = authType;
        }

        @Override
        public String toString() {
            return "Agent [agentid=" + agentid + ", authType=" + authType + "]";
        }

    }

    /** 该管理员拥有的通讯录权限，当登录用户为管理员时返回 */
    class AuthInfo {

        private List<Department> department;

        public List<Department> getDepartment() {
            return department;
        }

        public void setDepartment(List<Department> department) {
            this.department = department;
        }
    }
}
