package com.felix.grouppurchase.model;

import java.io.Serializable;

/**
 * @Date: 2018/11/22 15:25
 * @Author: fangyong
 */
public class CommodityType implements Serializable {
    private Integer id;
    private String name;
    private String pink;

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

    public String getPink() {
        return pink;
    }

    public void setPink(String pink) {
        this.pink = pink;
    }
}