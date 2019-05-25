package com.demo.novel.service;

import com.demo.novel.entity.NovelBase;
import com.demo.novel.entity.NovelChapter;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NovelManualService {
    List<NovelBase> getAll();
    PageInfo<NovelBase> selectByEntity(NovelBase novelBase,int start, int length);
    int insert(NovelBase record);
    NovelBase selectByBookName(String bookname);
    int deleteByPrimaryKey(Integer id);
    PageInfo<NovelBase> getNovelListByCategory(String category, int start, int length);
    NovelBase selectByNovelId(String novel_id);

    List<NovelChapter> qryChapterListByNovelId(String novel_id);

    PageInfo<NovelChapter> chapterListDetailByNovelId(String novel_id, int start, int length);
}
