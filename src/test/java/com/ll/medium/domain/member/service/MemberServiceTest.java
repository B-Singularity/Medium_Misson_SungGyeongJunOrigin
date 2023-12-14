package com.ll.medium.domain.member.service;

import com.ll.medium.domain.member.entity.Member;
import com.ll.medium.domain.member.repository.MemberRepository;
import com.ll.medium.domain.member.dto.request.MemberSignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("memberRegister")
    void registerMember_ValidMember_Success() {
        // Given
        MemberSignUpRequest memberSignUpRequest = MemberSignUpRequest.builder()
                .membername("username")
                .password("testpassword")
                .passwordConfirm("testpassword")
                .email("email@example.com")
                .build();

        Member newMember = Member.builder()
                .membername("username")
                .password("testpassword")
                .email("email@example.com")
                .build();

        // PasswordEncoder Mock 객체에 대한 동작 설정
        when(memberRepository.save(any(Member.class))).thenReturn(newMember);

        // When
        Member registeredMember = memberService.signUpMember(memberSignUpRequest);

        // Then
        assertEquals("username", registeredMember.getMembername());
        assertEquals("email@example.com", registeredMember.getEmail());
    }
}



