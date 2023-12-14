package com.ll.medium.domain.member.validator;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_Member");

    MemberRole(String value) {
        this.value = value;
    }

    private final String value;
}
