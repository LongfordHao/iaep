package com.tfjybj.iaep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2020/11/13
 * Time: 10:40
 * Description:${DESCRIPTION}
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.tfjybj.iaep.entity")
@EnableJpaRepositories(basePackages = "com.tfjybj.iaep.provider.dao")
public class RuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RuleApplication.class, args);
    }

    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
}
