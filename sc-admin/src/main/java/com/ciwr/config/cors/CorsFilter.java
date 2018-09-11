package com.ciwr.config.cors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class CorsFilter implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.debug("CorsFilter Loading...");
        // 配置可以被跨域的路径
        registry.addMapping("/**")
                // 允许所有的请求方法访问该跨域资源服务器，如：POST、GET、PUT、DELETE等
                .allowedMethods("*")
                // 允许所有的请求域名访问我们的跨域资源
                .allowedOrigins("*")
                // 允许所有的请求header访问,可自定义任意请求头信息
                .allowedHeaders("*")
                // 是否允许发送Cookie(CORS请求默认不发送Cookie和HTTP认证信息)
                .allowCredentials(true);
    }
}
