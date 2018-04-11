package personal.loginapp.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import personal.loginapp.service.impl.IndexServiceImpl;

/**
 * @author 刘晨
 * @create 2018-03-19 21:25
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Service
@Slf4j
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    //@Autowired
    //private IndexServiceImpl indexService;

//    @PostConstruct
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
}
