package com.seed.project.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.seed.project.aop.annotation.LogAnnotation;
import com.seed.project.contants.Constant;
import com.seed.project.entity.SysLog;
import com.seed.project.mapper.SysLogMapper;
import com.seed.project.utils.HttpContextUtils;
import com.seed.project.utils.IPUtils;
import com.seed.project.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: SysLogAspect
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {
    //环绕增强
    @Autowired
    private SysLogMapper sysLogMapper;
    /**
     * 配置织入点(以@MyLog注解为标志)
     * 只要出现 @MyLog注解都会进入
     */
    @Pointcut("@annotation(com.seed.project.aop.annotation.LogAnnotation)")
    public void logPointCut(){

    }

    /**
     * 环绕增强
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        try {
            saveSysLog(point, time);
        } catch (Exception e) {
            log.error("e={}",e);
        }

        return result;
    }
    /**
     * 把日志保存
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param joinPoint
     * @param time
     * @return       void
     * @throws
     */
    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        if(logAnnotation != null){
            //注解上的描述
            sysLog.setOperation(logAnnotation.title()+"-"+ logAnnotation.action());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //打印该方法耗时时间
        log.info("请求{}.{}耗时{}毫秒",className,methodName,time);
        try {
            //请求的参数
            Object[] args = joinPoint.getArgs();
            String params=null;
            if(args.length!=0){
                params= JSON.toJSONString(args);
            }

            sysLog.setParams(params);
        } catch (Exception e) {

        }
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        log.info("Ip{}，接口地址{}，请求方式{}，入参：{}",sysLog.getIp(),request.getRequestURL(),request.getMethod(),sysLog.getParams());
        //用户名
        String  token = request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(token);
        String username= JwtTokenUtil.getUserName(token);
        sysLog.setUsername(username);
        sysLog.setUserId(userId);
        sysLog.setTime((int) time);
        sysLog.setId(UUID.randomUUID().toString());
        sysLog.setCreateTime(new Date());
        log.info(sysLog.toString());
        sysLogMapper.insertSelective(sysLog);

    }
}
