package com.ll.medium.domain.member.controller;

import com.ll.medium.domain.member.dto.request.MemberSignUpRequest;
import com.ll.medium.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(MemberController.class)
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testRegisterMemberWithEncryptedPassword() throws Exception {
        // Given
        MemberSignUpRequest memberSignUpRequest = new MemberSignUpRequest("testmember", "testpassword", "testpassword", "test@example.com");

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/member/signup")
                        .param("membername", memberSignUpRequest.getMembername())
                        .param("password", memberSignUpRequest.getPassword())
                        .param("passwordConfirm", memberSignUpRequest.getPasswordConfirm())
                        .param("email", memberSignUpRequest.getEmail()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

    }
}



