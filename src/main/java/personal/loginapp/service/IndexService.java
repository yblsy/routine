package personal.loginapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
public class IndexService {

    @Autowired(required = false)
    private DataSource dataSource;

    @Value("${app-info}")
    private String appInfo;

    public String getAppInfo() {
        return appInfo;
    }

    public List<Object[]> getInfo(){
        if(dataSource == null){
            return new ArrayList<Object[]>();
        }
        List<Object[]> result = new ArrayList<Object[]>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement("select * from temp_tab");
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Object[] objs = new Object[4];
                objs[0] = rs.getObject(1);
                objs[1] = rs.getObject(2);
                objs[2] = rs.getObject(3);
                objs[3] = rs.getObject(4);
                result.add(objs);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                rs.close();
                preparedStatement.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
