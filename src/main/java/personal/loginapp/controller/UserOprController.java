package personal.loginapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.loginapp.entity.BaseUsers;
import personal.loginapp.service.BaseUserService;

import java.util.Date;

/**
 * @author 刘晨
 * @create 2018-04-08 21:51
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/

@Controller
@RequestMapping(value = "/user/")
public class UserOprController extends BaseController{

    @Autowired
    private BaseUserService baseUserService;

    @RequestMapping(value = "toRegDo")
    public String toRegister(){
        return "common/register";
    }

    @RequestMapping(value = "doReg",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String reg(BaseUsers baseUsers){
        baseUserService.addBaseUsers(baseUsers);
        return "common/register";
    }

    @RequestMapping(value = "toLogDo")
    public String toLogDo(){
        return "common/login";
    }

    @RequestMapping(value = "doLog",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String log(BaseUsers baseUsers){

        return "common/login";
    }
}
