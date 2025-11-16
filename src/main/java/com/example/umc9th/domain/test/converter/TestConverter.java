package com.example.umc9th.domain.test.converter;

import com.example.umc9th.domain.test.dto.res.TestResDTO;

public class TestConverter {

    // 객체 -> DTO (일반 테스트)
    public static TestResDTO.Testing toTestingDTO(String testing) {
        return TestResDTO.Testing.builder()
                .testString(testing)
                .build();
    }

    // 객체 -> DTO (예외 테스트)
    public static TestResDTO.Exception toExceptionDTO(String testing) {
        return TestResDTO.Exception.builder()
                .testString(testing)
                .build();
    }
}
