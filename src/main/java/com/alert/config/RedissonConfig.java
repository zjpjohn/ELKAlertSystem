package com.alert.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * Project: JuntuAlertSystem
 * Module Desc:com.juntu.alert.config
 * User: zjprevenge
 * Date: 2016/12/30
 * Time: 11:03
 */
@Configuration
@PropertySource(value = {"classpath:redis.properties","classpath:cache.properties"}, ignoreResourceNotFound = true)
public class RedissonConfig {

    @Resource
    private Environment env;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.setNettyThreads(2);
        config.useSingleServer().setAddress(env.getProperty("redis.uri"));
        config.useSingleServer().setConnectionPoolSize(env.getProperty("redis.maxTotal", Integer.class));
        config.useSingleServer().setConnectionMinimumIdleSize(env.getProperty("redis.minIdle", Integer.class));
        config.useSingleServer().setConnectTimeout(env.getProperty("redis.maxWaitMillis", Integer.class));
        config.useSingleServer().setDatabase(env.getProperty("redis.database", Integer.class));
        String password = env.getProperty("redis.password");
        if (StringUtils.isNotBlank(password)) {
            config.useSingleServer().setPassword(password);
        }
        return Redisson.create(config);
    }
}
