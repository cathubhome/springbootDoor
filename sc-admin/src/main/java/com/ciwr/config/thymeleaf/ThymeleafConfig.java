package com.ciwr.config.thymeleaf;

import com.google.common.collect.Maps;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.Map;

@Configuration
public class ThymeleafConfig {

    @Resource
    private Environment env;

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) throws FileNotFoundException {
        if(viewResolver != null) {
            Map<String, Object> vars = Maps.newHashMap();
            vars.put("baseUrl", env.getProperty("baseUrl"));
            viewResolver.setStaticVariables(vars);
        }
    }
}
