package com.lyarc.tp.corp.warehouse.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.warehouse.bean.Storage;
import com.lyarc.tp.corp.warehouse.bean.StorageAdmin;
import com.lyarc.tp.corp.warehouse.bean.StorageAuth;
import com.lyarc.tp.corp.warehouse.bean.StorageAuthKey;
import com.lyarc.tp.corp.warehouse.dao.StorageAdminMapper;
import com.lyarc.tp.corp.warehouse.dao.StorageAuthMapper;
import com.lyarc.tp.corp.warehouse.dto.StorageAdminDto;
import com.lyarc.tp.corp.warehouse.dto.StorageAuthDto;
import com.lyarc.tp.corp.warehouse.form.StorageAuthForm;
import com.lyarc.tp.corp.warehouse.form.StorageForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StorageAdminService {
    @Autowired
    private StorageAdminMapper storageAdminMapper;
    @Autowired
    private StorageAuthMapper storageAuthMapper;

    public List<StorageAdminDto> list() {
        List<StorageAdminDto> storageAdminList = storageAdminMapper.list();
        return storageAdminList;
    }

    public Integer delete(String  empId) {
        int rows = storageAdminMapper.deleteByPrimaryKey(empId);
        return rows;
    }

    public Integer update(StorageAdmin storageAdmin) {
        int rows = storageAdminMapper.updateByPrimaryKeySelective(storageAdmin);
        return rows;
    }

    public Integer add(StorageAdmin storageAdmin) {
        String empId = storageAdmin.getEmpId();
        if(StringUtils.isEmpty(empId)){
            throw new CorpException(ResultVo.failure("员工id不能为空"));
        }
        //校验id 是否重复
        StorageAdmin existStorageAdmin = storageAdminMapper.selectByPrimaryKey(storageAdmin.getEmpId());
        if(existStorageAdmin != null){
            throw new CorpException(ResultVo.failure("该用户已是仓库管理员"));
        }
        int row = storageAdminMapper.insertSelective(storageAdmin);
        return row;
    }

    public List<StorageAuthDto> getAuth(String  empId) {
        StorageAuthKey key = new StorageAuthKey();
        key.setEmpId(empId);
        List<StorageAuthDto> list = storageAuthMapper.getAuth(key);
        return list;
    }

    @Transactional
    public Integer setAuth(StorageAuthForm form) {
        Integer rows =0;
        String empId = form.getEmpId();
        storageAuthMapper.deleteByEmpId(empId);
        String[] storageIds = form.getStorageIds();
        for (String storageId : storageIds) {
            StorageAuth storageAuth = new StorageAuth();
            storageAuth.setEmpId(empId);
            storageAuth.setStorageId(storageId);
            storageAuthMapper.insertSelective(storageAuth);
            rows++;
        }
        return rows;
    }

}
