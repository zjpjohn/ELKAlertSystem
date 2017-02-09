package com.alert.config;

import com.alert.rule.FieldRule;
import com.alert.domain.SenderEmail;
import com.alert.rule.RateRule;
import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Project: JuntuAlertSystem
 * Module Desc:com.juntu.alert.config
 * User: zjprevenge
 * Date: 2016/12/30
 * Time: 14:20
 */
@Component
public class RedissonCache {
    private static final Logger log= LoggerFactory.getLogger(RedissonCache.class);
    @Resource
    private Environment env;

    @Resource
    private RedissonClient redissonClient;

    public RMapCache<String, RateRule> rateRuleCache() {
        return redissonClient.getMapCache(env.getProperty("cache.rate"));
    }

    public RMapCache<String, FieldRule> fieldRuleCache() {
        return redissonClient.getMapCache(env.getProperty("cache.field"));
    }

    //频率计数器
    public RMap<String, Integer> rateMap() {
        RMap<String, Integer> map = redissonClient.getMap(env.getProperty("cache.count"));
        return map;
    }

    //时间记录
    public RMap<String, Long> timeMap() {
        log.info("cache.time:{}",env.getProperty("cache.time"));
        RMap<String, Long> map = redissonClient.getMap(env.getProperty("cache.time"));
        return map;
    }

    //分步式锁
    public RLock countLock() {
        return redissonClient.getLock(env.getProperty("cache.lock"));
    }

    //判断是否报警，解决疯狂报警的问题
    public RMap<String, Boolean> alertMap() {
        RMap<String, Boolean> map = redissonClient.getMap(env.getProperty("cache.alert"));
        return map;
    }

    public RMap<Integer, SenderEmail> unselectedSender() {
        return redissonClient.getMap("sender.unselected");
    }

    public RMap<Integer, SenderEmail> selectedSender() {
        return redissonClient.getMap("sender.selected");
    }

    public RAtomicLong senderTag() {
        return redissonClient.getAtomicLong("sender.tag");
    }
}
