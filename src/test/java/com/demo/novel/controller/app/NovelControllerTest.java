package com.demo.novel.controller.app;

import com.demo.novel.entity.UserInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NovelControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private MockHttpSession session;



    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
        UserInfo user =new UserInfo();
        user.setUsername("admin");
        user.setPwd("123456");
        session.setAttribute("user",user); //拦截器那边会判断用户是否登录，所以这里注入一个用户
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void novelList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/app/novellist")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("00000"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void novelListByCategory() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/app/novellistByCategory")
                .param("category","n").param("start","1").param("length","10")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("00000"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void novelcategory() {
    }

    @Test
    public void novelDetail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/app/novelDetail")
                .param("novel_id","1025")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("00000"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void chapterListDetail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/app/chapterListDetail")
                .param("novel_id","1025")
                .param("start","1")
                .param("length","10")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("00000"))
                .andDo(MockMvcResultHandlers.print());
    }
}