package com.ciwr.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration
@Slf4j
public class EncodingFilterConfig {

    /**
     * 方法签名不能定义为filterRegistrationBean(或者@Bean使用autowire = Autowire.BY_NAME,value = "encodingFilterRegistrationBean“)，
     * 否则不会装载bean，在application.yml中配置debug:true会发现如下信息:
     * Overriding bean definition for bean 'filterRegistrationBean' with a different definition: replacing
     * [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0;
     * autowireCandidate=true; primary=false; factoryBeanName=druidConfig; factoryMethodName=filterRegistrationBean;
     * initMethodName=null; destroyMethodName=(inferred);
     * defined in class path resource [com/ciwr/config/druid/DruidConfig.class]] with [Root bean: class [null]; scope=;
     * abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true;
     * primary=false; factoryBeanName=encodingFilterConfig; factoryMethodName=filterRegistrationBean;
     * initMethodName=null; destroyMethodName=(inferred);
     * defined in class path resource [com/ciwr/config/filter/EncodingFilterConfig.class]]
     * 即：DruidConfig.class存在重名的bean，这尼玛在团队开发过程中如何能保证bean不重名？
     * @return
     */
    @Bean(autowire = Autowire.BY_NAME,value = "encodingFilterRegistrationBean")
    public FilterRegistrationBean filterRegistrationBean() {
        log.info("FilterRegistrationBean Loading...");
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

}