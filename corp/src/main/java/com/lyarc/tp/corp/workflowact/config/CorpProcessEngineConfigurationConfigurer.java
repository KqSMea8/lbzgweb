package com.lyarc.tp.corp.workflowact.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *
 * @description 解决流程图中文乱码
 * @param
 * @return
 * @author wangting
 * @date 2018/5/10 0010 15:36
 *
 */
@Component
public class CorpProcessEngineConfigurationConfigurer implements ProcessEngineConfigurationConfigurer {
    @Autowired
    private Environment env;
    @Autowired
    private MyEngineListener engineListener;
   @Override
   public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
       processEngineConfiguration.setActivityFontName("宋体");
       processEngineConfiguration.setLabelFontName("宋体");
       processEngineConfiguration.setAnnotationFontName("宋体");
        //注释原因:这个listener 是为加签所用，引擎启动后需要将加签持久化的节点加载到引擎中，目前加签先不开发，故注释掉
//       processEngineConfiguration.setProcessEngineLifecycleListener(engineListener);
       //activiti 默认定时器时间太短(10s)，console 一致滚动日志，日志无法看 开发环境时间设置长一点
       String profile = env.getActiveProfiles()[0];
       if("dev".equals(profile)){
           processEngineConfiguration.getAsyncExecutor().setDefaultAsyncJobAcquireWaitTimeInMillis(300000);
           processEngineConfiguration.getAsyncExecutor().setDefaultTimerJobAcquireWaitTimeInMillis(300000);
       }
   }
}
