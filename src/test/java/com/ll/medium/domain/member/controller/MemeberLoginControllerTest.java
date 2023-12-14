package com.ll.medium.domain.member.controller;

import com.ll.medium.domain.member.dto.request.MemberLoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@AutoConfigureMockMvc
public class MemeberLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginMember() throws Exception {
        MemberLoginRequest memberLoginRequest1 = new MemberLoginRequest("testmember", "testpassword");
        mockMvc.perform(MockMvcRequestBuilders.post("/member/login")
                        .param("membername", memberLoginRequest1.getMembername())
                        .param("password", memberLoginRequest1.getPassword()))
                .andExpect(status().isOk())
                .andExpect(view().name("login-success")) // 예상되는 뷰 이름
                .andExpect(model().attributeExists("loggedInMember")); // 로그인된 멤버의 속성 확인
    }
}
