package personal.loginapp.model;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘晨
 * @create 2018-04-13 13:45
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class LoginAppResult extends BaseResult {

    @Getter
    enum LoginAppResultConstant{

        LA_RC_SUCCESS("00","成功"),
        LA_RC_FAILED("01","失败")
        ;

        private String code;
        private String message;

        LoginAppResultConstant(String code,String message){
            this.code = code;
            this.message = message;
        }
    }

    public LoginAppResult(LoginAppResultConstant loginAppResultConstant, Object data) {
        super(loginAppResultConstant.getCode(), loginAppResultConstant.getMessage(), data);
    }

    public static LoginAppResult success(){
        return success(null, null);
    }


    public static LoginAppResult success(Object data, String message){
        LoginAppResult loginAppResult = new LoginAppResult(LoginAppResultConstant.LA_RC_SUCCESS, data);
        if(StringUtils.isNotBlank(message)){
            loginAppResult.setMessage(message);
        }
        return loginAppResult;
    }

    public static LoginAppResult error(String message){
        LoginAppResult loginAppResult = new LoginAppResult(LoginAppResultConstant.LA_RC_FAILED, null);
        if(StringUtils.isNotBlank(message)){
            loginAppResult.setMessage(message);
        }
        return loginAppResult;
    }

    public static LoginAppResult error(Object data, String message){
        LoginAppResult loginAppResult = new LoginAppResult(LoginAppResultConstant.LA_RC_FAILED, data);
        if(StringUtils.isNotBlank(message)){
            loginAppResult.setMessage(message);
        }
        return loginAppResult;
    }
}