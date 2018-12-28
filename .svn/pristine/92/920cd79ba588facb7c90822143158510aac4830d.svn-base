package com.lyarc.tp.corp;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
/* 禁用activiti api 的校验配置 */
@EnableAutoConfiguration(exclude = { org.activiti.spring.boot.RestApiAutoConfiguration.class,
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
		org.activiti.spring.boot.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class })
public class CorpApplication {

	public static ApplicationContext APPLICATIONCONTEXT;

	public static void main(String[] args) {
		APPLICATIONCONTEXT = new SpringApplicationBuilder(CorpApplication.class).web(true).run(args);
		// SpringApplication.run(CorpApplication.class, args);
	}
}
