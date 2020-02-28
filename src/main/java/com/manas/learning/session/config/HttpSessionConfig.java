package com.manas.learning.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig extends AbstractHttpSessionApplicationInitializer {

   @Bean
    public LettuceConnectionFactory connectionFactory(){
        return new LettuceConnectionFactory();
    }


   @Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
		return CustomHeaderHttpSessionIdResolver.xAuthToken();
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
