package com.alert.rule;

import com.alert.domain.BusinessLine;
import com.alert.service.BusinessLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/11/7
 * Time: 10:58
 */
@Component
public class AlertRuleFilter implements RuleFilter {
    private static final Logger log = LoggerFactory.getLogger(AlertRuleFilter.class);

    @Resource
    private FieldParserFilter fieldParserFilter;

    @Resource
    private BusinessLineService businessLineService;

    public Message filterMessage(Message message) throws Exception {
        if (message != null) {
            String businessName = message.getType();
            BusinessLine businessLine = businessLineService.getBusinessLineByName(businessName);
            try {
                if (businessLine == null) {
                    log.warn("please config business line...");
                }
            } catch (Exception e) {
                log.error("添加业务线失败：{}", e);
            }
        }
        return filter(message) ? message : null;
    }

    @Override
    public boolean filter(Message message) throws Exception {
        return fieldParserFilter.filter(message);
    }
}
