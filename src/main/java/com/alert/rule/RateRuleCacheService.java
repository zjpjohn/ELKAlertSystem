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
 * Date: 2016/12/2
 * Time: 11:43
 * <p>
 * 缓存频率过滤规则
 */
@Component
public class RateRuleCacheService {

    @Resource
    private RedissonCache redissonCache;

    /**
     * 获取缓存值
     *
     * @param key
     * @return
     */
    public RateRule get(String key) {
        return redissonCache.rateRuleCache().get(key);
    }

    /**
     * 添加缓存
     *
     * @param key
     * @param rule
     * @return
     */
    public RateRule set(String key, RateRule rule) {
        Preconditions.checkArgument(StringUtils.isNotBlank(key), "key must not be empty...");
        Preconditions.checkNotNull(rule);
        return redissonCache.rateRuleCache().put(key, rule);
    }

    /**
     * 装在所有过滤规则
     *
     * @param allRule 所有过滤规则集合
     */
    public void putAll(Map<String, RateRule> allRule) {
        Preconditions.checkArgument(allRule != null && !allRule.isEmpty(), "allRule must not be empty...");
        redissonCache.rateRuleCache().putAll(allRule);
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    public RateRule invalidate(String key) {
        return redissonCache.rateRuleCache().remove(key);
    }

    public Integer size() {
        return redissonCache.rateRuleCache().size();
    }

    public void invalidateAll() {
        redissonCache.rateRuleCache().clear();
    }
}
