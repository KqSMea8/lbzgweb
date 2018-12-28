package com.lyarc.tp.corp.contract.inquire.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.contract.inquire.bean.Inquire;
import com.lyarc.tp.corp.contract.inquire.bean.InquireDetail;
import com.lyarc.tp.corp.contract.inquire.bean.InquireQueryBean;
import com.lyarc.tp.corp.contract.inquire.dto.InquireDto;
import com.lyarc.tp.corp.contract.inquire.form.InquireDetailForm;
import com.lyarc.tp.corp.contract.inquire.form.InquireForm;
import com.lyarc.tp.corp.contract.inquire.service.InquireService;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.util.JMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author wangting
 */
@ResponseBody
@Controller
@RequestMapping(path = "/api/inquire")
public class InquireController {

    @Autowired
    private InquireService inquireService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<PageResultBean> list(@RequestBody InquireQueryBean queryBean) {
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }

        List<Inquire> inquireList = inquireService.list(queryBean);
        Long count = inquireService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, inquireList));
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultVo<Integer> add(@Valid @RequestBody InquireForm inquireForm) {
        LoginUser loginUser = UserHolder.getUser();
        inquireForm.setCreator(loginUser.getUserId());
        Integer id = inquireService.add(inquireForm);
        return ResultVo.success(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultVo<Integer> update(@Valid @RequestBody InquireForm inquireForm) {
        Integer rows = inquireService.update(inquireForm);
        return ResultVo.success(rows);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("inquireId") String inquireId) {
        Integer rows = inquireService.delete(inquireId);
        return ResultVo.success(rows);
    }

    @RequestMapping("/get")
    @ResponseBody
    public ResultVo<InquireDto> getInquire(@RequestParam("inquireId") String inquireId) {
        InquireDto inquire = inquireService.getInquire(inquireId);
        return ResultVo.success(inquire);
    }

//    @RequestMapping("/calcWeight/save")
//    @ResponseBody
//    public ResultVo<Integer> weightSave(@Valid @RequestBody InquireForm inquireForm) {
//        Integer rows = inquireService.calcWeightSave(inquireForm,1);
//        return ResultVo.success(rows);
//    }

    @RequestMapping("/weight/submit")
    @ResponseBody
    public ResultVo<Integer> weightSubmit(@Valid @RequestBody InquireForm inquireForm) {
        LoginUser loginUser = UserHolder.getUser();
        inquireForm.setDesigner(loginUser.getEmpId());
        Integer rows = inquireService.calcWeightSave(inquireForm,2);
        return ResultVo.success(rows);
    }

}
