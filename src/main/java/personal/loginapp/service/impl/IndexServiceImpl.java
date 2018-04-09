package personal.loginapp.service.impl;

import com.google.common.collect.Lists;
import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import personal.loginapp.entity.BaseEntity;
import personal.loginapp.mapper.BaseTableMapper;
import personal.loginapp.service.IndexService;

import java.util.List;

/**
 * @author 刘晨
 * @create 2018-03-18 19:18
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class IndexServiceImpl implements IndexService{

    //@Autowired(required = false)
    private BaseTableMapper baseTableMapper;

    //@Value("${app-info}")
    private String appInfo;

    public String getAppInfo() {
        return appInfo;
    }

    public List<Object[]> getInfo(){
        return Lists.newArrayList();
    }

    public int insertBase(BaseEntity baseEntity){
        return baseTableMapper.insertBase(baseEntity);
    }
}
