package com.alert.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.utils
 * User: zjprevenge
 * Date: 2016/11/7
 * Time: 11:25
 */
public class BeanUtils {
    private static final Logger log = LoggerFactory.getLogger(BeanUtils.class);

    public static Object fieldValue(Object bean, String fieldName) throws Exception {
        Field field = bean.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Object result = field.get(bean);
        field.setAccessible(false);
        return result;
    }

    public static double fieldDoubleValue(Object bean, String fieldName) throws Exception {
        Field field = bean.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        double result = field.getDouble(bean);
        field.setAccessible(false);
        return result;
    }

}
