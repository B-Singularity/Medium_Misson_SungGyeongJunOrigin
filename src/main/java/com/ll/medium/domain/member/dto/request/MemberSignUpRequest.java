package com.ll.medium.domain.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberSignUpRequest {
        @Size(min = 3, max = 25, message = "아이디는 3자 이상 25자 이하로 입력 해야합니다.")
        @NotEmpty(message = "아이디는 필수항목입니다.")
        private final String membername;

        @NotEmpty(message = "비밀번호는 필수항목입니다.")
        private final String password;

        @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
        private final String passwordConfirm;

        @Email(message = "올바른 이메일 형식을 입력해 주시길 바랍니다.")
        @NotEmpty(message = "이메일은 필수항목입니다.")
        private final String email;

        public MemberSignUpRequest(String membername, String password, String passwordConfirm, String email) {
                this.membername = membername;
                this.password = password;
                this.passwordConfirm = passwordConfirm;
                this.email = email;

                // 유효성 검사
                if (!password.equals(passwordConfirm)) {
                        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
                }
        }
}


