package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    // 1) 내가 작성한 리뷰 목록 (페이징)
    @Builder
    public record MyReviewPreviewListDTO(
            List<MyReviewPreviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MyReviewPreviewDTO(
            String storeName,
            Short rating,
            String body,
            LocalDate createdAt
    ) {}
}
