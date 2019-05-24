package com.demo.novel.dao;

import com.demo.novel.entity.NovelBase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovelBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NovelBase record);

    NovelBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(NovelBase record);

    List<NovelBase> selectAll();

    List<NovelBase> selectByEntity(NovelBase record);

    NovelBase selectByBookName(String bookname);

    List<NovelBase> selectByCategory(String category);
}