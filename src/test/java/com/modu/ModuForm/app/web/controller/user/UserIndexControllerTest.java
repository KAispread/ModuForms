package com.modu.ModuForm.app.web.controller.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserIndexController.class)
public class UserIndexControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void 로그인_페이지_반환() throws Exception {
        String comment = "로그인이 필요합니다.";

        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(comment));
    }
}