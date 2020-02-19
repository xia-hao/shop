package com.shop.domain;

import java.util.Date;

public class PayRecord {
    private Integer uid;

    private Integer frontSum;

    private Integer lastSum;

    private String bid;

    private Date payDate;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFrontSum() {
        return frontSum;
    }

    public void setFrontSum(Integer frontSum) {
        this.frontSum = frontSum;
    }

    public Integer getLastSum() {
        return lastSum;
    }

    public void setLastSum(Integer lastSum) {
        this.lastSum = lastSum;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}