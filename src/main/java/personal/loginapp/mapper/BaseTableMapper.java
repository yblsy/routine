package personal.loginapp.mapper;
/**
 * Created by liuchen on 2018/3/20.
 */

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.maven.model.Model;
import personal.loginapp.entity.BaseEntity;

/**
 * @author 刘晨
 * @create 2018-03-20 14:02
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/

public interface BaseTableMapper extends BaseMapper<BaseEntity> {

    Integer insertBase(BaseEntity baseEntity);
}
