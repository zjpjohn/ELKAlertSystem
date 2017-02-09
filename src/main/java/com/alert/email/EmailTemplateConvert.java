package com.alert.email;

import com.alert.domain.EmailTemplate;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.email
 * User: zjprevenge
 * Date: 2016/12/5
 * Time: 10:54
 */
public class EmailTemplateConvert {

    /**
     * 将消息的内容填充到模板中
     *
     * @param template 模板
     * @param model    消息
     * @return
     */
    public static String convert(EmailTemplate template, Map<String, Object> model) throws Exception {
        //创建freemarker模板
        Template freemarker = new Template(template.getTemplateName(), new StringReader(template.getTemplateValue()), new Configuration());
        //创建转换结果
        StringWriter result = new StringWriter();
        //进行转换处理
        freemarker.process(model, result);
        return result.toString();
    }

    /**
     * freemarker模板转换器
     *
     * @param template
     * @param model
     * @return
     * @throws Exception
     */
    public static String freemarkerConvert(EmailTemplate template, Map<String, String> model) throws Exception {
        //freemarker配置器
        Configuration configuration = new Configuration();
        //字符串模板加载器
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        //设置字符串模板
        stringLoader.putTemplate(template.getTemplateName(), template.getTemplateValue());
        configuration.setTemplateLoader(stringLoader);
        //获取模板
        Template temp = configuration.getTemplate(template.getTemplateName(), "utf-8");
        StringWriter writer = new StringWriter();
        //模板处理
        temp.process(model, writer);
        return writer.toString();
    }
}
