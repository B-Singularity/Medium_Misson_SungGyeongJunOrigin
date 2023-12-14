package com.ll.medium.domain.member.controller;

import com.ll.medium.domain.member.dto.request.MemberLoginRequest;
import com.ll.medium.domain.member.dto.request.MemberSignUpRequest;
import com.ll.medium.domain.member.service.MemberService;
import com.ll.medium.domain.member.validator.LoginValidator;
import com.ll.medium.global.util.ValidateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final LoginValidator loginValidator;
    private final ValidateUtil validateUtil;

    @GetMapping("/signup")
    public ModelAndView getSignupForm() {
        ModelAndView modelAndView = new ModelAndView("signup_form");
        modelAndView.addObject("memberSignUpForm", new MemberSignUpRequest("", "", "", ""));
        return modelAndView;
    }



    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody MemberSignUpRequest memberSignUpRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.toString());
        }

        if (!memberSignUpRequest.getPassword().equals(memberSignUpRequest.getPasswordConfirm())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        memberService.signUpMember(memberSignUpRequest);

        return ResponseEntity.ok("회원가입에 성공했습니다!");
    }

    @GetMapping("/login")
    public ModelAndView getLoginForm() {
        ModelAndView modelAndView = new ModelAndView("login_form");
        modelAndView.addObject("memberLoginRequest", new MemberLoginRequest("", ""));
        return modelAndView;
    }


    @PostMapping("/login")
    public ResponseEntity<?> memberLogin(@Valid MemberLoginRequest memberLoginRequest, BindingResult bindingResult) {
        loginValidator.validate(memberLoginRequest, bindingResult);

        if (validateUtil.hasErrors(bindingResult)) {
            return validateUtil.getErrors(bindingResult);
        }

        return ResponseEntity.ok(memberService.loginMember(memberLoginRequest));
    }
}


