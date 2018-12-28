package com.lyarc.tp.corp.corp.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;

/**
 * 企业
 * 
 * @version 1.0
 * @since JDK1.8
 * @author lidoudou
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月3日 上午10:52:36
 */
@Controller
public class CorpController {


    /**
     * 返回应用前端 spa 首页 - rx
     */
    @RequestMapping(value = {"/index", "/test"}, produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String index(Model model) {
        
        String html = getFileContent("public/index.html");
        return html;
    }
    
    private String getFileContent(String path){
        String html = "";
        try {
            InputStream streamReader = new ClassPathResource(path).getInputStream();
            if (streamReader != null) { 
                html = IOUtils.toString(streamReader, "UTF-8"); 
            }else{
                html = "读取文件失败！";
            }
        } catch (Exception ex) {
            //logger.error("读取文件'public/index.html'失败",ex);
            html = "读取文件失败！";
        }

        return html;
    }

    /**
     * 
     * 方法描述 打开菜单页面
     * 
     * @param
     * @return
     * 
     * @author zhangjingtao
     * @date 2017年8月3日 下午5:10:43
     */
    @RequestMapping(value = "/mainpanel", method = RequestMethod.GET)
    public String corpMenu() {
        return "debugMainpanel";
    }


}

