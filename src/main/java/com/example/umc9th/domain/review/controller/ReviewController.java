package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ApiResponse<List<Review>> getFilteredReviews(
            @RequestParam String storeName,
            @RequestParam Double minRating,
            @RequestParam Double maxRating
    ) {
        List<Review> reviews =
                reviewService.getFilteredReviews(storeName, minRating, maxRating);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }
}
