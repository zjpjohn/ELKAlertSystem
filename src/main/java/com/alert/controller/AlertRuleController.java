package com.alert.controller;

import com.alert.domain.AlertFieldRule;
import com.alert.domain.AlertRateRule;
import com.alert.service.AlertRuleService;
import com.alert.vo.FieldRuleVo;
import com.alert.vo.RateRuleVo;
import com.alert.vo.RuleVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.alert.vo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.controller
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 12:39
 */
@Controller
@RequestMapping("/rule")
public class AlertRuleController {

    private static final Logger log = LoggerFactory.getLogger(AlertRuleController.class);

    private static final String RATE_TYPE = "rate";
    private static final String FIELD_TYPE = "field";

    @Resource
    private AlertRuleService alertRuleService;

    /**
     * 添加频率规则
     *
     * @param rateRuleVo
     * @return
     */
    @RequestMapping(value = "/addRateRule", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addRateRule(RateRuleVo rateRuleVo) {
        if (rateRuleVo == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            AlertRateRule alertRateRule = new AlertRateRule();
            alertRateRule.setRateThresh(rateRuleVo.getRateThresh());
            alertRateRule.setInterval(rateRuleVo.getInterval());
            alertRateRule.setRateExpress(rateRuleVo.getRateExpress());
            alertRateRule.setBusinessId(rateRuleVo.getBusinessId());
            alertRuleService.addRateRule(alertRateRule);
            return JsonResult.success("添加规则成功");
        } catch (Exception e) {
            log.error("add rate rule error:{}", e);
        }
        return JsonResult.failure("添加规则失败");
    }

    /**
     * 添加字段规则
     *
     * @param fieldRuleVo
     * @return
     */
    @RequestMapping(value = "/addFieldRule", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addFieldRule(FieldRuleVo fieldRuleVo) {
        if (fieldRuleVo == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            AlertFieldRule alertFieldRule = new AlertFieldRule();
            alertFieldRule.setBusinessId(fieldRuleVo.getBusinessId());
            alertFieldRule.setRuleName(fieldRuleVo.getRuleName());
            alertFieldRule.setRuleValue(fieldRuleVo.getRuleValue());
            alertRuleService.addFieldRule(alertFieldRule);
            return JsonResult.success("添加字段规则成功");
        } catch (Exception e) {
            log.error("add field Rule error：{}", e);
        }
        return JsonResult.failure("添加字段规则失败");
    }

    /**
     * 查询业务线的规则
     *
     * @param id
     * @return
     */
    @RequestMapping("/query/{id}")
    public JsonResult<RuleVo> queryRule(@PathVariable("id") Integer id) {
        if (id == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            RuleVo ruleVo = alertRuleService.queryRule(id);
            return JsonResult.success(null).setData(ruleVo);
        } catch (Exception e) {
            log.error("查询业务线的使用规则出错：{}", e);
        }
        return JsonResult.failure("查询出错咯");
    }

    @RequestMapping("/query")
    public ModelAndView queryRules() {
        ModelAndView view = new ModelAndView("alert_rule");
        try {
            List<RuleVo> ruleVos = alertRuleService.queryRules();
            view.addObject("code", 200);
            view.addObject("ruleVos", ruleVos);
        } catch (Exception e) {
            log.error("查询规则出错：{}", e);
            view.addObject("code", 500);
            view.addObject("result", "查询出错咯");
        }
        return view;
    }

    @RequestMapping("/update/{type}/{id}")
    @ResponseBody
    public JsonResult updateRulePage(@PathVariable("id") Integer id,
                                     @PathVariable("type") String type) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            if (RATE_TYPE.equals(type)) {
                AlertRateRule rateRule = alertRuleService.getRateRuleById(id);
                map.put("result", rateRule);
                map.put("type", RATE_TYPE);
            } else {
                AlertFieldRule fieldRule = alertRuleService.getFieldRuleById(id);
                map.put("result", fieldRule);
                map.put("type", FIELD_TYPE);
            }
            return JsonResult.success(null).setData(map);
        } catch (Exception e) {
            log.error("查询数据失败：{}", e);
        }
        return JsonResult.failure("加载数据失败");
    }

