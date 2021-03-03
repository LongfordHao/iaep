package com.tfjybj.ieap.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2020/11/11
 * Time: 17:54
 * Description:${DESCRIPTION}
 */
//添加@enablediscoveryclient连接到nacos config
@EnableDiscoveryClient

@ComponentScan({"com.tfjybj.ieap.gateway.config"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IeapGetwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(IeapGetwayApplication.class,args);
    }
}
