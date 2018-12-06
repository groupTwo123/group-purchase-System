package com.felix.grouppurchase.model;

import java.io.Serializable;

/**
 * @ClassName ShopCar
 * @Description TODO
 * @Author fangyong
 * @Date 2018/12/5 17:00
 **/
public class ShopCar implements Serializable {

    private String commodityId;
    private String commodityNumber;
    private String volumeId;
    private String userId;

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

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
