package com.alert.rule;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/12/2
 * Time: 10:57
 */
@Component
public class RateThresholdFilter implements RateFilter {

    //默认异常出现频率
    public static final Integer DEFAULT_RATE = 10;
    //默认连续五分钟出现相同异常进行报警
    public static final Integer DEFAULT_INTERVAL = 30;

    @Resource
    private RateRuleCacheService ruleCacheService;

    @Resource
    private DistributeRateCountMap distributeRateCountMap;

    @Override
    public boolean filter(Message message, String exception) {
            String business = message.getType();
            RateRule rateRule = ruleCacheService.get(business + ":" + exception);
            if (rateRule != null) {
                return distributeRateCountMap.countRate(message,
                        rateRule.getRateExpress(),
                        rateRule.getRateThresh(),
                        rateRule.getTimeInterval());
            } else {
                //默认不添加频率规则，则使用默认规则
                return distributeRateCountMap.countRate(message,
                        rateRule.getRateExpress(),
                        DEFAULT_RATE,
                        DEFAULT_INTERVAL);
            }
    }
}
