package com.lyarc.tp.corp.warehouse.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.warehouse.bean.*;
import com.lyarc.tp.corp.warehouse.form.StorageForm;
import com.lyarc.tp.corp.warehouse.form.StoragePlaceForm;
import com.lyarc.tp.corp.warehouse.service.StoragePlaceService;
import com.lyarc.tp.corp.warehouse.service.StorageWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@ResponseBody
@Controller
@RequestMapping(path = "/api/storage/place")
public class StoragePlaceController {

    @Autowired
    private StoragePlaceService storagePlaceService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<List<StoragePlace>> list(@RequestBody StoragePlaceQueryBean queryBean) {

        List<StoragePlace> storagePlaceList = storagePlaceService.list(queryBean);
        return ResultVo.success(storagePlaceList);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultVo<Integer> add(@Valid @RequestBody StoragePlaceForm storagePlaceForm) {
        StoragePlace storagePlace =
                JMapperUtils.getDestination(storagePlaceForm, StoragePlace.class, StoragePlaceForm.class);
        LoginUser loginUser = UserHolder.getUser();
        storagePlace.setCreator(loginUser.getUserId());
        Integer id = storagePlaceService.add(storagePlace);
        return ResultVo.success(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultVo<Integer> update(@Valid @RequestBody StoragePlaceForm storagePlaceForm) {
        StoragePlace storagePlace =
                JMapperUtils.getDestination(storagePlaceForm, StoragePlace.class, StoragePlaceForm.class);
        storagePlace.setUpdateTime(new Date());
        Integer rows = storagePlaceService.update(storagePlace);
        return ResultVo.success(rows);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("storageId") String storageId,@RequestParam("placeId") String placeId) {
        StoragePlaceKey key = new StoragePlaceKey();
        key.setPlaceId(placeId);
        key.setStorageId(storageId);
        Integer rows = storagePlaceService.delete(key);
        return ResultVo.success(rows);
    }

    @RequestMapping("/get")
    @ResponseBody
    public ResultVo<StoragePlace> detail(@RequestParam("storageId") String storageId,@RequestParam("placeId") String placeId) {
        StoragePlaceKey key = new StoragePlaceKey();
        key.setPlaceId(placeId);
        key.setStorageId(storageId);
        StoragePlace storagePlace = storagePlaceService.getStoragePlace(key);
        return ResultVo.success(storagePlace);
    }

    @RequestMapping("/getStoragePlaceTree")
    @ResponseBody
    public ResultVo<List<StoragePlace>> getStoragePlaceTree() {
        List<StoragePlace> placeList = storagePlaceService.buildStoragePlaceTree();
        return ResultVo.success(placeList);
    }


}
