package com.ciwr.config.xss;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 防止跨站脚本攻击
 */
@Configuration
@Slf4j
@SuppressWarnings("unchecked")
public class XssConfig{

    /**
     * xss过滤拦截器
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        log.info("{XssFilterRegistration} Loading... ");
        FilterRegistrationBean xssFilterRegistrationBean = new FilterRegistrationBean();
        xssFilterRegistrationBean.setFilter(new XssFilter());
        xssFilterRegistrationBean.setOrder(Integer.MAX_VALUE);
        xssFilterRegistrationBean.setEnabled(true);
        xssFilterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = Maps.newHashMap();
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        initParameters.put("isIncludeRichText", "true");
        xssFilterRegistrationBean.setInitParameters(initParameters);
        return xssFilterRegistrationBean;
    }
}