package spring.config;

import org.springframework.beans.factory.config.PreferencesPlaceholderConfigurer;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @author 刘晨
 * @create 2018-03-17 20:33
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"personal.loginapp"})
public class WebConfig extends WebMvcConfigurerAdapter{

    /**
     * 该类为视图解析器
     * @return
     */
    //
    // @Bean
    public ViewResolver internalResourceViewResolverJsp(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        //解析器的前缀
        resolver.setPrefix("/WEB-INF/views jsp/");
        //解析器的后缀
        resolver.setSuffix(".jsp");
        resolver.setExposePathVariables(true);
        resolver.setOrder(1);
        return resolver;
    }

    @Bean
    public ViewResolver internalResourceViewResolverHtml(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        //解析器的前缀
        resolver.setPrefix("/WEB-INF/views html/");
        //解析器的后缀
        resolver.setSuffix(".html");
        resolver.setExposePathVariables(true);
        resolver.setOrder(2);
        return resolver;
    }

    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }

    @Bean
    public PropertiesFactoryBean propertiesFactoryBean(){
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocations(new ClassPathResource("/conf/conf.properties"),new ClassPathResource("/conf/jdbc.properties"));
        return propertiesFactoryBean;
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(Properties propertiesFactoryBean){
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setProperties(propertiesFactoryBean);
        return propertyPlaceholderConfigurer;
    }

    /**
     * 配置静态资源的处理
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //通过调用enable方法，我们来要求DispatcherServlet将对静态资源的请求转发到Servlet容器中默认的Servlet上
        configurer.enable();
    }

}
