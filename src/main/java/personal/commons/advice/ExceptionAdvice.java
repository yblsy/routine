package personal.commons.advice;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 刘晨
 * @create 2018-04-15 17:19
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@ControllerAdvice
public class ExceptionAdvice {

    @Autowired(required = false)
    private Gson gson;

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String oprNullPointException(NullPointerException ex){
        ex.printStackTrace();
        return null;
    }

}
