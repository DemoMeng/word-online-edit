package com.mqz.online.edit.config.web;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.plugin.InterceptorChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mqz
 * @description
 * @since 2020/4/26
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusPageConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        InterceptorChain ic = new InterceptorChain();
        return paginationInterceptor;
    }
}
