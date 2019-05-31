package com.demo.novel.dao;

import com.demo.novel.entity.Dic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface DicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dic record);

    List<Dic> selectByType(String type);

    int updateByPrimaryKey(Dic record);
}