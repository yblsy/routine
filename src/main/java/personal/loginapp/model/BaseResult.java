package personal.loginapp.model;/**
 * Created by liuchen on 2018/4/13.
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 刘晨
 * @create 2018-04-13 13:43
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Getter
@Setter
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1767327487645930392L;
    public String code;
    public String message;
    private Object data;

    public BaseResult() {
    }

    public BaseResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}