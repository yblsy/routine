package personal.loginapp.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import personal.loginapp.entity.BaseEntity;
import personal.loginapp.mapper.BaseMapper;
import personal.loginapp.service.IndexService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘晨
 * @create 2018-03-18 19:18
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Service
public class IndexServiceImpl implements IndexService{

    @Autowired(required = false)
    private BaseMapper baseMapper;

    @Value("${app-info}")
    private String appInfo;

    public String getAppInfo() {
        return appInfo;
    }

    public List<Object[]> getInfo(){
        return Lists.newArrayList();
    }

    public int insertBase(BaseEntity baseEntity){
        return baseMapper.insertBase(baseEntity);
    }
}
