package com.lyarc.tp.corp.system.material.form;

import com.googlecode.jmapper.annotations.JGlobalMap;

import javax.validation.constraints.NotNull;

@JGlobalMap
public class MaterialCategoryForm {
    private Integer id;

    @NotNull(message = "物料类别名称不能为空")
    private String title;
    
    @NotNull(message = "物料编码类别名称不能为空")
    private String code;

    private String remark;

    private Integer isInit;

    private Integer seq;

    private Integer parentId;

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsInit() {
        return isInit;
    }

    public void setIsInit(Integer isInit) {
        this.isInit = isInit;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

}