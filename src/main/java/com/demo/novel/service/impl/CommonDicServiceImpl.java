package com.demo.novel.service.impl;

import com.demo.novel.dao.DicMapper;
import com.demo.novel.entity.Dic;
import com.demo.novel.service.CommonDicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommonDicServiceImpl implements CommonDicService {
    private static Logger logger = LoggerFactory.getLogger(CommonDicServiceImpl.class);

    @Resource
    DicMapper dicMapper;


    @Override
    public List<Dic> queryDicList(String type) {
        return dicMapper.selectByType(type);
    }
}
