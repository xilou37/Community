package com.lf.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/8 0008 22:15
 * @Version 1.0
 */
//@Component
//@Aspect
public class AlphaAspect {
    @Pointcut("execution(* com.lf.community.service.*.*(..))")
    public void pointcut(){}
    
    @Before("pointcut()")
    public void before(){
        System.out.println("before");
    }
    @After("pointcut()")
    public void after(){
        System.out.println("after");
    }
    @AfterReturning("pointcut()")
    public void afterReturning(){
        System.out.println("afterReturning");
    }
    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        System.out.println("afterThrowing");
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around-before");
        Object obj = joinPoint.proceed();
        System.out.println("arount-after");
        return obj;
    }
}
