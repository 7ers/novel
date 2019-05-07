package com.demo.novel.entity;

public class RolePerms {

    private Integer roleid;

    private String permsid;

    /**
     * @return roleId
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getPermsid() {
        return permsid;
    }

    public void setPermsid(String permsid) {
        this.permsid = permsid;
    }
}