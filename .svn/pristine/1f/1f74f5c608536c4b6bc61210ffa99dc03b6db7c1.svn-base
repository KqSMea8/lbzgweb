package com.lyarc.tp.corp.warehouse.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.warehouse.bean.StorageAdmin;
import com.lyarc.tp.corp.warehouse.dto.StorageAdminDto;
import com.lyarc.tp.corp.warehouse.dto.StorageAuthDto;
import com.lyarc.tp.corp.warehouse.form.StorageAuthForm;
import com.lyarc.tp.corp.warehouse.service.StorageAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@ResponseBody
@Controller
@RequestMapping(path = "/api/storage/admin")
public class StorageAdminController {

    @Autowired
    private StorageAdminService storageAdminService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<List<StorageAdminDto>> list() {
        List<StorageAdminDto> storageAdminList = storageAdminService.list();
        return ResultVo.success(storageAdminList);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultVo<Integer> add(@RequestBody StorageAdmin storageAdmin) {
        LoginUser loginUser = UserHolder.getUser();
        storageAdmin.setCreator(loginUser.getUserId());
        Integer id = storageAdminService.add(storageAdmin);
        return ResultVo.success(id);
    }



    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> remove(@RequestParam("empId") String empId) {
        Integer rows = storageAdminService.delete(empId);
        return ResultVo.success(rows);
    }

    @RequestMapping("/getAuth")
    @ResponseBody
    public ResultVo<List<StorageAuthDto>> getAuth(@RequestParam("empId") String empId) {
        List<StorageAuthDto> auth = storageAdminService.getAuth(empId);
        return ResultVo.success(auth);
    }

    @RequestMapping("/setAuth")
    @ResponseBody
    public ResultVo<Integer> setAuth(@RequestBody StorageAuthForm storageAuthForm) {
        Integer rows = storageAdminService.setAuth(storageAuthForm);
        return ResultVo.success(rows);
    }

}
