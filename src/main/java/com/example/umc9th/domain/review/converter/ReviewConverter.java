package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class ReviewConverter {

    public static ReviewResDTO.MyReviewPreviewListDTO toMyReviewPreviewListDTO(Page<Review> result) {
        return ReviewResDTO.MyReviewPreviewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toMyReviewPreviewDTO)
                        .toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.MyReviewPreviewDTO toMyReviewPreviewDTO(Review review) {
        return ReviewResDTO.MyReviewPreviewDTO.builder()
                // Review -> Mission -> Store -> name
                .storeName(review.getMission().getStore().getName())
                .rating(review.getRating())
                .body(review.getContent())
                //.createdAt(LocalDate.from(review.getCreatedAt()))
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }
}
