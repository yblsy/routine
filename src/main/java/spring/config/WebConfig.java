package spring.config;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import personal.commons.handler.OprMethodReturnResultValueHandler;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author 刘晨
 * @create 2018-03-17 20:33
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"personal.loginapp", "personal.commons", "personal.aop"})
@ImportResource({"classpath:/spring/spring-mvc.xml"})
public class WebConfig extends WebMvcConfigurerAdapter{

    /**
     * 该类为视图解析器
     * @return
     */
    @Bean
    public ViewResolver internalResourceViewResolverHtml(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        //解析器的前缀
        resolver.setPrefix("/WEB-INF/views html/");
        //解析器的后缀
        resolver.setSuffix(".html");
        resolver.setExposePathVariables(true);
        resolver.setOrder(1);
        return resolver;
    }

    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }

    @Profile("myself")
    @Bean(name = "prop")
    public PropertiesFactoryBean propertiesFactoryBeanMyself(){
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocations(new ClassPathResource("/conf/conf.properties"),
                new ClassPathResource("/conf/jdbc-myself.properties"),
                new ClassPathResource("/conf/redis-myself.properties"));
        return propertiesFactoryBean;
    }

    @Profile("runtong")
    @Bean(name = "prop")
    public PropertiesFactoryBean propertiesFactoryBeanRuntong(){
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocations(new ClassPathResource("/conf/conf.properties"),
                new ClassPathResource("/conf/jdbc-runtong.properties"),
                new ClassPathResource("/conf/redis-runtong.properties"));
        return propertiesFactoryBean;
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(Properties prop){
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setProperties(prop);
        return propertyPlaceholderConfigurer;
    }

    @Bean
    public DataSource dataSource(Properties prop){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(prop.getProperty("jdbc.driver"));
        driverManagerDataSource.setUrl(prop.getProperty("jdbc.url"));
        driverManagerDataSource.setUsername(prop.getProperty("jdbc.username"));
        driverManagerDataSource.setPassword(prop.getProperty("jdbc.password"));
        return driverManagerDataSource;
    }

    /**
     * 配置事务管理
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

    @Bean
    public GlobalConfiguration globalConfiguration(){
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        globalConfiguration.setIdType(2);
        globalConfiguration.setDbType("mysql");
        globalConfiguration.setDbColumnUnderline(true);
        return globalConfiguration;
    }

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(DataSource dataSource,PaginationInterceptor paginationInterceptor,GlobalConfiguration globalConfiguration) throws IOException{
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("/sqlMapperXml/*Mapper.xml"));
        mybatisSqlSessionFactoryBean.setConfigLocation(new ClassPathResource("/mybatis/mybatis-config.xml"));
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage("personal.loginapploginapp.entity");
        mybatisSqlSessionFactoryBean.setPlugins(new Interceptor[]{paginationInterceptor});
        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfiguration);
        return mybatisSqlSessionFactoryBean;
    }

    /**
     * 配置mybatis mapper扫描接口
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("mybatisSqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage("personal.loginapp.mapper");
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        return mapperScannerConfigurer;
    }

    /**
     * 配置Redis
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig(Properties prop){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.parseInt(prop.getProperty("redis.maxIdle")));
        jedisPoolConfig.setMaxWaitMillis(Integer.parseInt(prop.getProperty("redis.maxWait")));
        jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(prop.getProperty("redis.testOnBorrow")));
        return jedisPoolConfig;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(Properties prop,JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        jedisConnectionFactory.setHostName(prop.getProperty("redis.hostname"));
        jedisConnectionFactory.setPort(Integer.parseInt(prop.getProperty("redis.port")));
        jedisConnectionFactory.setPassword(prop.getProperty("redis.password"));
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }

    @Bean
    public OprMethodReturnResultValueHandler oprMethodReturnResultValueHandler(){
        OprMethodReturnResultValueHandler oprMethodReturnResultValueHandler = new OprMethodReturnResultValueHandler();
        return oprMethodReturnResultValueHandler;
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(0,oprMethodReturnResultValueHandler());
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
