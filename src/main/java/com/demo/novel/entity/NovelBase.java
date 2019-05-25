package com.demo.novel.entity;

import java.util.Date;

public class NovelBase {
    private Integer id;

    private String bookname;

    private String author;

    private String category;

    private String status;

    private Date updatetime;

    private String abstracts;

    private String novelid;

    private String cover;

    private String counts;

    private int readtimes;

    public String getNovelid() {
        return novelid;
    }

    public void setNovelid(String novelid) {
        this.novelid = novelid;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public int getReadtimes() {
        return readtimes;
    }

    public void setReadtimes(int readtimes) {
        this.readtimes = readtimes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getAbstract() {
        return abstracts;
    }

    public void setAbstract(String abstracts) {
        this.abstracts = abstracts == null ? null : abstracts.trim();
    }
}