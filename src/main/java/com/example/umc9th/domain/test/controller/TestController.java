package com.example.umc9th.domain.test.controller;

import com.example.umc9th.domain.test.converter.TestConverter;
import com.example.umc9th.domain.test.dto.res.TestResDTO;
import com.example.umc9th.domain.test.service.query.TestQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestQueryService testQueryService;

    // GET /temp/test
    @GetMapping("/test")
    public ApiResponse<TestResDTO.Testing> test() {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                TestConverter.toTestingDTO("This is Test!")
        );
    }

    // GET /temp/exception?flag=1
    @GetMapping("/exception")
    public ApiResponse<TestResDTO.Exception> exception(@RequestParam Long flag) {

        // flag 값에 따라 예외를 던질지 말지 결정
        testQueryService.checkFlag(flag);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                TestConverter.toExceptionDTO("This is Test!")
        );
    }
}
