package com.lyarc.tp.corp.attachfile.bean;

/**
 * 查询参数
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月31日 15:39:41
 */
public class QueryFileBean {

    /**
     * 所属模块
     */
    private String module;
    /**
     * 对象标识（记录的主键）
     */
    private String objId;
    /**
     * 最后一条附件 Id
     */
    private String lastFileId;
    /**
     * 查询条数
     */
    private Integer rows;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getLastFileId() {
        return lastFileId;
    }

    public void setLastFileId(String lastFileId) {
        this.lastFileId = lastFileId;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "QueryFileBean{" + "module='" + module + '\'' + ", objId='" + objId + '\'' + ", lastFileId='"
                + lastFileId + '\'' + ", rows=" + rows + '}';
    }
}
