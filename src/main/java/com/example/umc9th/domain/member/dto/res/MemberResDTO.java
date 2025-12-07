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

    // 로그인 응답 (세션 방식에서는 memberId 정도만 써도 되고,
    // JWT 단계에서는 accessToken까지 사용)
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ) {}
}
