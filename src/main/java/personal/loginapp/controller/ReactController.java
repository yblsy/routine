package personal.loginapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 刘晨
 * @create 2018-03-24 16:27
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@RequestMapping(value = "/routine/react/")
@Controller
public class ReactController {

    @RequestMapping("toReact/{route}")
    public String toReactHtml(@PathVariable(value = "route")String route){
        return "react/"+route;
    }
}
