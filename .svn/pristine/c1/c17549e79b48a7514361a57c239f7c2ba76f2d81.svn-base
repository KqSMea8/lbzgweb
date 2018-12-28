package com.lyarc.tp.corp.contacts.controller;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.contacts.service.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 同步Controleller
 * 
 * @version 1.0
 * @since JDK1.8
 * @author lidoudou
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月23日 下午6:15:09
 */
@Controller
public class ContactController {

    private static Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private SyncService syncService;
    /**
     * 同步用户信息
     *
     * @return
     *
     * @author lidoudou
     * @date 2017年8月23日 下午5:26:25
     */
    @RequestMapping(value = "/api/contacts/sync", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> syncUser() {
        try {
            syncService.syncData();
        } catch (Exception e) {
            logger.error("同步用户信息出错，异常信息为：{}", e.getMessage(),e);
        }
        return ResultVo.success("");
    }

    /**
     * 获取通讯录同步状态
     *
     * @return
     *
     * @author lidoudou
     * @date 2017年9月20日 下午6:13:21
     */
    @RequestMapping(value = "/api/contacts/getSyncStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<Map> getSyncStatus() {
        Map<String, Object> resMap = new HashMap<>();

        Integer syncStatus = SyncService.syncStatus;
        resMap.put("syncStatus", syncStatus);
        resMap.put("startTime", SyncService.startTime);
        resMap.put("threadId", SyncService.threadId);
        if(syncStatus == 0){//未同步
            resMap.put("endTime", SyncService.endTime);
            resMap.put("useTime", SyncService.useTime);
            resMap.put("execResult", SyncService.execResult);
        }
        return ResultVo.success(resMap);
    }
}
