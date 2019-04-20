package com.demo.novel.service;

import com.demo.novel.entity.NovelBase;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NovelManualService {
    List<NovelBase> getAll();
    PageInfo<NovelBase> selectByEntity(NovelBase novelBase,int start, int length);
    int insert(NovelBase record);
    NovelBase selectByBookName(String bookname);
    int deleteByPrimaryKey(Integer id);
}