    @RequestMapping(value = "/updateFieldRule", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateFieldRule(FieldRuleVo fieldRuleVo) {
        if (fieldRuleVo == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            AlertFieldRule fieldRule = new AlertFieldRule();
            fieldRule.setId(fieldRuleVo.getId());
            fieldRule.setRuleValue(fieldRuleVo.getRuleValue());
            fieldRule.setBusinessId(fieldRuleVo.getBusinessId());
            alertRuleService.updateFieldRule(fieldRule);
            return JsonResult.success("更新规则成功");
        } catch (Exception e) {
            log.error("更新规则失败：{}", e);
        }
        return JsonResult.failure("更新规则失败");
    }

    @RequestMapping(value = "/updateRateRule", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateRateRule(RateRuleVo rateRuleVo) {
        if (rateRuleVo == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            AlertRateRule rateRule = new AlertRateRule();
            rateRule.setId(rateRuleVo.getId());
            rateRule.setBusinessName(rateRuleVo.getBusinessName());
            rateRule.setRateExpress(rateRuleVo.getRateExpress());
            rateRule.setInterval(rateRuleVo.getInterval());
            rateRule.setRateThresh(rateRuleVo.getRateThresh());
            alertRuleService.updateRateRule(rateRule);
            return JsonResult.success("更新规则成功");
        } catch (Exception e) {
            log.error("更新规则失败：{}", e);
        }
        return JsonResult.failure("更新规则失败");
    }

    /**
     * 删除页面跳转
     *
     * @param id 删除根据业务线来进行
     * @return
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView deletePage(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("edit_rule");
        try {
            AlertFieldRule fieldRule = alertRuleService.getFieldRuleByBusiness(id);
            List<AlertRateRule> rateRuleList = alertRuleService.getRateRuleByLine(id);
            RuleVo ruleVo = new RuleVo();
            //字段规则转换
            FieldRuleVo fieldRuleVo = new FieldRuleVo();
            ruleVo.setBusinessName(fieldRule.getBusinessName());
            fieldRuleVo.setBusinessName(fieldRule.getBusinessName());
            fieldRuleVo.setBusinessId(fieldRule.getBusinessId());
            fieldRuleVo.setRuleValue(fieldRule.getRuleValue());
            fieldRuleVo.setRuleName(fieldRule.getRuleName());
            fieldRuleVo.setId(fieldRule.getId());
            fieldRuleVo.setActive(fieldRule.getActive());
            ruleVo.setFieldRuleVo(fieldRuleVo);
            //频率规则转换
            List<RateRuleVo> ruleVos = Lists.transform(rateRuleList, new Function<AlertRateRule, RateRuleVo>() {
                @Override
                public RateRuleVo apply(AlertRateRule alertRateRule) {
                    RateRuleVo rateRuleVo = new RateRuleVo();
                    rateRuleVo.setBusinessName(alertRateRule.getBusinessName());
                    rateRuleVo.setBusinessId(alertRateRule.getBusinessId());
                    rateRuleVo.setRateThresh(alertRateRule.getRateThresh());
                    rateRuleVo.setRateExpress(alertRateRule.getRateExpress());
                    rateRuleVo.setInterval(alertRateRule.getInterval());
                    rateRuleVo.setId(alertRateRule.getId());
                    rateRuleVo.setActive(alertRateRule.getActive());
                    return rateRuleVo;
                }
            });
            ruleVo.setRateRuleVo(ruleVos);
            view.addObject("code", 200);
            view.addObject("ruleVo", ruleVo);
        } catch (Exception e) {
            log.error("加载数据出错咯");
            view.addObject("code", 500);
            view.addObject("result", "加载数据出错咯");
        }
        return view;
    }


    @RequestMapping("/deleteRule/{type}/{id}")
    @ResponseBody
    public JsonResult deleteFieldRule(@PathVariable("id") Integer id,
                                      @PathVariable("type") String type) {
        try {
            if (type.equals(FIELD_TYPE)) {
                alertRuleService.deleteFieldRule(id);
            } else {
                alertRuleService.deleteRateRule(id);
            }
            return JsonResult.success("删除规则成功");
        } catch (Exception e) {
            log.error("删除规则失败：{}", e);
        }
        return JsonResult.failure("删除规则失败");
    }

    /**
     * 激活规则
     *
     * @param id   规则id
     * @param type 规则类型
     * @return
     */
    @RequestMapping("/activeRule/{type}/{id}")
    @ResponseBody
    public JsonResult activeRule(@PathVariable("id") Integer id,
                                 @PathVariable("type") String type) {
        try {
            if (FIELD_TYPE.equals(type)) {
                alertRuleService.activeFieldRule(id);
            } else {
                alertRuleService.activeRateRule(id);
            }
            return JsonResult.success("激活规则成功");
        } catch (Exception e) {
            log.error("激活规则失败：{}", e);
        }
        return JsonResult.failure("激活股则出错");
    }
}
