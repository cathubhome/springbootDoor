package com.ciwr.global.exception;

import com.ciwr.global.common.utils.G;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_EXTENDED;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    /**
     * 在controller里面内容执行之前，校验一些参数不匹配啊，Get post方法不对啊之类的
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return new ResponseEntity<Object>(G.error(), NOT_EXTENDED);

    }

    @ExceptionHandler(GlobalException.class)
    public G globalHandler(GlobalException e) throws Exception {

        logger.error(e.getMessage(), e);

        return G.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public G authorityHandler(AuthorizationException e) throws Exception {

        logger.error(e.getMessage(), e);

        return G.error("没有授权,请联系管理员授权!");
    }

    @ExceptionHandler(ExpiredSessionException.class)
    public G expiredSessionHandler(ExpiredSessionException e) throws Exception {

        logger.error(e.getMessage(), e);

        return G.error("操作超时请重新登录!");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public G unauthorizedHandler(UnauthorizedException e) throws Exception {

        logger.error("没有授权,请联系管理员授权!");

        return G.error("没有授权,请联系管理员授权!");
    }

    @ExceptionHandler(Exception.class)
    public G globalException(Exception e) {

        logger.error(e.getMessage(), e);

        return G.error();
    }
}