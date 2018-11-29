package com.felix.grouppurchase.model;

import java.io.Serializable;

/**
 * @Date: 2018/11/28 16:13
 * @Author: fangyong
 */
public class Role implements Serializable {

    private Integer id;
    private String name;
    private Integer role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}