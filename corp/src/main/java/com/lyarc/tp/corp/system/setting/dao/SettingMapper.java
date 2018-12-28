package com.lyarc.tp.corp.system.setting.dao;

import com.lyarc.tp.corp.system.setting.bean.Setting;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SettingMapper {
    int deleteByPrimaryKey(String name);

    int insert(Setting record);

    int insertSelective(Setting record);

    Setting selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Setting record);

    int updateByPrimaryKey(Setting record);
}