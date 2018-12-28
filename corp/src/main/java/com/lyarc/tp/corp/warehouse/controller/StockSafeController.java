package com.lyarc.tp.corp.warehouse.controller;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.warehouse.bean.StockSafe;
import com.lyarc.tp.corp.warehouse.bean.StockSafeKey;
import com.lyarc.tp.corp.warehouse.bean.StockSafeQueryBean;
import com.lyarc.tp.corp.warehouse.form.StockSafeForm;
import com.lyarc.tp.corp.warehouse.service.StockSafeService;
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
@RequestMapping(path = "/api/storage/stockSafe")
public class StockSafeController {

    @Autowired
    private StockSafeService stockSafeService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<PageResultBean> list(@RequestBody StockSafeQueryBean stockSafe) {
        PageResultBean pageResultBean = stockSafeService.list(stockSafe);
        return ResultVo.success(pageResultBean);
    }

    @RequestMapping("/batch")
    @ResponseBody
    public ResultVo<Integer> batch(@Valid @RequestBody List<StockSafeForm> stockSafeList) {
        List<StockSafe> stockSafes = JMapperUtils.getDestinations(stockSafeList, StockSafe.class, StockSafeForm.class);
        Integer id = stockSafeService.addOrUpdate(stockSafes);
        return ResultVo.success(id);
    }


    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("storageId") String storageId, @RequestParam("materId") String materId) {
        StockSafeKey key = new StockSafeKey();
        key.setStorageId(storageId);
        key.setMaterId(materId);
        Integer rows = stockSafeService.delete(key);
        return ResultVo.success(rows);
    }

}
