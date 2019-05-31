package com.demo.novel.dao;

import com.demo.novel.entity.NovelChapter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface NovelChapterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NovelChapter record);

    NovelChapter selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(NovelChapter record);

    int selectByNovelId(String sectionid);

    List<NovelChapter> qryChapterListByNovelId(String novel_id);

    List<NovelChapter> selectListDetailByNovelId(String novel_id);
}