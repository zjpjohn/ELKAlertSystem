package com.alert.config;

import com.alert.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

/**
 * Project: JuntuAlertSystem
 * Module Desc:com.juntu.alert.config
 * User: zjprevenge
 * Date: 2016/12/30
 * Time: 10:52
 */
@Configuration
@ComponentScan(basePackages = {"com.juntu.alert.controller"})
@EnableWebMvc
public class WebMVCConfig extends WebMvcConfigurerAdapter {

    //创建登录拦截器
    @Bean
    public LoginInterceptor loginInterceptor() {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        return loginInterceptor;
    }

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor());
    }


    /**
     * 静态资源处理
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * freemarker配置
     *
     * @return
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        Properties props = new Properties();
        props.put("template_update_delay", "0");
        props.put("locale", "zh_CN");
        props.put("datetime_format", "yyyy-MM-dd HH:mm:ss");
        props.put("date_format", "yyyy-MM-dd");
        props.put("number_format", "#.##");
        props.put("classic_compatible", "true");
        freeMarkerConfigurer.setFreemarkerSettings(props);
        return freeMarkerConfigurer;
    }

    /**
     * freemarker视图配置
     *
     * @return
     */
    @Bean
    public FreeMarkerViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setRequestContextAttribute("request");
        viewResolver.setCache(false);
        viewResolver.setSuffix(".htm");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setViewClass(org.springframework.web.servlet.view.freemarker.FreeMarkerView.class);
        viewResolver().setOrder(0);
        return viewResolver;
    }

    /**
     * json 解决方案
     * 用于支持RequestBody 和ResponseBody
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter bean=new MappingJackson2HttpMessageConverter();
        return bean;
    }
    /**
     * 静态资源处理
     *
     * @param registry
     * @see ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("*.css").addResourceLocations("/css/**");
        registry.addResourceHandler("*.eot", "*.svg", "*.ttf", "*.woff", "*.woff2").addResourceLocations("/fonts/**");
        registry.addResourceHandler("*.jpg", "*.gif", "*.png").addResourceLocations("/img/**");
        registry.addResourceHandler("*.js").addResourceLocations("/js/**", "/scripts/**");
        registry.addResourceHandler("*.ico").addResourceLocations("/favicon.ico");
    }
}
