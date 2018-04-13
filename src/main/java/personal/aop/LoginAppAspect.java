package personal.aop;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import personal.exception.LoginAppException;
import personal.loginapp.model.LoginAppResult;

/**
 * @author 刘晨
 * @create 2018-04-13 14:42
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Component
@Aspect
@Slf4j
public class LoginAppAspect {

    @Autowired
    private Gson gson;

    @Pointcut(value = "execution(* personal.loginapp.controller.*(..)) && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void PointCut(){

    }

    @AfterThrowing(pointcut = "PointCut()",throwing = "loginAppException")
    public LoginAppResult exceptionCatchAdvice(LoginAppException loginAppException){
        log.error(loginAppException.getErrorMsg());
        return LoginAppResult.error(loginAppException.getMessage());
    }
}
