package com.ciwr.global.aspect;

import com.alibaba.fastjson.JSON;
import com.ciwr.global.annotation.AnnoSysLog;
import com.ciwr.global.utils.HttpContextUtils;
import com.ciwr.global.utils.IPUtils;
import com.ciwr.modle.sys.SysLog;
import com.ciwr.service.sys.SysLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private SysLogService sysLogService;

    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Pointcut("execution(* com.ciwr.controller..*.*(..)) && @annotation( com.ciwr.global.annotation.AnnoSysLog))")
    public void logPointCut() {
    }


    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //开始执行的时间
        long startTime = System.currentTimeMillis();
        //执行方法
        Object proceed = point.proceed();
        //执行时间
        long execTime = System.currentTimeMillis() - startTime;
        //保存日志
        saveLog(point, execTime);
        //返回结果
        return proceed;
    }

    /**
     * 保存日志
     */
    private void saveLog(ProceedingJoinPoint point, Long execTime) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog=new SysLog();
        sysLog.setId(null);
        AnnoSysLog annoSysLog = method.getAnnotation(AnnoSysLog.class);
        if(annoSysLog!=null){
            //备注
            sysLog.setRemark(annoSysLog.value());
        }
        //获取request对象
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //IP地址
        String ipAddr = IPUtils.getIpAddr(request);
        sysLog.setIpAddress(ipAddr);
        //执行时间
        sysLog.setTime(execTime);
        //请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setClazz(className);
        sysLog.setMethod(className+"."+methodName+"()");
        //请求的参数
        String param=null;
        try {
            param=JSON.toJSONString(point.getArgs());
        } catch (Exception e) {
        }
        sysLog.setParams(param);
        //请求的URI
        String requestURI = request.getRequestURI();
        sysLog.setOperation(requestURI);
        //保存日志
        sysLogService.insert(sysLog);
    }

}
