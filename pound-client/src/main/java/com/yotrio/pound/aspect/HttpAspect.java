package com.yotrio.pound.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * ${DESCRIPTION}
 * 模块名称：study-boot.com.wangyq.aspect
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-03 上午11:09
 * 系统版本：1.0.0
 **/
@Aspect
@Component
public class HttpAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 定义切点，在哪里响应
     */
    @Pointcut("execution(public * com.yotrio.pound.controller.TaskController.*(..))")
    public void log() {

    }

    /**
     * 方法执行之前指向
     *
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("log={doBefore}");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = requestAttributes.getRequest();

        //url
        String url = String.valueOf(servletRequest.getRequestURL());
        logger.info("url={}", url);
        //method
        String method = servletRequest.getMethod();
        logger.info("method={}", method);
        //ip
        String ip = servletRequest.getRemoteAddr();
        logger.info("ip={}", ip);
        //类方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        logger.info("classMethod={}", classMethod);
        //参数
        Object[] args = joinPoint.getArgs();
        logger.info("arg={}", args);
    }

    /**
     * 方法执行之后执行
     *
     * @param joinPoint
     */
    @After("log()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("log={doAfter}");
    }

    /**
     * 获取执行结果
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object);
    }
}