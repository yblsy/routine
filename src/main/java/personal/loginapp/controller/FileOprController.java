package personal.loginapp.controller;/**
 * Created by liuchen on 2018/4/12.
 */

import com.aliyun.oss.model.OSSObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import personal.tools.FtpUtils;
import personal.tools.OSSUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 刘晨
 * @create 2018-04-12 16:15
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Slf4j
@Controller
@RequestMapping(value = "/foc/")
public class FileOprController {

//    @Autowired(required = false)
//    private FtpUtils ftpUtils;

    @Autowired(required = false)
    private OSSUtils ossUtils;

//    @RequestMapping(value = "ftpUtils")
//    public void ftpUtils() throws IOException{
//        ftpUtils.instanceFtpClient();
//        ftpUtils.download("/home/ftp/","1.txt");
//    }

    @RequestMapping(value = "fileUpload",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String fileUpload(HttpServletRequest request) throws IOException{
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multiRequest.getFiles("source").get(0);
        ossUtils.ossSimpleUpload4MultiPartFile("test/liuchen2",multipartFile);
        return null;
    }

    @RequestMapping(value = "fileDown")
    public void fileDown(HttpServletResponse response){
        try{
            OSSObject ossObject = ossUtils.ossDownloadFile4Stream(null,"test/liuchen2");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            response.getOutputStream().write(IOUtils.toByteArray(ossObject.getObjectContent()));
            ossObject.getObjectContent().close();
        }catch (IOException e){
            log.error(e.getMessage());
        }finally {
            ossUtils.close();
        }
    }
}
