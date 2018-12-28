package com.lyarc.tp.corp.power.service;

import com.lyarc.tp.corp.power.bean.PowerBean;
import com.lyarc.tp.corp.power.dao.PowerMapper;
import com.lyarc.tp.corp.power.dto.PowerDTO;
import com.lyarc.tp.corp.util.JMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能点
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年09月06日 11:39:44
 */
@Service
public class PowerService {

    @Autowired
    private PowerMapper powerMapper;

    /**
     * 方法描述 查询功能点
     * 
     * @param show 是否显示
     * @return List<PowerDTO>
     *
     * @author huanghaoqi
     * @date 2017年09月06日 11:42:36
     */
    public List<PowerDTO> list(Integer show) {
        List<PowerBean> powerBeanList = powerMapper.list(show);
        return JMapperUtils.getDestinations(powerBeanList, PowerDTO.class, PowerBean.class);
    }

    public List<String> getPowerkeysByUserId(Integer userId){
        List<String> powerKeys = powerMapper.getPowerkeysByUserId(userId);
        return powerKeys;

    }
}
