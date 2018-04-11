package personal.loginapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.commons.utils.MD5Utils;
import personal.commons.utils.PkUtils;
import personal.loginapp.entity.BaseUsers;
import personal.loginapp.inner.InnerBaseUsersComponent;
import personal.loginapp.mapper.BaseUsersMapper;
import personal.loginapp.service.BaseUserService;

/**
 * @author 刘晨
 * @create 2018-04-09 21:21
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Service
public class BaseUserServiceImpl implements BaseUserService{

    @Autowired
    private InnerBaseUsersComponent innerBaseUsersComponent;

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public BaseUsers addBaseUsers(BaseUsers baseUsers) {
        if(innerBaseUsersComponent.selectBaseUsersByUserName(baseUsers.getUserName()).size() > 0){

        }
        innerBaseUsersComponent.addUser(baseUsers);
        return baseUsers;
    }
}
