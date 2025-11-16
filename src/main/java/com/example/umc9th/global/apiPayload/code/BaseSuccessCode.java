package com.example.umc9th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {

    HttpStatus getStatus();  // HTTP 상태코드 (200, 201 등)
    String getCode();        // "COMMON200_1" 같은 내부용 코드
    String getMessage();     // "성공적으로 요청을 처리했습니다." 같은 메시지
}
