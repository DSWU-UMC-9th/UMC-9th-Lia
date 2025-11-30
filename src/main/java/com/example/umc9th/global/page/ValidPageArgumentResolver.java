package com.example.umc9th.global.page;

import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ValidPageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidPage.class)
                && (parameter.getParameterType().equals(Integer.class)
                || parameter.getParameterType().equals(int.class));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        String pageParam = webRequest.getParameter("page");
        int page;

        if (pageParam == null || pageParam.isBlank()) {
            // 쿼리스트링이 없으면 1페이지로 간주
            page = 1;
        } else {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                throw new GeneralException(GeneralErrorCode.INVALID_PAGE);
            }
        }

        if (page <= 0) {
            throw new GeneralException(GeneralErrorCode.INVALID_PAGE);
        }

        // 서비스에서는 0-base 로 쓰기 편하게 -1 해서 넘겨줌
        return page - 1;
    }
}
