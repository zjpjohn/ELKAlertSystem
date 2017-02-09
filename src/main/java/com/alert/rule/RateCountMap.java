package com.alert.rule;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/12/1
 * Time: 15:03
 * <p>
 * 单台机器不存在问题
 * 存在问题：不能进行准确计数，导致告警信息不能准确发送
 * 如：设置阈值10/5，进行分布式一台机器消费了 5/5,另一台机器5/5 导致不能发送告警信息
 */
//@Component
public class RateCountMap {

    private static final Logger log = LoggerFactory.getLogger(RateCountMap.class);
    //频率
    private static Map<String, AtomicInteger> rateMap = Maps.newConcurrentMap();
    //时间
    private static Map<String, Long> timeMap = Maps.newConcurrentMap();

    /**
     * 统计消息频率
     *
     * @param message  消息
     * @param express  异常
     * @param rate     频率
     * @param interval 时间间隔
     * @return
     */
    public synchronized boolean countRate(Message message, String express, Integer rate, Integer interval) {
        String key = message.getType() + ":" + express;
        AtomicInteger rateCount = rateMap.get(key);
        //如果消息首次出现
        if (rateCount == null) {
            rateCount = new AtomicInteger(1);
            timeMap.put(key, System.currentTimeMillis());
            rateMap.put(key, rateCount);
            return false;
        }
        //统计次数增加
        int count = rateCount.getAndIncrement();
        //计算到达时间
        long arriveTime = System.currentTimeMillis();
        Long startTime = timeMap.get(key);

        //频率未达到阈值
        if (count < rate) {
            //未达到时间间隔
            if ((arriveTime - startTime) < interval * 1000) {
                rateMap.put(key, rateCount);
            } else {
                //已经达到时间间隔
                rateCount.set(1);
                rateMap.put(key, rateCount);
                timeMap.put(key, System.currentTimeMillis());
            }
            return false;
        } else {
            //在时间阈值内出现满足条件次数，进行报警
            if ((arriveTime - startTime) < interval * 1000) {
                rateMap.remove(key);
                timeMap.remove(key);
                return true;
            } else {//在时间阈值内未出现满足条件的次数，不进行报警
                rateCount.set(1);
                rateMap.put(key, rateCount);
                timeMap.put(key, System.currentTimeMillis());
                return false;
            }
        }
    }
}
