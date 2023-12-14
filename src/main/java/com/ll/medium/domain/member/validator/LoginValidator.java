package com.ll.medium.domain.member.validator;

import com.ll.medium.domain.member.dto.request.MemberLoginRequest;
import com.ll.medium.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class LoginValidator implements Validator {

    private final static String MEMBER_ERROR_CODE = "memberServiceError";
    private final MemberService memberService;

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberLoginRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberLoginRequest request = (MemberLoginRequest) target;

        if (!memberService.checkUser(request.getMembername(), request.getPassword())) {
            errors.rejectValue("exception", MEMBER_ERROR_CODE, "아이디/비밀번호가 일치하지 않습니다.");
        }
    }

}
