package com.lyarc.tp.corp.common.config;

import com.lyarc.tp.corp.common.config.storage.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;


/**
 * 文件上传配置
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月31日 16:43:56
 */
@Configuration
public class FileUploadConfig {
    @Value("${spring.http.multipart.location}")
    private String location;
//    @Value("${spring.http.multipart.max-file-size}")
//    private String maxFileSize;
//    @Value("${spring.http.multipart.max-request-size}")
//    private String maxRequestSize;

    // 初始化文件根路径
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }


    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }

    // @Bean
    // public MultipartConfigElement multipartConfigElement() {
    // MultipartConfigFactory factory = new MultipartConfigFactory();
    // // 设置文件大小限制 ,超出设置页面会抛出异常信息，
    // // 这样在文件上传的地方就需要进行异常信息的处理了;
    // factory.setMaxFileSize(maxFileSize); // KB,MB
    // /// 设置总上传数据总大小
    // factory.setMaxRequestSize(maxRequestSize);
    // // Sets the directory location where files will be stored.
    // factory.setLocation(location);
    // return factory.createMultipartConfig();
    // }



}
