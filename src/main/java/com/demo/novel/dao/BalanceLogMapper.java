package com.demo.novel.dao;

import com.demo.novel.entity.BalanceLog;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BalanceLog record);

    int insertSelective(BalanceLog record);

    BalanceLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BalanceLog record);

    int updateByPrimaryKey(BalanceLog record);
}