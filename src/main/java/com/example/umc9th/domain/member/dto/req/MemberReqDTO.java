package com.example.umc9th.domain.member.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MemberReqDTO {

    // 회원가입 요청
    public record JoinDTO(
            @NotBlank
            @Size(max = 30)
            String name,

            @NotBlank
            @Email
            @Size(max = 100)
            String email,

            @NotBlank
            @Size(max = 20)
            String phoneNumber,

            @NotBlank
            String password
    ) {}

    // 로그인 요청 (JWT 단계에서 사용)
    public record LoginDTO(
            @NotBlank
            @Email
            String email,

            @NotBlank
            String password
    ) {}
}
