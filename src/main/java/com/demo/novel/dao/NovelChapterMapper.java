package com.demo.novel.dao;

import com.demo.novel.entity.NovelChapter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovelChapterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NovelChapter record);

    NovelChapter selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(NovelChapter record);

    int selectByNovelId(String sectionid);

    List<NovelChapter> selectChapterList(Integer sectionid);
}