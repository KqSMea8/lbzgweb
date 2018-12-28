package com.lyarc.tp.corp.purchase.provider.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.purchase.provider.bean.NewProviderMaterial;
import com.lyarc.tp.corp.purchase.provider.bean.NewProviderMaterialQueryBean;
import com.lyarc.tp.corp.purchase.provider.dto.NewProviderMaterialDto;
import com.lyarc.tp.corp.purchase.provider.form.NewProviderMaterialForm;
import com.lyarc.tp.corp.purchase.provider.service.NewProviderMaterialService;
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
@RequestMapping(path = "/api/newProviderMaterial")
public class NewProviderMaterialController {

    @Autowired
    private NewProviderMaterialService newProviderMaterialService;
    
    //审核中
    private static Integer VERIFYING =1;

    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<PageResultBean> list(@RequestBody NewProviderMaterialQueryBean queryBean) {
        //LoginUser user = UserHolder.getUser();
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }

        List<NewProviderMaterialDto> newProviderMaterialList = newProviderMaterialService.list(queryBean);
        Long count = newProviderMaterialService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, newProviderMaterialList));
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultVo<Integer> add(@Valid @RequestBody NewProviderMaterialForm newProviderMaterialForm) {
        LoginUser loginUser = UserHolder.getUser();
        newProviderMaterialForm.setCreator(loginUser.getUserId());
        newProviderMaterialForm.setSubmiter(loginUser.getEmpId());
//        newProviderMaterialForm.setSubmitTime(LocalDateTime.now());
        Integer id = newProviderMaterialService.add(newProviderMaterialForm);
        return ResultVo.success(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultVo<Integer> update(@RequestBody NewProviderMaterialForm newProviderMaterialForm) {
        Integer rows = newProviderMaterialService.update(newProviderMaterialForm);
        return ResultVo.success(rows);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("newProviderMaterialId") String newProviderMaterialId) {
        Integer rows = newProviderMaterialService.delete(newProviderMaterialId);
        return ResultVo.success(rows);
    }

    @RequestMapping("/get")
    @ResponseBody
    public ResultVo<NewProviderMaterialDto> getNewProviderMaterial(@RequestParam("newProviderMaterialId") String newProviderMaterialId) {
        NewProviderMaterialDto newProviderMaterial = newProviderMaterialService.getNewProviderMaterial(newProviderMaterialId);
        return ResultVo.success(newProviderMaterial);
    }

     /**
      *
      * @description 提交审批：前端页面调用流程发起 ，会得到流程实例id ,再调用此接口，将流程实例id 保存
      * @param
      * @return
      * @author wangting
      * @date 2018/8/3 0003 13:47
      *
      */
    @RequestMapping("/submit")
    @ResponseBody
    public ResultVo submit(@RequestBody NewProviderMaterialForm newProviderMaterialForm) {
//        NewProviderMaterialDto newProviderMaterial = newProviderMaterialService.getNewProviderMaterial(newProviderMaterialId);
//        return ResultVo.success(newProviderMaterial);
        NewProviderMaterial newProviderMaterial = new NewProviderMaterial();

        if(StringUtils.isNotEmpty(newProviderMaterialForm.getWfInstId())){
            newProviderMaterial.setWfInstId(newProviderMaterialForm.getWfInstId());
        }
        newProviderMaterial.setNpmId(newProviderMaterialForm.getNpmId());
        newProviderMaterial.setWfInstTime(LocalDateTime.now());
        newProviderMaterial.setWfStatus(VERIFYING);//审核中
        newProviderMaterialService.updateNewProviderMaterial(newProviderMaterial);
        return ResultVo.success();
    }

    @RequestMapping("/report/complete")
    @ResponseBody
    public ResultVo<Integer> complete(@RequestBody NewProviderMaterialForm newProviderMaterialForm) {
        if(StringUtils.isEmpty(newProviderMaterialForm.getNpmId())){
            throw new CorpException(ResultVo.failure("npmid 不能为空"));
        }
        LoginUser user = UserHolder.getUser();
        newProviderMaterialForm.setReporter(user.getEmpId());
        Integer rows = newProviderMaterialService.completeReport(newProviderMaterialForm);
        return ResultVo.success(rows);
    }

}
