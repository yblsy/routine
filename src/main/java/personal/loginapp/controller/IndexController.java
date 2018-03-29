package personal.loginapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.loginapp.entity.BaseEntity;
import personal.loginapp.service.IndexService;
import personal.loginapp.service.impl.IndexServiceImpl;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 刘晨
 * @create 2018-03-17 20:37
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Controller
@Slf4j
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/index",method = {RequestMethod.GET})
    public String toIndex(String loginname,String password){
        log.info("loginname="+loginname);
        log.info("password="+password);
        log.info("appInfo="+indexService.getAppInfo());
        for(Object[] objs : indexService.getInfo()){
            System.out.println(objs);
        }
        return "index";
    }

    @RequestMapping(value = "/indexHtml",method = {RequestMethod.GET})
    public String toIndexHtml(String loginname,String password){
        log.info("loginname="+loginname);
        log.info("password="+password);
        log.info("appInfo="+indexService.getAppInfo());
        return "indexH";
    }

    @RequestMapping(value = "/insertBase" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String insertBase(BaseEntity baseEntity){
        baseEntity.setTempCol2(new Timestamp(new Date().getTime()));
        return ""+indexService.insertBase(baseEntity);
    }

    @RequestMapping(value = "/abc",produces = "application/json;charset=utf-8")
    public String getUserList(){
        return null;
    }

}
