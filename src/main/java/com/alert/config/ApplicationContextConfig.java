package com.alert.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Project: JuntuAlertSystem
 * Module Desc:com.juntu.alert.config
 * User: zjprevenge
 * Date: 2016/12/30
 * Time: 10:54
 */
@Configuration
@ComponentScan(basePackages = {"com.juntu.alert"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = {Controller.class, ControllerAdvice.class})})
@Import({KafkaConfig.class,
        MybatisConfig.class,
        RedissonConfig.class,
        SessionConfig.class,
        DisruptorQueueConfig.class})
public class ApplicationContextConfig {
}
