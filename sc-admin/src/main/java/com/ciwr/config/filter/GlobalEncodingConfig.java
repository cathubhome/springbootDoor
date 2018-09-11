package com.ciwr.config.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局编码过滤器
 */
@Component
@WebFilter(urlPatterns = "/*")
@SuppressWarnings("all")
public class GlobalEncodingConfig implements Filter {

    @Value("${server.defaultCharset}")
    private String defaultCharset;

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String charset = this.config.getInitParameter("charset");
        if (charset == null) {
            charset = defaultCharset;
        }
        //设置编码
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}