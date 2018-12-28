package com.lyarc.tp.corp.system.setting.service;

import com.lyarc.tp.corp.system.setting.bean.Setting;
import com.lyarc.tp.corp.system.setting.dao.SettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    @Autowired
    private SettingMapper settingMapper;

    public void update(Setting setting) {
        settingMapper.updateByPrimaryKeySelective(setting);
    }

}
