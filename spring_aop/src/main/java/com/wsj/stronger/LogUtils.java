package com.wsj.stronger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @Author jiahao
 * @Date 2020/11/28 10:52
 */
@Component
@Aspect
public class LogUtils {

    @Pointcut("execution(public void com.wsj.stronger.DogBean.*(..))")
    public void pt1(){

    }

    @Before("pt1()")
    public void before(){
        System.out.println("执行之前++++++++");
    }
    @After("pt1()")
    public void after(){
        System.out.println("执行之后++++++++");
    }
    @Around("pt1()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object proceed = null;
        try {
            System.out.println("环绕开始+++++++++++++");
            proceed = proceedingJoinPoint.proceed();
            System.out.println("环绕结束+++++++++++++");

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;

    }
}
