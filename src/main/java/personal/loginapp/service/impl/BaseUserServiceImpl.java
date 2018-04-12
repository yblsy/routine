package personal.loginapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.enums.LoginAppEnum;
import personal.exception.LoginAppException;
import personal.loginapp.entity.BaseUsers;
import personal.loginapp.inner.InnerBaseUsersComponent;
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

    @Transactional(readOnly = false,rollbackFor = {LoginAppException.class})
    public BaseUsers addBaseUsers(BaseUsers baseUsers) {
        //已经存在的用户名
        if(innerBaseUsersComponent.selectBaseUsersByUserName(baseUsers.getUserName()).size() > 0){
            throw new LoginAppException(LoginAppEnum.REGISTER_ERROR_USER_EXIST.getErrorCode(),LoginAppEnum.REGISTER_ERROR_USER_EXIST.getErrorMsg());
        }
        innerBaseUsersComponent.addUser(baseUsers);
        return baseUsers;
    }

    @Transactional(readOnly = false,rollbackFor = {LoginAppException.class})
    public BaseUsers loginBaseUsers(BaseUsers baseUsers) {
        return null;
    }

    @Transactional(readOnly = false,rollbackFor = {LoginAppException.class})
    public BaseUsers resetPassword(BaseUsers baseUsers) {
        return null;
    }
}
