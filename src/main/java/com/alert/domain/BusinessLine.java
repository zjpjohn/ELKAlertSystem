package com.alert.domain;

import java.util.Date;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.domain
 * User: zjprevenge
 * Date: 2016/12/1
 * Time: 16:42
 */
public class BusinessLine {

    private Integer id;
    private String tag;
    private String businessName;
    private Date createTime;
    private Date updateTime;
    private int status;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
