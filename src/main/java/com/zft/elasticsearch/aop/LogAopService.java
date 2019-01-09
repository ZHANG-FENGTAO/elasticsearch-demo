package com.zft.elasticsearch.aop;

import com.zft.elasticsearch.entity.RespBean;
import com.zft.elasticsearch.util.AopLogUtils;
import com.zft.elasticsearch.util.MethodDesc;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author zft
 * @date 2019/1/9.
 */
@Service
@Slf4j
@Aspect
public class LogAopService {

    @Pointcut("execution(public * com.zft.elasticsearch.controller.*.*(..))")
    public void pointCut() {
    }

    @Pointcut("@annotation(com.zft.elasticsearch.util.MethodDesc))")
    public void pointCutOfAnnotation() {
    }

    private void logBefore(String classDesc, String methodDesc, Object[] args, String ip) {
        log.info("start invoke elastic {} - {}, params: {}, caller ip: {}", classDesc, methodDesc,
                AopLogUtils.toJson(args), ip);
    }

    private void logAfter(String classDesc, String methodDesc, Object result, long time) {
        log.info("elastic {} - {} invoke finished, result：{}. time：{}ms", classDesc, methodDesc,
                AopLogUtils.toJson(result), time);
    }

    @Around("pointCut() || pointCutOfAnnotation()")
    public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();

        Class<?> targetService = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String classDesc = AopLogUtils.getClassDesc(targetService);
        Method method = signature.getMethod();
        String methodDesc = AopLogUtils.getMethodDesc(targetService, method);
        MethodDesc methodAnnotation = method.getAnnotation(MethodDesc.class);
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            Object[] args = joinPoint.getArgs();
            this.logBefore(classDesc, methodDesc, args, ip);
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("service internal error. ", throwable);
            result = RespBean.error("invoke error");
        } finally {
            long end = System.currentTimeMillis();
            if (methodAnnotation == null || methodAnnotation.enableLogResult()) {
                this.logAfter(classDesc, methodDesc, result, (end - start));
            }
        }
        return result;
    }

}
