package com.manas.learning.session.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
@EnableRedisHttpSession(redisNamespace = "${spring.session.redis.namespace}")
public class HttpSessionConfig extends AbstractHttpSessionApplicationInitializer implements ApplicationListener {

    @Value("${spring.session.timeout}")
    private Integer maxInactiveIntervalInSeconds;

    @Value("${spring.redis.host}")
    private String host;


    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.pass}")
    private String pass;

    @Bean
    public LettuceConnectionFactory connectionFactory(){

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(pass);
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        return connectionFactory;
    }

    @Bean
    public ConfigureRedisAction configureRedisAction(){
        return ConfigureRedisAction.NO_OP;
    }


   @Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
		return CustomHeaderHttpSessionIdResolver.xAuthToken();
	}

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            RedisIndexedSessionRepository repository =((ContextRefreshedEvent) event).getApplicationContext().getBean("sessionRepository" , RedisIndexedSessionRepository.class);
            repository.setDefaultMaxInactiveInterval(maxInactiveIntervalInSeconds);
        }
    }

    /**
     * /**
     *  Uncomment connection factory if you would like to use Jedis instead Lettuce.
     *  Add corresponding dependency in pom
     * /
   @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }


     /**
     * Cookie serializer is suitable for a web application. You have to be careful about setting path and domain.
    *  If any API-Gateway or Reverse Proxy server used in between and context path is not same as your application then, client will not be able to read cookies
    *  hence will send null/empty value to backend and it will be treated as a new session every time.
     * /
	@Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID");
        serializer.setCookiePath("*");
        //serializer.setUseSecureCookie(true);
        //serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return serializer;
    }*/
   
   
	
	
}
