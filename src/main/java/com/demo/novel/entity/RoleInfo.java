package com.demo.novel.entity;

import java.util.Date;

public class RoleInfo {
    public String get;
    private Integer roleid;

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    private String available;

    private String roledesc;

    private String roleval;

    private Date createtime;

    private Date updatetime;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
    }

    public String getRoleval() {
        return roleval;
    }

    public void setRoleval(String roleval) {
        this.roleval = roleval == null ? null : roleval.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}