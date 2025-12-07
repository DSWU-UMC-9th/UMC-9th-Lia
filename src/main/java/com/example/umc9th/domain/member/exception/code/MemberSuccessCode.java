package com.example.umc9th.domain.member.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    // 회원가입 성공
    CREATED(
            HttpStatus.CREATED,
            "MEMBER201_1",
            "회원가입이 완료되었습니다."
    ),

    // (JWT 방식 로그인 성공에 쓸 예정)
    LOGIN_SUCCESS(
            HttpStatus.OK,
            "MEMBER200_1",
            "로그인에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
