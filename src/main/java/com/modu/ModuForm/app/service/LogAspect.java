package com.modu.ModuForm.app.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Around("@annotation(PerfLog)")
    public Object logRegister(ProceedingJoinPoint pjp) throws Throwable {
        String s = Arrays.stream(pjp.getArgs()).filter(arg -> arg.getClass() == String.class).findFirst().toString();
        Object retVal = pjp.proceed();
        log.info("Business Logic : " + pjp.getSignature());
        return retVal;
    }

//    @AfterThrowing(
//            pointcut = "execution(* com.modu.ModuForm.app.service.*())",
//            throwing = "exception"
//    )
//    public void logException(Exception exception) {
//      log.error("Exception : " + exception);
//    }
}
