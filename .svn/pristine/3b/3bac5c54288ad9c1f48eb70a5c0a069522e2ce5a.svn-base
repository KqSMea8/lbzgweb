package com.lyarc.tp.corp.warehouse.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.warehouse.bean.StockOpertype;
import com.lyarc.tp.corp.warehouse.bean.StockOpertypeQueryBean;
import com.lyarc.tp.corp.warehouse.form.StockOpertypeForm;
import com.lyarc.tp.corp.warehouse.service.StockOpertypeService;
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
@RequestMapping(path = "/api/storage/stockOpertype")
public class StockOpertypeController {

    @Autowired
    private StockOpertypeService stockOpertypeService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<List<StockOpertype>> list(@RequestBody StockOpertypeQueryBean queryBean) {

        List<StockOpertype> stockOpertypeList = stockOpertypeService.list(queryBean);
        return ResultVo.success(stockOpertypeList);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultVo<Integer> add(@Valid @RequestBody StockOpertypeForm stockOpertypeForm) {
        StockOpertype stockOpertype =
                JMapperUtils.getDestination(stockOpertypeForm, StockOpertype.class, StockOpertypeForm.class);
        LoginUser loginUser = UserHolder.getUser();
        stockOpertype.setCreator(loginUser.getUserId());
        Integer id = stockOpertypeService.add(stockOpertype);
        return ResultVo.success(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultVo<Integer> update(@Valid @RequestBody StockOpertypeForm stockOpertypeForm) {
        StockOpertype stockOpertype =
                JMapperUtils.getDestination(stockOpertypeForm, StockOpertype.class, StockOpertypeForm.class);
        stockOpertype.setUpdateTime(new Date());
        Integer rows = stockOpertypeService.update(stockOpertype);
        return ResultVo.success(rows);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("stockOpertypeId") String stockOpertypeId) {
        Integer rows = stockOpertypeService.delete(stockOpertypeId);
        return ResultVo.success(rows);
    }

    @RequestMapping("/get")
    @ResponseBody
    public ResultVo<StockOpertype> detail(@RequestParam("stockOpertypeId") String stockOpertypeId) {
        StockOpertype stockOpertype = stockOpertypeService.getStockOpertype(stockOpertypeId);
        return ResultVo.success(stockOpertype);
    }
}
