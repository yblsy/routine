package personal.commons.handler;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import personal.loginapp.model.LoginAppResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 刘晨
 * @create 2018-04-15 21:44
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Slf4j
public class OprMethodReturnResultValueHandler implements HandlerMethodReturnValueHandler {

    public boolean supportsReturnType(MethodParameter methodParameter) {
        return methodParameter.getMethod().getReturnType() == LoginAppResult.class && methodParameter.getMethodAnnotation(LoginRequestAnnotation.class) != null;
    }

    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        modelAndViewContainer.setRequestHandled(true);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = null;
        Gson gson = new Gson();
        try {
            writer = response.getWriter();
            writer.write(gson.toJson((LoginAppResult) o));
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
