package com.demo.novel.service.impl;

import com.demo.novel.dao.PersonMapper;
import com.demo.novel.entity.Person;
import com.demo.novel.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public void insert(Person user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Person select(int id) {
        return personMapper.selectByPrimaryKey(id);
    }

    @Override
    public List selectAll(){return personMapper.selectAll();}
}
