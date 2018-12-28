package com.lyarc.tp.corp.purchase.provider.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.purchase.provider.bean.Provider;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderQueryBean;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderDto;
import com.lyarc.tp.corp.purchase.provider.form.ProviderForm;
import com.lyarc.tp.corp.purchase.provider.service.ProviderService;
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

@ResponseBody
@Controller
@RequestMapping(path = "/api/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultVo<PageResultBean> list(@RequestBody ProviderQueryBean queryBean) {
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }

        List<ProviderDto> providerList = providerService.list(queryBean);
        Long count = providerService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, providerList));
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultVo<Integer> add(@Valid @RequestBody ProviderForm providerForm) {
        Provider provider =
                JMapperUtils.getDestination(providerForm, Provider.class, ProviderForm.class);
        LoginUser loginUser = UserHolder.getUser();
        provider.setCreator(loginUser.getUserId());
        Integer id = providerService.add(provider);
        return ResultVo.success(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultVo<Integer> update(@Valid @RequestBody ProviderForm providerForm) {
        Provider provider =
                JMapperUtils.getDestination(providerForm, Provider.class, ProviderForm.class);
        provider.setUpdateTime(new Date());
        Integer rows = providerService.update(provider);
        return ResultVo.success(rows);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public ResultVo<Integer> updateStatus(@RequestBody ProviderForm providerForm) {
        Provider provider = new Provider();
        provider.setProviderId(providerForm.getProviderId());
        provider.setStatus(providerForm.getStatus());
        provider.setUpdateTime(new Date());
        Integer rows = providerService.update(provider);
        return ResultVo.success(rows);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("providerId") String providerId) {
        Integer rows = providerService.delete(providerId);
        return ResultVo.success(rows);
    }

    @RequestMapping("/get")
    @ResponseBody
    public ResultVo<Provider> detail(@RequestParam("providerId") String providerId) {
        Provider provider = providerService.getProvider(providerId);
        return ResultVo.success(provider);
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResultVo<List<ProviderDto>> search(@RequestBody ProviderQueryBean queryBean) {
        List<ProviderDto> providerList = providerService.list(queryBean);
        return ResultVo.success(providerList);
    }

}
