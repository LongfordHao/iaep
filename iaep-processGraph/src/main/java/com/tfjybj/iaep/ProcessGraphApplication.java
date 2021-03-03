package com.tfjybj.iaep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.tfjybj.iaep.entity")
@EnableJpaRepositories(basePackages = "com.tfjybj.iaep.provider.dao")
public class ProcessGraphApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProcessGraphApplication.class, args);
    }

    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
}
