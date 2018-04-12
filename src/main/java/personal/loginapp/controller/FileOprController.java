package personal.loginapp.controller;/**
 * Created by liuchen on 2018/4/12.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘晨
 * @create 2018-04-12 16:15
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Controller
@RequestMapping(value = "/foc/")
public class FileOprController {

    @RequestMapping(value = "fileUpload",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String fileUpload(HttpServletRequest request){
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        return null;
    }
}
