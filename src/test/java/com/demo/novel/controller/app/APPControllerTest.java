package com.demo.novel.controller.app;

import com.demo.novel.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.apache.shiro.mgt.SecurityManager;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class APPControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private MockHttpSession session;

    private Subject subject;

    private MockHttpServletRequest mockHttpServletRequest;

    private MockHttpServletResponse mockHttpServletResponse;

    @Resource
    private SecurityManager mockSecurityManager;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        mockHttpServletRequest = new MockHttpServletRequest(wac.getServletContext());
        mockHttpServletResponse = new MockHttpServletResponse();
        session = new MockHttpSession(wac.getServletContext());
        mockHttpServletRequest.setSession(session);
        SecurityUtils.setSecurityManager(mockSecurityManager);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void appLogin() throws Exception {
//        subject = new WebSubject.Builder(mockHttpServletRequest, mockHttpServletResponse)
//                .buildWebSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456", false);
//        subject.login(token);
//        ThreadContext.bind(subject);
        mvc.perform(MockMvcRequestBuilders.post("/app/appLogin")
                .param("username","admin").param("password","123456")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("00000"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void register() {
    }
}