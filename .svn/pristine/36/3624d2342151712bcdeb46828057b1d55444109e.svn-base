package com.lyarc.tp.corp.power.dao;

import com.lyarc.tp.corp.power.bean.PowerBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 注释
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年09月05日 11:33:10
 */
@Mapper
public interface PowerMapper {

    /**
     * 方法描述 查询功能点
     * 
     * @param show 是否显示
     * @return List<PowerBean>
     *
     * @author huanghaoqi
     * @date 2017年09月05日 11:41:32
     */
    List<PowerBean> list(@Param("show") Integer show);

    /**
     * 通过权限编号列表获取power_key
     *
     * @param ids
     * @return
     * 
     * @author lidoudou
     * @date 2017年9月6日 下午6:58:15
     */
    List<String> getByPowerIds(@Param("ids") List<String> ids);

    List<String> getPowerkeysByUserId(@Param("userId") Integer userId);
}
