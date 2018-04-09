package personal.loginapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.loginapp.entity.BaseUsers;
import personal.loginapp.mapper.BaseUsersMapper;
import personal.loginapp.service.BaseUserService;

/**
 * @author 刘晨
 * @create 2018-04-09 21:21
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Service
public class BaseUserServiceImpl implements BaseUserService{

    @Autowired(required = false)
    private BaseUsersMapper baseUsersMapper;

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public BaseUsers addBaseUsers(BaseUsers baseUsers) {
        baseUsersMapper.insert(baseUsers);
        if(baseUsers.getUserName().equals("liuchen")){
            baseUsers.setUserName(null);
            baseUsers.getUserName().toString();
        }
        return baseUsers;
    }
}
