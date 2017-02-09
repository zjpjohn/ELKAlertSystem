package com.alert.domain;

import java.util.Date;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.domain
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 13:32
 */
public class ManagerOperationLog {
    private int id;
    private String managerName;
    private int managerId;
    private String operationType;
    private Date operationTime;
    private String operationContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }
}
