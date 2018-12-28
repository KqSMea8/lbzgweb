package com.lyarc.tp.corp.warehouse.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.warehouse.bean.Storage;
import com.lyarc.tp.corp.warehouse.bean.StorageQueryBean;
import com.lyarc.tp.corp.warehouse.dao.StorageMapper;
import com.lyarc.tp.corp.warehouse.dto.StorageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageWareService {
    @Autowired
    private StorageMapper storageMapper;

    public List<StorageDto> list(StorageQueryBean queryBean) {
        List<StorageDto> storageList = storageMapper.list(queryBean);
        return storageList;
    }


    public Long count(StorageQueryBean queryBean) {
       Long count =  storageMapper.count(queryBean);
       return count;
    }

    public Integer delete(String storageId) {
        int rows = storageMapper.deleteByPrimaryKey(storageId);
        return rows;
    }

    public Integer update(Storage storage) {
        int rows = storageMapper.updateByPrimaryKeySelective(storage);
        return rows;
    }

    public Integer add(Storage storage) {
        //校验id 是否重复
        String storageId = storage.getStorageId();
        Storage existStorage = storageMapper.selectByPrimaryKey(storageId);
        if(existStorage != null){
            throw new CorpException(ResultVo.failure("仓库代码已存在"));
        }
        int row = storageMapper.insertSelective(storage);
        return row;
    }

    public Storage getStorage(String storageId) {
        Storage storage = storageMapper.selectByPrimaryKey(storageId);
        return storage;
    }

     /**
      *
      * @description 反初始化
      * @param
      * @return
      * @author wangting
      * @date 2018/8/28 0028 14:11
      *
      */
    public Integer reverseInit(String storageId) {
        //todo 判断是否有出入移盘 操作
        Storage storage = new Storage();
        storage.setStorageId(storageId);
        storage.setInitStatus(0);
        Integer rows = storageMapper.updateByPrimaryKeySelective(storage);
        return rows;
    }
}
