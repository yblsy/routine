package personal.loginapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import personal.loginapp.entity.BaseUsers;
import personal.loginapp.service.BaseUserService;

import java.util.Date;

/**
 * @author 刘晨
 * @create 2018-04-08 21:51
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/

@Controller
@RequestMapping(value = "/user/register")
public class RegisterController extends BaseController{

    @Autowired
    private BaseUserService baseUserService;

    @RequestMapping(value = "/toDo")
    public String toRegister(){
        return "common/register";
    }

    @RequestMapping(value = "/do",method = RequestMethod.POST)
    public String reg(BaseUsers baseUsers){
        baseUserService.addBaseUsers(baseUsers);
        return "common/register";
    }
}
