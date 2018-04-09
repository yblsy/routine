package personal.loginapp.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author 刘晨
 * @create 2018-03-20 12:40
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Getter
@Setter
public class BaseEntity implements Serializable{

    private static final long serialVersionUID = 8350109295011593559L;

    private String tempCol1;

    private Timestamp tempCol2;

    private BigDecimal tempCol3;

    private Integer tempCol4;
}
