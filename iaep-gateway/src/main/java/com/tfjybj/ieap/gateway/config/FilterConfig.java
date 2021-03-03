//package com.tfjybj.ieap.gateway.config;
//
////import com.tfjybj.auth.gateway.filter.AuthGatewayFilter;
////import com.tfjybj.auth.gateway.filter.ResourseGatewayFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Author: LangFordHao
// * Version:
// * Date: 2020/11/11
// * Time: 18:03
// * Description:${DESCRIPTION}
// */
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public AuthGatewayFilter authGatewayFilter() {
//        //配置拦截器参数
//        //order：序号，设置拦截器执行顺序，AuthGatewayFilter为1
//        return new AuthGatewayFilter(1);
//    }
//
//    @Bean
//    public ResourseGatewayFilter resourseGatewayFilter() {
//        //配置拦截器参数
//        //order：序号，设置拦截器执行顺序，ResourseGatewayFilter为2
//        //path：项目yml中配置的context-path（有则设置，无则不填，这里不填写），配置资源时使用
//        return new ResourseGatewayFilter(2);
//    }
//}
