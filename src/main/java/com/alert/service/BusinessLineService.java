package com.alert.service;

import com.alert.domain.BusinessLine;

import java.util.List;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service
 * User: zjprevenge
 * Date: 2016/12/5
 * Time: 9:47
 */
public interface BusinessLineService {

    List<BusinessLine> getBusinessLineAll();

    /**
     * 通过id获取业务线
     *
     * @param id
     * @return
     */
    BusinessLine getBusinessLineById(Integer id);

    /**
     * 通过名称获取业务线
     *
     * @param name
     * @return
     */
    BusinessLine getBusinessLineByName(String name);

    /**
     * 添加业务线
     *
     * @param businessLine
     * @return
     */
    BusinessLine addBusinessLine(BusinessLine businessLine);

    /**
     * 更新业务线
     *
     * @param businessLine
     * @return
     */
    Integer updateBusinessLine(BusinessLine businessLine);

    /**
     * 删除业务线
     *
     * @param id
     * @return
     */
    Integer deleteBusinessLine(Integer id);

    void activeBusinessLine(Integer id);

    /**
     * 用户已经绑定的业务线
     *
     * @param userId 用户id
     * @return
     */
    List<BusinessLine> userBindBusinessLine(Integer userId);

    /**
     * 用户未绑定的业务线
     *
     * @param userId 用户id
     * @return
     */
    List<BusinessLine> userUnbindBusinessLine(Integer userId);
}
