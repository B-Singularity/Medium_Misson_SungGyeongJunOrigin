package com.ll.medium.domain.member.repository;

import com.ll.medium.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.data.jpa.repository.JpaRepository;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("registerTest")
    public void registerMember() {
        //given
        final Member member = new Member().builder()
                .id(1L)
                .membername("membername1")
                .password("password1")
                .email("email1")
                .build();
        //when
        final Member result = memberRepository.save(member);

        //then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getMembername()).isEqualTo("membername1");
        assertThat(result.getPassword()).isEqualTo("password1");
        assertThat(result.getEmail()).isEqualTo("email1");
    }
}
