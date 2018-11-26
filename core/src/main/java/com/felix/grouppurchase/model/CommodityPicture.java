package com.felix.grouppurchase.model;

import java.io.Serializable;

/**
 * @Date: 2018/11/22 15:23
 * @Author: fangyong
 */
public class CommodityPicture implements Serializable {
    private Integer id;
    private String pictureBase64;
    private String commodityId;
    private Integer priority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureBase64() {
        return pictureBase64;
    }

    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}