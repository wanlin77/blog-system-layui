package com.wl.bs.cofig;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description: mybatis-plus配置
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/10/26 9:53
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.wl.bs.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
