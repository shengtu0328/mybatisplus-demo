package com.xrq.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//配置分页插件
public class MybatisPlusConfig {


    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    /**
     * 分页插件 3.1.1版本的mp不需要写
     */
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }


    /**
     * 打印 sql，性能分析拦截器，不建议生产使用，他是有开销的
     * 设置 dev test 环境开启
     */
    @Bean
//    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor()
                .setFormat(true)//格式化sql
                .setMaxTime(5);//超过5毫秒就让sql停止
    }


}
