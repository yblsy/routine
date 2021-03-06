package personal.loginapp.service;

import personal.loginapp.entity.BaseUsers;

/**
 * @author 刘晨
 * @create 2018-04-09 21:21
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public interface BaseUserService {

    /**
     * 只供注册使用
     * @param baseUsers
     * @return
     */
    BaseUsers addBaseUsers(BaseUsers baseUsers);

    BaseUsers loginBaseUsers(BaseUsers baseUsers);

    BaseUsers resetPassword(BaseUsers baseUsers);
}
