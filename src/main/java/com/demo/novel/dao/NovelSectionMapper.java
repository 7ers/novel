package com.demo.novel.dao;

import com.demo.novel.entity.NovelSection;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface NovelSectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NovelSection record);

    int insertSelective(NovelSection record);

    NovelSection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NovelSection record);

    int updateByPrimaryKey(NovelSection record);
}