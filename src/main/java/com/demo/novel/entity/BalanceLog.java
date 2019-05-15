package com.demo.novel.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BalanceLog {
    private Long id;

    private String username;

    private String type;

    private String msg;

    private BigDecimal amount;

    private Date transtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTranstime() {
        return transtime;
    }

    public void setTranstime(Date transtime) {
        this.transtime = transtime;
    }
}