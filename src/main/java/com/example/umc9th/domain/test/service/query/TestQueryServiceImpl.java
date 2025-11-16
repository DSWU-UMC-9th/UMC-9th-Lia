package com.example.umc9th.domain.test.service.query;

import com.example.umc9th.domain.test.exception.TestException;
import com.example.umc9th.domain.test.code.TestErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestQueryServiceImpl implements TestQueryService {

    @Override
    public void checkFlag(Long flag) {
        // flag가 1이면 테스트 예외 발생
        if (flag != null && flag == 1L) {
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}
