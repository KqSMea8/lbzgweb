package com.lyarc.tp.corp.system.material.dto;

import com.googlecode.jmapper.annotations.JGlobalMap;

import java.util.ArrayList;
import java.util.List;

@JGlobalMap(excluded = {"children"})
public class MaterialCategoryNodeDto {

    private Integer id;

    private String title;

    private String remark;

    private Integer isInit;

    private Integer seq;

    private Integer parentId;

    private List<MaterialCategoryNodeDto> children = new ArrayList<>(0);

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
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public List<MaterialCategoryNodeDto> getChildren() {
        return children;
    }

    public void setChildren(List<MaterialCategoryNodeDto> children) {
        this.children = children;
    }
}
