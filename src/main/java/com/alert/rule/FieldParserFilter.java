package com.alert.rule;

import com.alert.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/12/2
 * Time: 11:31
 */
@Component
public class FieldParserFilter implements RuleFilter {

    private static final Logger log= LoggerFactory.getLogger(FieldParserFilter.class);

    public static final String FIELD_EXPRESS = "content";

    public static final String NGINX_REQUEST_TIME = "request_time";

    public static final String NGINX_REQUEST = "request";

    @Resource
    private FieldRuleCacheService ruleCacheService;

    @Resource
    private RateThresholdFilter rateThresholdFilter;

    @Override
    public boolean filter(Message message) throws Exception {
        if (message != null) {
            String business = message.getType();
            //获取字段过滤规则
            FieldRule fieldRule = ruleCacheService.get(business);
            //业务日志
            if (message.getTag().equals(RuleType.BUSINESS.getName())) {
                BusinessMessage businessMessage = (BusinessMessage) message;
                String content = (String) BeanUtils.fieldValue(businessMessage, FIELD_EXPRESS);
                if (fieldRule != null && content != null) {
                    for (String express : fieldRule.getFilters()) {
                        if (content.contains(express)) {
                            return rateThresholdFilter.filter(businessMessage, express);
                        }
                    }
                }
            } else if (message.getTag().equals(RuleType.NGINX.getName())) {//nginx日志
                NginxMessage nginxMessage = (NginxMessage) message;
                //获取请求URL
                String request = (String) BeanUtils.fieldValue(nginxMessage, NGINX_REQUEST);
                //获取请求时间
                double requestTime = BeanUtils.fieldDoubleValue(nginxMessage, NGINX_REQUEST_TIME);
                for (String filter : fieldRule.getFilters()) {
                    String[] split = filter.split("=");
                    //如果请求URL超时，进行报警
                    if (request.contains(split[0])
                            && (requestTime >= Double.valueOf(split[1]))) {
                        return rateThresholdFilter.filter(nginxMessage, split[0]);
                    }
                }
            }
        }
        return false;
    }
}
