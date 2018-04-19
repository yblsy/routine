package personal.loginapp.controller;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.tools.RedisUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 刘晨
 * @create 2018-03-24 16:27
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@RequestMapping(value = "/routine/react/")
@Controller
public class ReactController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired(required = false)
    private Gson gson;

    @RequestMapping("toReact/{route}")
    public String toReactHtml(@PathVariable(value = "route")String route){
        return "react/"+route;
    }

    @RequestMapping("toIndexH")
    public String toIndexH(){
        return "indexH";
    }

    @RequestMapping("oprRedisSet")
    public String oprRedisSet(String key, String value){
        redisUtils.setRedis(key,value);
        return "indexH";
    }

    @RequestMapping(value = "oprRedisGet",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String oprRedisGet(@RequestParam(value = "key") String key){
        Map result = Maps.newHashMap();
        result.put("value",redisUtils.getRedisValue7ResetTime(key).toString());
        return gson.toJson(result);
    }
}
