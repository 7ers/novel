package com.demo.novel.service.impl;

import com.demo.novel.dao.NovelBaseMapper;
import com.demo.novel.dao.NovelChapterMapper;
import com.demo.novel.entity.NovelBase;
import com.demo.novel.entity.NovelChapter;
import com.demo.novel.service.NovelManualService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("NovelManualService")
public class NovelManualServiceImpl implements NovelManualService {

    @Resource
    private NovelBaseMapper novelBaseMapper;

    @Resource
    private NovelChapterMapper novelChapterMapper;

    /**
     * 查询小说列表
     * @return
     */
    @Override
    public List<NovelBase> getAll() {
        return novelBaseMapper.selectAll();
    }

    /**
     * 查询小说列表
     * @param category
     * @param start
     * @param length
     * @return
     */
    @Override
    public PageInfo<NovelBase> getNovelListByCategory(String category, int start, int length){
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        List<NovelBase> novelList = novelList = novelBaseMapper.selectByCategory(category);
        return new PageInfo<>(novelList);
    }

    @Override
    public NovelBase selectByNovelId(String novel_id) {
        return novelBaseMapper.selectByNovelId(novel_id);
    }

    @Override
    public List<NovelChapter> qryChapterListByNovelId(String novel_id) {
        return novelChapterMapper.qryChapterListByNovelId(novel_id);
    }

    @Override
    public PageInfo<NovelChapter> chapterListDetailByNovelId(String novel_id, int start, int length) {
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        List<NovelChapter> chapterList = novelChapterMapper.selectListDetailByNovelId(novel_id);
        return new PageInfo<>(chapterList);
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
