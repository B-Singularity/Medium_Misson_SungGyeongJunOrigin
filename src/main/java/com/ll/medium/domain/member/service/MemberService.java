package com.ll.medium.domain.member.service;

import com.ll.medium.domain.member.entity.Member;
import com.ll.medium.domain.member.repository.MemberRepository;
import com.ll.medium.domain.member.dto.request.MemberLoginRequest;
import com.ll.medium.domain.member.dto.request.MemberSignUpRequest;
import com.ll.medium.domain.member.dto.response.response.MemberResponse;
import com.ll.medium.domain.member.validator.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signUpMember(MemberSignUpRequest memberSignUpRequest) {
        Member member = Member.builder()
                .membername(memberSignUpRequest.getMembername())
                .password(passwordEncoder.encode(memberSignUpRequest.getPassword()))
                .email(memberSignUpRequest.getEmail())
                .build();


        return memberRepository.save(member);
    }
    public MemberResponse loginMember(MemberLoginRequest memberLoginRequest) {
        Member member = memberRepository.findByMembername(memberLoginRequest.getMembername())
                .filter(value -> passwordEncoder.matches(memberLoginRequest.getPassword(), value.getPassword()))
                .orElseThrow(IllegalArgumentException::new);

        MemberRole memberRole = MemberRole.USER;
        if(checkAdmin(member.getMembername())) {
            memberRole = MemberRole.ADMIN;
        }

        return MemberResponse.builder()
                .membername(member.getMembername())
                .memberRole(memberRole)
                .build();
    }

    public boolean existsByUsername(String username) {
        Optional<Member> member = memberRepository.findByMembername(username);
        return member.isPresent();
    }

    public boolean existsByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.isPresent();
    }
    public boolean checkAdmin(String username) {
        return username.equals(MemberRole.ADMIN.getValue());
    }

    public boolean checkUser(String membername, String password) {
        Optional<Member> member = memberRepository.findByMembername(membername);

        return member.filter(value -> passwordEncoder.matches(password, value.getPassword()))
                .isPresent();
    }
}

