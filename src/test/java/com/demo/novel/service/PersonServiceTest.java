package com.demo.novel.service;

import com.demo.novel.NovelApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;


public class PersonServiceTest extends NovelApplicationTests {
    @Autowired
    private PersonService personService;

    @Test
    public void testGetPersonById(){
        Assert.assertSame(1,personService.select(1).getId());
    }
}
