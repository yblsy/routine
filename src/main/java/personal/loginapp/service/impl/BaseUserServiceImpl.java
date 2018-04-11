package personal.loginapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.commons.utils.MD5Utils;
import personal.commons.utils.PkUtils;
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
        baseUsers.setUserId(PkUtils.pk4BaseUsers());
        //进行密码加密
        baseUsers.setPassWord(MD5Utils.MD5(baseUsers.getPassWord()));
        baseUsersMapper.insert(baseUsers);
        if(baseUsers.getUserName().equals("liuchen")){
            baseUsers.setUserName(null);
            baseUsers.getUserName().toString();
        }
        return baseUsers;
    }
}
