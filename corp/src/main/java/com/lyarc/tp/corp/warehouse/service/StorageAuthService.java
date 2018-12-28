package com.lyarc.tp.corp.warehouse.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.warehouse.bean.StorageAuth;
import com.lyarc.tp.corp.warehouse.bean.StorageAuthKey;
import com.lyarc.tp.corp.warehouse.dao.StorageAuthMapper;
import com.lyarc.tp.corp.warehouse.dto.StorageAuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StorageAuthService {
    @Autowired
    private StorageAuthMapper storageAuthMapper;

    public List<StorageAuthDto> list(StorageAuthKey queryBean) {
        List<StorageAuthDto> storageAuthList = storageAuthMapper.list(queryBean);
        return storageAuthList;
    }


    public Integer delete(StorageAuthKey storageAuthKey) {
        int rows = storageAuthMapper.deleteByPrimaryKey(storageAuthKey);
        return rows;
    }

    public Integer update(StorageAuth storageAuth) {
        int rows = storageAuthMapper.updateByPrimaryKeySelective(storageAuth);
        return rows;
    }

    public Integer add(StorageAuth storageAuth) {
        //校验id 是否重复
        String storageId = storageAuth.getStorageId();
        StorageAuthKey spkey = new StorageAuthKey();
        spkey.setStorageId(storageId);
        spkey.setEmpId(storageAuth.getEmpId());
        StorageAuth existStorageAuth = storageAuthMapper.selectByPrimaryKey(spkey);
        if(existStorageAuth != null){
            throw new CorpException(ResultVo.failure("该用户已是有该仓库权限"));
        }
        int row = storageAuthMapper.insertSelective(storageAuth);
        return row;
    }

    public StorageAuth getStorageAuth(StorageAuthKey spkey) {
        StorageAuth storageAuth = storageAuthMapper.selectByPrimaryKey(spkey);
        return storageAuth;
    }

}
