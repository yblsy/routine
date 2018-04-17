package personal.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import personal.exception.exception.LoginAppException;
import personal.commons.model.LoginAppResult;

/**
 * @author 刘晨
 * @create 2018-04-13 14:42
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Component
@Aspect
@Slf4j
public class LoginAppAspect {

    @Pointcut(value = "execution(* personal.loginapp.controller..*.*(..)) && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void PointCut(){
        log.info("pointCut..................");
    }

    @Before("PointCut()")
    public void before(JoinPoint joinPoint){
        log.info("before...............");
    }

    @AfterReturning(pointcut = "PointCut()",returning = "result")
    public void afterReturn(JoinPoint joinPoint,Object result){
        log.info("afterReturn.......................");
    }

    @AfterThrowing(pointcut = "PointCut()",throwing = "loginAppException")
    public LoginAppResult exceptionCatchAdvice(LoginAppException loginAppException){
        log.info("AfterThrowing.......................");
        log.error(loginAppException.getErrorMsg());
        return LoginAppResult.error(loginAppException.getMessage());
    }

    @After("PointCut()")
    public void after(JoinPoint joinPoint){
        log.info("after.......................");
    }

}
