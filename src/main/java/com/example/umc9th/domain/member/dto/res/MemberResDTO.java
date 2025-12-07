package com.example.umc9th.domain.member.dto.res;

import lombok.Builder;

public class MemberResDTO {

    // 회원가입 응답
    @Builder
    public record JoinDTO(
            Long memberId,
            String name,
            String email,
            String phoneNumber
    ) {}


    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ) {}


}
