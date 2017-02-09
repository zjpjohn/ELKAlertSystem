package com.alert.controller;

import com.alert.domain.BusinessLine;
import com.alert.service.BusinessLineService;
import com.alert.vo.JsonResult;
import org.apache.commons.lang3.StringUtils;
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

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.controller
 * User: zjprevenge
 * Date: 2016/12/6
 * Time: 17:01
 */
@Controller
@RequestMapping("/business")
public class BusinessLineController {

    private static final Logger log = LoggerFactory.getLogger(BusinessLineController.class);

    @Resource
    private BusinessLineService businessLineService;

    /**
     * 查询全部业务线
     *
     * @return
     */
    @RequestMapping("/queryAll")
    public ModelAndView queryBusinessLines() {
        ModelAndView view = new ModelAndView("business_lines");
        try {
            List<BusinessLine> businessLines = businessLineService.getBusinessLineAll();
            view.addObject("code", 200);
            view.addObject("businessLines", businessLines);
        } catch (Exception e) {
            log.error("加载业务线数据出错：{}", e);
            view.addObject("code", 500);
            view.addObject("result", "加载数据出错咯！");
        }
        return view;
    }


    @RequestMapping("/edit/{id}")
    public ModelAndView editBusinessLine(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("edit_business");
        try {
            BusinessLine businessLine = businessLineService.getBusinessLineById(id);
            view.addObject("code", 200);
            view.addObject("businessLine", businessLine);
        } catch (Exception e) {
            log.error("加载数据出错：{}", e);
            view.addObject("code", 500);
            view.addObject("result", "加载数据出错咯");
        }
        return view;
    }

    @RequestMapping("/select")
    @ResponseBody
    public JsonResult<List<BusinessLine>> businessLineSelect() {
        try {
            List<BusinessLine> businessLines = businessLineService.getBusinessLineAll();
            return JsonResult.success(null).setData(businessLines);
        } catch (Exception e) {
            log.error("查询业务线出错：{}", e);
        }
        return JsonResult.failure("查询业错咯");
    }


    /**
     * 查询指定业务线
     *
     * @param id
     * @return
     */
    @RequestMapping("/query/{id}")
    @ResponseBody
    public JsonResult queryBusinessLine(@PathVariable("id") Integer id) {
        try {
            return JsonResult.success(null).setData(businessLineService.getBusinessLineById(id));
        } catch (Exception e) {
            log.error("查询数据出错：{}", e);
        }
        return JsonResult.failure("查询出错咯");
    }

    /**
     * 添加业务线
     *
     * @param businessName
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addBusinessLine(String businessName, String tag) {
        if (StringUtils.isBlank(businessName)
                || StringUtils.isBlank(tag)) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            BusinessLine businessLine = businessLineService.getBusinessLineByName(businessName);
            if (businessLine != null) {
                return JsonResult.failure("业务线已经存在");
            }
            businessLine = new BusinessLine();
            businessLine.setBusinessName(businessName);
            businessLine.setTag(tag);
            businessLineService.addBusinessLine(businessLine);
            return JsonResult.success("添加业务线成功");
        } catch (Exception e) {
            log.error("添加业务线失败：{}", e);
        }
        return JsonResult.failure("添加业务线失败");
    }

    /**
     * 更新业务线
     *
     * @param id           业务线id
     * @param businessName 业务线名称
     * @return
     */
    @RequestMapping("/update/{id}/{businessName}")
    @ResponseBody
    public JsonResult updateBusinessLine(@PathVariable Integer id,
                                         @PathVariable String businessName) {
        JsonResult result;
        try {
            if (id != null && StringUtils.isNoneBlank(businessName)) {
                BusinessLine businessLine = businessLineService.getBusinessLineById(id);
                if (businessLine != null) {
                    businessLine.setBusinessName(businessName);
                    businessLineService.updateBusinessLine(businessLine);
                }
                result = JsonResult.success("更新业务线成功").setData("/business/queryAll");
            } else {
                result = JsonResult.failure("参数不允许为空");
            }
        } catch (Exception e) {
            log.error("更新业务线失败：{}", e);
            result = JsonResult.failure("更新业务线失败");
        }
        return result;
    }

    /**
     * 删除指定业务线
     *
     * @param id 业务线id
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public JsonResult deleteBusinessLine(@PathVariable Integer id) {
        try {
            businessLineService.deleteBusinessLine(id);
            return JsonResult.success("删除业务线成功");
        } catch (Exception e) {
            log.error("删除业务线失败:{}", e);
        }
        return JsonResult.failure("删除业务线失败");
    }

    @RequestMapping("/active/{id}")
    @ResponseBody
    public JsonResult activeBusinessLine(@PathVariable Integer id) {
        try {
            businessLineService.activeBusinessLine(id);
            return JsonResult.success("激活业务线成功");
        } catch (Exception e) {
            log.error("激活业务线失败：{}", e);
        }
        return JsonResult.failure("激活业务线失败");
    }
}
