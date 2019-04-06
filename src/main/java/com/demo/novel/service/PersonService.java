package com.demo.novel.service;

import com.demo.novel.entity.Person;

import java.util.List;

public interface PersonService {
    public void insert(Person user);

    public void delete(int id);

    public Person select(int id);

    public List selectAll();

}
