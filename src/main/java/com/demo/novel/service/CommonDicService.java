package com.demo.novel.service;

import com.demo.novel.entity.Dic;

import java.util.List;

public interface CommonDicService {
    List<Dic> queryDicList(String type);
}
