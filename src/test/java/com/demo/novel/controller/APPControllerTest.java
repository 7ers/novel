package com.demo.novel.controller;

import com.demo.novel.controller.app.APPController;
import com.demo.novel.util.Constants;
import com.demo.novel.util.JsonResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APPControllerTest {
    @Test
    public void testLogin() {
        String username = "admin";
        String password = "123456";
        APPController appController = new APPController();
        JsonResult jr = appController.appLogin(username, password);
        if (jr != null) {
            Assert.assertEquals(Constants.RET_CODE_00000, jr.getCode());
        }
    }
}
