package com.felix.grouppurchase.model;

import java.io.Serializable;

/**
 * @Date: 2018/11/30 9:13
 * @Author: fangyong
 */
public class Seller implements Serializable {
    private String sellerId;
    private String sellerNickname;
    private String sellerName;
    private String sellerPassword;
    private String sellerIdentityId;
    private String sellerPhone;
    private String sellerEmail;
    private String storeName;
    private String storeArea;
    private Integer sellerPink;
    private String volumeId;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerNickname() {
        return sellerNickname;
    }

    public String getSellerPassword() {
        return sellerPassword;
    }

    public void setSellerPassword(String sellerPassword) {
        this.sellerPassword = sellerPassword;
    }

    public void setSellerNickname(String sellerNickname) {
        this.sellerNickname = sellerNickname;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerIdentityId() {
        return sellerIdentityId;
    }

    public void setSellerIdentityId(String sellerIdentityId) {
        this.sellerIdentityId = sellerIdentityId;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }

    public Integer getSellerPink() {
        return sellerPink;
    }

    public void setSellerPink(Integer sellerPink) {
        this.sellerPink = sellerPink;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }
}