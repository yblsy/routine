package personal.loginapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import personal.annotation.BaseResultAnnotation;
import personal.loginapp.entity.BaseUsers;
import personal.commons.model.LoginAppResult;
import personal.loginapp.service.BaseUserService;

/**
 * @author 刘晨
 * @create 2018-04-08 21:51
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/

@Controller
@RequestMapping(value = "/user/")
@Slf4j
public class UserOprController extends BaseController{

    @Autowired
    private BaseUserService baseUserService;

    @RequestMapping(value = "toRegDo")
    public String toRegister(){
        return "common/register";
    }

    @RequestMapping(value = "doReg",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @BaseResultAnnotation
    public LoginAppResult reg(BaseUsers baseUsers){
        BaseUsers bu = baseUserService.addBaseUsers(baseUsers);
        return LoginAppResult.success(bu,"注册成功");
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
