package com.demo.novel.dao;

import com.demo.novel.entity.NovelChapter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovelChapterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NovelChapter record);

    int insertSelective(NovelChapter record);

    NovelChapter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NovelChapter record);

    int updateByPrimaryKeyWithBLOBs(NovelChapter record);

    int updateByPrimaryKey(NovelChapter record);

    int selectByNovelId(Integer sectionid);

    List<NovelChapter> selectChapterList(Integer sectionid);
}