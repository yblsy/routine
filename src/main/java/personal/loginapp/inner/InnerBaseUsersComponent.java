package personal.loginapp.inner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import personal.loginapp.entity.BaseUsers;
import personal.loginapp.mapper.BaseUsersMapper;
import personal.tools.MD5Utils;
import personal.tools.PkUtils;

import java.util.List;

/**
 * @author 刘晨
 * @create 2018-04-11 21:40
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Component
public class InnerBaseUsersComponent {

    @Autowired(required = false)
    private BaseUsersMapper baseUsersMapper;

    /**
     * 根据用户名查询用户列表
     * @param username
     * @return
     */
    public List<BaseUsers> selectBaseUsersByUserName(String username){
        EntityWrapper<BaseUsers> entityWrapper = new EntityWrapper<BaseUsers>();
        entityWrapper.eq("user_name",username);
        return baseUsersMapper.selectList(entityWrapper);
    }

    /**
     * 新增用户
     * @param baseUsers
     * @return
     */
    public String addUser(BaseUsers baseUsers){
        baseUsers.setUserId(PkUtils.pk4BaseUsers());
        //进行密码加密
        baseUsers.setPassWord(MD5Utils.MD5(baseUsers.getPassWord()));
        baseUsersMapper.insert(baseUsers);
        return baseUsers.getUserId();
    }
}
