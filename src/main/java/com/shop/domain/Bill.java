package com.shop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Bill {
    private String id;

    private Integer uid;

    private Integer pid;

    private Integer money;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date billDate;

    private String billStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus == null ? null : billStatus.trim();
    }
}