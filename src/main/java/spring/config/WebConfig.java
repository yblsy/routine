package spring.config;

import com.google.gson.Gson;
import org.apache.ibatis.annotations.Mapper;
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
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author 刘晨
 * @create 2018-03-17 20:33
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"personal.loginapp", "personal.commons"})
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

    /**
     * 配置mybatis工厂类
     * @param dataSource
     * @return
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("personal.loginapploginapp.entity");
        //sqlSessionFactoryBean.setTypeAliasesSuperType(BaseEntity.class);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("/sqlMapperXml/*Mapper.xml"));
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("/mybatis/mybatis-config.xml"));
        return sqlSessionFactoryBean;
    }

    /**
     * 配置mybatis mapper扫描接口
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
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
