package com.lyarc.tp.corp.arg.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.arg.bean.Arg;
import com.lyarc.tp.corp.arg.bean.ArgQueryBean;
import com.lyarc.tp.corp.arg.service.ArgService;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.login.form.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017-12-27 13:58
 * @since JDK1.8
 */

@RestController
@RequestMapping(value = "/api/arg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
public class ArgController {

    @Autowired
    private ArgService argService;



    @RequestMapping("/list")
    public ResultVo<List<Arg>> argData(@RequestBody ArgQueryBean queryBean) {
        List<Arg> argData = argService.queryArgList(queryBean);
        return ResultVo.success(argData);
    }

    @RequestMapping("/listAll")
    public ResultVo<Map<String,List<Arg>>> listAll() {
        Map<String,List<Arg>> argData = argService.queryArgMap();
        return ResultVo.success(argData);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultVo<Integer> add(@RequestBody Arg arg) {
        LoginUser loginUser = UserHolder.getUser();
        Integer creator = loginUser.getUserId();
        arg.setCreator(creator);
        //校验编码是否存在
        ArgQueryBean queryBean = new ArgQueryBean();
        queryBean.setArgCode(arg.getArgCode());
        queryBean.setArgGroup(arg.getArgGroup());

        List<Arg> argData = argService.queryArgList(queryBean);
        if(argData!=null && argData.size()>0){
            return ResultVo.build(400, "数据已存在，请修改后重试");
        }
        Integer id = argService.add(arg);
        return ResultVo.success(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultVo<Integer> edit(@RequestBody Arg arg) {

        argService.update(arg);
        return ResultVo.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo<Integer> delete(@RequestParam("id") Integer id) {
        argService.delete(id);
        return ResultVo.success();
    }

}
