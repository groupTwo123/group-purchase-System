package com.felix.grouppurchase.model;

import java.io.Serializable;

/**
 * @Date: 2018/11/30 9:17
 * @Author: fangyong
 */
public class Admin implements Serializable{
    private String adminId;
    private String adminName;
    private String adminPassword;
    private String adminHead;


    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminHead() {
        return adminHead;
    }

    public void setAdminHead(String adminHead) {
        this.adminHead = adminHead;
    }
}