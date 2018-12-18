package com.felix.grouppurchase.model;

import java.io.Serializable;

/**
 * @ClassName Article
 * @Description TODO
 * @Author fangyong
 * @Date 2018/12/18 16:08
 **/
public class Article implements Serializable {

    private String id;
    private String commodityId;
    private String article;
    private Integer type;
    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
