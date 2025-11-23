package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {

    private static final Long HARD_CODED_MEMBER_ID = 1L; // 하드코딩 유저 ID

    private final ReviewService reviewService;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;

    @GetMapping
    @Operation(
            summary = "리뷰 필터링 조회",
            description = "가게 이름, 최소 평점, 최대 평점으로 리뷰를 필터링하여 조회합니다."
    )
    public ApiResponse<List<Review>> getFilteredReviews(
            @RequestParam String storeName,
            @RequestParam Double minRating,
            @RequestParam Double maxRating
    ) {
        List<Review> reviews =
                reviewService.getFilteredReviews(storeName, minRating, maxRating);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }

    /**
     * 미션에 대한 리뷰 작성 API
     * POST /api/reviews/missions/{missionId}/reviews
     */
    @PostMapping("/missions/{missionId}/reviews")
    @Operation(
            summary = "미션에 리뷰 추가",
            description = "로그인이 없어 memberId=1을 하드코딩해서 해당 미션에 리뷰를 작성합니다."
    )
    public ApiResponse<Review> addReview(@PathVariable Long missionId,
                                         @RequestBody Review request) {

        // 1) 하드코딩된 회원 조회
        Member member = memberRepository.findById(HARD_CODED_MEMBER_ID)
                .orElseThrow(() -> new IllegalArgumentException("하드코딩한 회원이 DB에 없습니다."));

        // 2) 미션 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        // 3) 리뷰 엔티티 생성 (Member + Mission 연결)
        Review review = Review.builder()
                .member(member)
                .mission(mission)
                .rating(request.getRating())
                .content(request.getContent())
                .build();

        // 4) 저장
        Review saved = reviewRepository.save(review);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, saved);
    }
}
