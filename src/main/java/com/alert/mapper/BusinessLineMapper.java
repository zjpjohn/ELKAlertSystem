package com.alert.mapper;

import com.alert.domain.BusinessLine;

import java.util.List;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.mapper
 * User: zjprevenge
 * Date: 2016/12/2
 * Time: 17:02
 */
public interface BusinessLineMapper {

    BusinessLine getBusinessLineById(Integer id);

    BusinessLine getBusinessLineByName(String name);

    List<BusinessLine> getBusinessLineAll();

    Integer addBusinessLine(BusinessLine businessLine);

    Integer updateBusinessLine(BusinessLine businessLine);

    Integer deleteBusinessLine(Integer id);

    /**
     * 激活已经删除的业务线
     *
     * @param id
     * @return
     */
    Integer activeBusinessLine(Integer id);

    /**
     * 获取用户已经绑定的业务线
     *
     * @param userId 用户id
     * @return
     */
    List<BusinessLine> getBindBusinessLines(Integer userId);

    /**
     * 获取用户未绑定的业务线
     *
     * @param userId 用户id
     * @return
     */
    List<BusinessLine> getUnbindBusinessLines(Integer userId);
}
