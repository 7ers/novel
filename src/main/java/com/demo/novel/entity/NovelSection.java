package com.demo.novel.entity;

public class NovelSection {
    private Integer id;

    private Integer novelid;

    private String sectionname;

    private String updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNovelid() {
        return novelid;
    }

    public void setNovelid(Integer novelid) {
        this.novelid = novelid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname == null ? null : sectionname.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }
}