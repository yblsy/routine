package personal.loginapp.service; /**
 * Created by liuchen on 2018/3/20.
 */

import org.springframework.stereotype.Service;
import personal.loginapp.entity.BaseEntity;

import java.util.List;

/**
 * @author 刘晨
 * @create 2018-03-20 15:17
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public interface IndexService {

    List<Object[]> getInfo();

    String getAppInfo();

    int insertBase(BaseEntity baseEntity);
}
