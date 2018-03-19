package personal.loginapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 刘晨
 * @create 2018-03-18 19:18
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Service
public class IndexService {

    @Value("${app-info}")
    private String appInfo;

    public String getAppInfo() {
        return appInfo;
    }
}
