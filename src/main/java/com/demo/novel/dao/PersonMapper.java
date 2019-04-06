package com.demo.novel.dao;

import com.demo.novel.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper
public interface PersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);

    List selectAll();

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKeyWithBLOBs(Person record);

    int updateByPrimaryKey(Person record);
}