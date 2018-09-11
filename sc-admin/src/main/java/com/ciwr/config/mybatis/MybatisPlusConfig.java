package com.ciwr.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.ciwr.global.constants.Constants;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * 扫描dao或者是Mapper接口
 */
@MapperScan("com.ciwr.mapper*")
@Configuration
public class MybatisPlusConfig {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private DruidDataSource druidDataSource;

    /**
     * mybatis-plus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        logger.info("{MyBatis-Plus} Load...");
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType(Constants.MYSQL_DIALECT);
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("pageSizeZero","true");
        properties.setProperty("reasonable","true");
        page.setProperties(properties);
        page.setLocalPage(true);
        return page;
    }

    /***
     * plus 的性能优化
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(1000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(Constants.FORMAT_SQL);
        performanceInterceptor.setWriteInLog(Constants.SHOW_SQL);
        return performanceInterceptor;
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis-plus.global-config")
    public GlobalConfiguration globalConfig() {
        logger.info("{mybatis-globalConfig} Load...");
        return new GlobalConfiguration();
    }

    @Bean(name = "mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        //数据源
        sqlSessionFactory.setDataSource(druidDataSource);
        //全局配置
        sqlSessionFactory.setGlobalConfig(globalConfig());
        PaginationInterceptor[] interceptor = {paginationInterceptor()};
        //分页插件
        sqlSessionFactory.setPlugins(interceptor);
        return sqlSessionFactory.getObject();
    }

}