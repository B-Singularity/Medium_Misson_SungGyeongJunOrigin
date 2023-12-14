package com.ll.medium.domain.member.dto.response.response;

import com.ll.medium.domain.member.validator.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class MemberResponse {
    private final String membername;
    private final MemberRole memberRole;
}
