package com.lyarc.tp.corp.warehouse.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.corp.bean.Corp;
import com.lyarc.tp.corp.system.material.bean.MaterialCategory;
import com.lyarc.tp.corp.system.material.dto.MaterialCategoryNodeDto;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.warehouse.bean.StoragePlace;
import com.lyarc.tp.corp.warehouse.bean.StoragePlaceKey;
import com.lyarc.tp.corp.warehouse.bean.StoragePlaceQueryBean;
import com.lyarc.tp.corp.warehouse.dao.StoragePlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StoragePlaceService {
    @Autowired
    private StoragePlaceMapper storagePlaceMapper;

    public List<StoragePlace> list(StoragePlaceQueryBean queryBean) {
        List<StoragePlace> storagePlaceList = storagePlaceMapper.list(queryBean);
        return storagePlaceList;
    }

    public List<StoragePlace> buildStoragePlaceTree() {
        StoragePlaceQueryBean queryBean = new StoragePlaceQueryBean();
        List<StoragePlace> list = storagePlaceMapper.list(queryBean);
        Map<String, List<StoragePlace>> collect = list.stream().collect(Collectors.groupingBy(StoragePlace::getParentPlaceId));
        List<StoragePlace> res = new ArrayList<>();
        createTree("-1",collect,res);
        return res;
    }

    private void createTree(String id, Map<String, List<StoragePlace>> map,List<StoragePlace> res) {
        List<StoragePlace> list = map.get(id);
        if(list == null){
            return;
        }
        list.forEach(c->{
            String cid = c.getPlaceId();
            createTree(cid, map, c.getChildren());
            res.add(c);
        });
    }


    public Integer delete(StoragePlaceKey storagePlaceKey) {
        List<StoragePlace> childList = storagePlaceMapper.selectChildPlace(storagePlaceKey);
        if(childList != null && childList.size() >0){
            throw new CorpException(ResultVo.failure("该货位下有子货位，无法删除"));
        }
        int rows = storagePlaceMapper.deleteByPrimaryKey(storagePlaceKey);
        return rows;
    }

    public Integer update(StoragePlace storagePlace) {
        int rows = storagePlaceMapper.updateByPrimaryKeySelective(storagePlace);
        return rows;
    }

    public Integer add(StoragePlace storagePlace) {
        //校验id 是否重复
        String storageId = storagePlace.getStorageId();
        StoragePlaceKey spkey = new StoragePlaceKey();
        spkey.setStorageId(storageId);
        spkey.setPlaceId(storagePlace.getPlaceId());
        StoragePlace existStoragePlace = storagePlaceMapper.selectByPrimaryKey(spkey);
        if(existStoragePlace != null){
            throw new CorpException(ResultVo.failure("该货位已存在"));
        }
        int row = storagePlaceMapper.insertSelective(storagePlace);
        return row;
    }

    public StoragePlace getStoragePlace(StoragePlaceKey spkey) {
        StoragePlace storagePlace = storagePlaceMapper.selectByPrimaryKey(spkey);
        return storagePlace;
    }

}
