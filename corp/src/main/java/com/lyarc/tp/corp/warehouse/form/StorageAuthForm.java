package com.lyarc.tp.corp.warehouse.form;


import javax.validation.constraints.NotNull;
import java.util.Date;

public class StorageAuthForm {
    @NotNull(message = "仓库代码不能为空")
    private String[] storageIds;
    @NotNull(message = "员工id不能为空")
    private String empId;

    public String[] getStorageIds() {
        return storageIds;
    }

    public void setStorageIds(String[] storageIds) {
        this.storageIds = storageIds;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
}