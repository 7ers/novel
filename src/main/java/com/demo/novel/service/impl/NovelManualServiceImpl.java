package com.demo.novel.service.impl;

import com.demo.novel.dao.NovelBaseMapper;
import com.demo.novel.entity.NovelBase;
import com.demo.novel.service.NovelManualService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("NovelManualService")
public class NovelManualServiceImpl implements NovelManualService {

    @Autowired
    private NovelBaseMapper novelBaseMapper;

    /**
     * 查询小说列表
     * @return
     */
    @Override
    public List<NovelBase> getAll() {
        return novelBaseMapper.selectAll();
    }

    @Override
    public PageInfo<NovelBase> selectByEntity(NovelBase novelBase, int start, int length) {
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        List<NovelBase> novelList = novelBaseMapper.selectByEntity(novelBase) ;
        return new PageInfo<>(novelList);
    }

    @Override
    public int insert(NovelBase record) {
        return novelBaseMapper.insert(record);
    }

    @Override
    public  NovelBase selectByBookName(String bookname) {
        return novelBaseMapper.selectByBookName(bookname);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return novelBaseMapper.deleteByPrimaryKey(id);
    }
}
