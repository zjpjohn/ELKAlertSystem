package com.alert.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.annotation.Resource;

/**
 * Project: JuntuAlertSystem
 * Module Desc:com.juntu.alert.config
 * User: zjprevenge
 * Date: 2016/12/30
 * Time: 11:04
 */
@PropertySource({"classpath:session.properties"})
@EnableRedisHttpSession
public class SessionConfig {

    @Resource
    private Environment env;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setDatabase(env.getProperty("redis.database", Integer.class));
        connectionFactory.setHostName(env.getProperty("redis.host"));
        connectionFactory.setPort(env.getProperty("redis.port", Integer.class));
        String password = env.getProperty("redis.password");
        if (StringUtils.isNotBlank(password)) {
            connectionFactory.setPassword(password);
        }
        return connectionFactory;
    }
}
