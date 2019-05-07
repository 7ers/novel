package com.demo.novel.entity;

import java.util.Date;

public class PermsInfo {
    private Integer permsid;

    private String permsname;

    private String permstype;

    private String permsval;

    private Date createtime;

    private Date updatetime;

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    private String checked;

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    private Integer parentid;

    public Integer getPermsid() {
        return permsid;
    }

    public void setPermsid(Integer permsid) {
        this.permsid = permsid;
    }

    public String getPermsname() {
        return permsname;
    }

    public void setPermsname(String permsname) {
        this.permsname = permsname == null ? null : permsname.trim();
    }

    public String getPermstype() {
        return permstype;
    }

    public void setPermstype(String permstype) {
        this.permstype = permstype;
    }

    public String getPermsval() {
        return permsval;
    }

    public void setPermsval(String permsval) {
        this.permsval = permsval == null ? null : permsval.trim();
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