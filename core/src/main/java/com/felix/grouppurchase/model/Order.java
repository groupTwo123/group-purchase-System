package com.felix.grouppurchase.model;

import java.io.Serializable;

/**
 * @Date: 2018/11/22 15:11
 * @Author: fangyong
 */
public class Order implements Serializable {
    private Integer id;
    private String orderId;
    private String userId;
    private String commodityId;
    private String commodityNumber;
    private String money;
    private Integer state;
    private String rebatePrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(String commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRebatePrice() {
        return rebatePrice;
    }

    public void setRebatePrice(String rebatePrice) {
        this.rebatePrice = rebatePrice;
    }
}