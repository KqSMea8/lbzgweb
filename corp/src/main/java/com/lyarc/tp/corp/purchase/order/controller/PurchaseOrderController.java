package com.lyarc.tp.corp.purchase.order.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.purchase.order.bean.PurchaseOrder;
import com.lyarc.tp.corp.purchase.order.bean.PurchaseOrderQueryBean;
import com.lyarc.tp.corp.purchase.order.constant.PurchaseOrderConstant;
import com.lyarc.tp.corp.purchase.order.dto.PurchaseOrderDto;
import com.lyarc.tp.corp.purchase.order.form.PurchaseOrderForm;
import com.lyarc.tp.corp.purchase.order.service.PurchaseOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@ResponseBody
@Controller
@RequestMapping(path = "/api/order")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<PageResultBean> list(@RequestBody PurchaseOrderQueryBean queryBean) {
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }

        List<PurchaseOrderDto> purchaseOrderList = purchaseOrderService.list(queryBean);
        Long count = purchaseOrderService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, purchaseOrderList));
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultVo<Integer> add(@Valid @RequestBody PurchaseOrderForm purchaseOrderForm) {
        LoginUser loginUser = UserHolder.getUser();
        purchaseOrderForm.setCreator(loginUser.getUserId());
        Integer id = purchaseOrderService.add(purchaseOrderForm);
        return ResultVo.success(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultVo<Integer> update(@RequestBody PurchaseOrderForm purchaseOrderForm) {
        Integer rows = purchaseOrderService.update(purchaseOrderForm);
        return ResultVo.success(rows);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("purchaseOrderId") String purchaseOrderId) {
        Integer rows = purchaseOrderService.delete(purchaseOrderId);
        return ResultVo.success(rows);
    }

    @RequestMapping("/get")
    @ResponseBody
    public ResultVo<PurchaseOrderDto> getPurchaseOrder(@RequestParam("purchaseOrderId") String purchaseOrderId) {
        PurchaseOrderDto purchaseOrder = purchaseOrderService.getPurchaseOrder(purchaseOrderId);
        return ResultVo.success(purchaseOrder);
    }

     /**
      *
      * @description 提交审批
      * @param
      * @return
      * @author wangting
      * @date 2018/8/3 0003 13:47
      *
      */
    @RequestMapping("/submit")
    @ResponseBody
    public ResultVo submit(@RequestBody PurchaseOrderForm purchaseOrderForm) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        if(StringUtils.isNotEmpty(purchaseOrderForm.getWfInstId())){
            purchaseOrder.setWfInstId(purchaseOrderForm.getWfInstId());
        }
        purchaseOrder.setPurchaseOrderId(purchaseOrderForm.getPurchaseOrderId());
        purchaseOrder.setWfInstTime(LocalDateTime.now());
        purchaseOrder.setStatus(PurchaseOrderConstant.VERIFYING);//审核中
        purchaseOrderService.updatePurchaseOrder(purchaseOrder);
        return ResultVo.success();
    }
}
