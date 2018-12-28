package com.lyarc.tp.corp.warehouse.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.warehouse.bean.Storage;
import com.lyarc.tp.corp.warehouse.bean.StorageQueryBean;
import com.lyarc.tp.corp.warehouse.dto.StorageDto;
import com.lyarc.tp.corp.warehouse.form.StorageForm;
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
@RequestMapping(path = "/api/storage")
public class StorageController {

    @Autowired
    private StorageWareService storageService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<PageResultBean> list(@RequestBody StorageQueryBean queryBean) {
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }

        List<StorageDto> storageList = storageService.list(queryBean);
        Long count = storageService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, storageList));
    }

    @RequestMapping("/listAll")
    @ResponseBody
    public ResultVo<List<StorageDto>> listAll() {
        StorageQueryBean queryBean = new StorageQueryBean();
        List<StorageDto> storageList = storageService.list(queryBean);
        return ResultVo.success(storageList);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultVo<Integer> add(@Valid @RequestBody StorageForm storageForm) {
        Storage storage =
                JMapperUtils.getDestination(storageForm, Storage.class, StorageForm.class);
        LoginUser loginUser = UserHolder.getUser();
        storage.setCreator(loginUser.getUserId());
        Integer id = storageService.add(storage);
        return ResultVo.success(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultVo<Integer> update(@Valid @RequestBody StorageForm storageForm) {
        Storage storage =
                JMapperUtils.getDestination(storageForm, Storage.class, StorageForm.class);
        storage.setUpdateTime(new Date());
        Integer rows = storageService.update(storage);
        return ResultVo.success(rows);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("storageId") String storageId) {
        Integer rows = storageService.delete(storageId);
        return ResultVo.success(rows);
    }

    @RequestMapping("/get")
    @ResponseBody
    public ResultVo<Storage> detail(@RequestParam("storageId") String storageId) {
        Storage storage = storageService.getStorage(storageId);
        return ResultVo.success(storage);
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResultVo<List<StorageDto>> search(@RequestBody StorageQueryBean queryBean) {
        List<StorageDto> storageList = storageService.list(queryBean);
        return ResultVo.success(storageList);
    }

    @RequestMapping("/reverseInit")
    @ResponseBody
    public ResultVo<Integer> reverseInit(@RequestParam("storageId") String storageId) {
        Integer rows = storageService.reverseInit(storageId);
        return ResultVo.success(rows);
    }

}
