package com.lyarc.tp.corp.common.config.wxcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.message.WxCpMessageRouter;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Configuration
@ConditionalOnClass(WxCpService.class)
@EnableConfigurationProperties({WxCpProperties.class,WxCpWorkflowProperties.class,WxCpManageProperties.class}) 
public class WxCpConfiguration {
  @Autowired
  private WxCpProperties properties;

  @Autowired
  private WxCpWorkflowProperties workflowProperties;
  
  @Autowired
  private WxCpManageProperties manageProperties;

  @Bean
  @ConditionalOnMissingBean
  public WxCpConfigStorage configStorage() {
    WxCpInMemoryConfigStorage configStorage = new WxCpInMemoryConfigStorage();
    configStorage.setCorpId(this.properties.getCorpId());
    configStorage.setAgentId(this.properties.getAgentId());
    configStorage.setCorpSecret(this.properties.getSecret());
    configStorage.setToken(this.properties.getToken());
    configStorage.setAesKey(this.properties.getAesKey());
    return configStorage;
  }

  // 同步通讯录
  @Bean(name="wxCpService")
  @ConditionalOnMissingBean
  public WxCpService wxCpService(WxCpConfigStorage configStorage) {
    WxCpService service = new me.chanjar.weixin.cp.api.impl.WxCpServiceImpl();
    service.setWxCpConfigStorage(configStorage);
    return service;
  }
  
  // 工作流应用
  @Bean(name="wxCpWorkflowService")
  public WxCpService wxCpWorkflowService() {
    WxCpService service = new me.chanjar.weixin.cp.api.impl.WxCpServiceImpl();

    WxCpInMemoryConfigStorage configStorage = new WxCpInMemoryConfigStorage();
    configStorage.setCorpId(this.workflowProperties.getCorpId());
    configStorage.setAgentId(this.workflowProperties.getAgentId());
    configStorage.setCorpSecret(this.workflowProperties.getSecret());
    configStorage.setToken(this.workflowProperties.getToken());
    configStorage.setAesKey(this.workflowProperties.getAesKey());

    service.setWxCpConfigStorage(configStorage);
    return service;
  }
  
  // pc 端应用
  @Bean(name="wxCpManageService")
  public WxCpService wxCpManageService() {
    WxCpService service = new me.chanjar.weixin.cp.api.impl.WxCpServiceImpl();

    WxCpInMemoryConfigStorage configStorage = new WxCpInMemoryConfigStorage();
    configStorage.setCorpId(this.manageProperties.getCorpId());
    configStorage.setAgentId(this.manageProperties.getAgentId());
    configStorage.setCorpSecret(this.manageProperties.getSecret());
    configStorage.setToken(this.manageProperties.getToken());
    configStorage.setAesKey(this.manageProperties.getAesKey());

    service.setWxCpConfigStorage(configStorage);
    return service;
  }

  @Bean
  public WxCpMessageRouter router(WxCpService wxCpService) {
    final WxCpMessageRouter newRouter = new WxCpMessageRouter(wxCpService);

    return newRouter;
  }

}
