package com.example.spring.domain.annotation.aop;

import com.example.spring.domain.returnMsg.excetion.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author: Staro
 * @date: 2020/5/27 15:07
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class TokenAspect {

    @Pointcut("@annotation(com.example.spring.domain.annotation.MpkScanner)")
    public void annotationPointcut(){
    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
    }

    @Around("annotationPointcut()")
    public void doAround(ProceedingJoinPoint joinPoint)throws GlobalException {
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
//        Token token = method.getAnnotation(Token.class);
        Annotation[][] annotations = method.getParameterAnnotations();

    }
}
