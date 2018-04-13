package personal.loginapp.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author 刘晨
 * @create 2018-04-08 21:51
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class BaseController {

    @Autowired(required = false)
    protected Gson gson;
}
