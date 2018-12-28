package com.lyarc.tp.corp.power.controller;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.power.dto.PowerDTO;
import com.lyarc.tp.corp.power.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 功能点
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年09月06日 11:45:51
 */
@Controller
@RequestMapping(value = "/api/power", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PowerController {

    @Autowired
    private PowerService powerService;

    /**
     * 方法描述 查询功能点列表
     * 
     * @return ResultVo<List<PowerDTO>>
     *
     * @author huanghaoqi
     * @date 2017年09月06日 11:48:52
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<List<PowerDTO>> list() {
        List<PowerDTO> powerDtoList = powerService.list(1);
        return ResultVo.success(powerDtoList);
    }
}
