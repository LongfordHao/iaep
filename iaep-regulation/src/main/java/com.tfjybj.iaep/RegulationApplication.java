package com.tfjybj.iaep;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @author jeff
 * @description ${description}
 * @date 2021/2/21 16:59
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.tfjybj.iaep.entity")
@EnableJpaRepositories(basePackages = "com.tfjybj.iaep.provider.dao")
public class RegulationApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegulationApplication.class, args);
    }

    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
}
