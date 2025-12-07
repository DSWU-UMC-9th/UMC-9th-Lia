package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.Role;

public class MemberConverter {

    // DTO + 인코딩된 비밀번호 + Role -> Member 엔티티
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String encodedPassword,
            Role role
    ) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .password(encodedPassword)
                .role(role)
                .build();
    }

    // 회원가입 응답 DTO
    public static MemberResDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    // 로그인 응답 DTO (JWT 단계에서 accessToken 같이 조립할 때 사용)
    public static MemberResDTO.LoginDTO toLoginDTO(Member member, String accessToken) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }
}
