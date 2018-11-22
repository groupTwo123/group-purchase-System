package com.felix.grouppurchase.model;

import java.util.Date;


public class User {
    private Integer id;
    private String userName;
    private Integer gender;
    private Date birth;
    private String phone;
    private String email;
    private String password;
    private Integer type;
    private String area;
    private Integer level;
    private double vacancy;
    private String volumeManagerId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public double getVacancy() {
        return vacancy;
    }

    public void setVacancy(double vacancy) {
        this.vacancy = vacancy;
    }

    public String getVolumeManagerId() {
        return volumeManagerId;
    }

    public void setVolumeManagerId(String volumeManagerId) {
        this.volumeManagerId = volumeManagerId;
    }
}