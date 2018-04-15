package personal.commons.handler;

import java.lang.annotation.*;

/**
 * @author 刘晨
 * @create 2018-04-15 21:51
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequestAnnotation {

    String value() default "";
}
