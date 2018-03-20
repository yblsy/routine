package personal.loginapp.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import personal.loginapp.service.IndexService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 刘晨
 * @create 2018-03-19 21:25
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Service
@Slf4j
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private IndexService indexService;

//    @PostConstruct
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        if(contextRefreshedEvent.getApplicationContext().getParent() == null){
            for(Object[] objs : indexService.getInfo()){
                for (Object obj : objs){
                    if(obj instanceof String){
                        log.info("String="+obj.toString());
                    }else if(obj instanceof Date){
                        log.info("Date="+(Date)obj);
                    }else if(obj instanceof Double){
                        log.info("Double="+(Double)obj);
                    }else if(obj instanceof Integer){
                        log.info("Integer="+(Integer)obj);
                    }else if(obj instanceof BigDecimal){
                        log.info("BigDecimal="+(BigDecimal)obj);
                    }
                }
            }
//        }
    }
}
