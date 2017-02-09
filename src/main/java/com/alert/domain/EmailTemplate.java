package com.alert.domain;

import java.util.Date;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.domain
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 13:31
 */
public class EmailTemplate {
    private int id;
    private String templateName;
    private String templateValue;
    private Date createTime;
    private Date updateTime;
    private int status;
    //是否启用模板
    private int active;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateValue() {
        return templateValue;
    }

    public void setTemplateValue(String templateValue) {
        this.templateValue = templateValue;
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
