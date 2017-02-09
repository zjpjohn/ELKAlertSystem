package com.alert.rule;

import com.alert.config.RedissonCache;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/11/7
 * Time: 10:00
 */
@Component
public class FieldRuleCacheService {

    @Resource
    private RedissonCache redissonCache;


    /**
     * 获取缓存值
     *
     * @param key
     * @return
     */
    public FieldRule get(String key) {
        return redissonCache.fieldRuleCache().get(key);
    }

    /**
     * 添加缓存
     *
     * @param key
     * @param rule
     * @return
     */
    public FieldRule set(String key, FieldRule rule) {
        Preconditions.checkArgument(StringUtils.isNotBlank(key), "key must not be empty...");
        Preconditions.checkNotNull(rule);
        return redissonCache.fieldRuleCache().put(key, rule);
    }

    /**
     * 装在所有过滤规则
     *
     * @param allRule 所有过滤规则集合
     */
    public void putAll(Map<String, FieldRule> allRule) {
        Preconditions.checkArgument(allRule != null && !allRule.isEmpty(), "allRule must not be empty...");
        redissonCache.fieldRuleCache().putAll(allRule);
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    public FieldRule invalidate(String key) {
        return redissonCache.fieldRuleCache().remove(key);
    }

    public Integer size() {
        return redissonCache.fieldRuleCache().size();
    }

    public void invalidateAll() {
        redissonCache.fieldRuleCache().clear();
    }

}
