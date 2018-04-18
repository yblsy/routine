package personal.loginapp.controller;/**
 * Created by liuchen on 2018/4/12.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import personal.tools.FtpUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 刘晨
 * @create 2018-04-12 16:15
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Controller
@RequestMapping(value = "/foc/")
public class FileOprController {

    @Autowired(required = false)
    private FtpUtils ftpUtils;

    @RequestMapping(value = "ftpUtils")
    public void ftpUtils() throws IOException{
        ftpUtils.instanceFtpClient();
        ftpUtils.download("/home/ftp/","1.txt");
    }

    @RequestMapping(value = "fileUpload",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String fileUpload(HttpServletRequest request){
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        return null;
    }


    public static void main(String[] args) throws IOException{
        FtpUtils ftpUtils = new FtpUtils("47.100.100.163","root","Wlbrcjs1993",21);
        ftpUtils.instanceFtpClient();
    }
}
