package personal.loginapp.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 刘晨
 * @create 2018-04-09 20:56
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@TableName("base_users")
@Getter
@Setter
public class BaseUsers implements Serializable{

    private static final long serialVersionUID = 7590503941191186096L;

    @TableField("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("pass_word")
    private String passWord;

    @TableField("nick_name")
    private String nickName;

    @TableField("user_img")
    private String userImg;

    protected Serializable pkVal() {
        return this.userId;
    }
}
