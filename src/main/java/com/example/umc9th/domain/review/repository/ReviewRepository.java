package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query("""
        SELECT mm
        FROM MemberMission mm
        WHERE mm.member.id = :memberId
        AND mm.status = 'COMPLETE'
        AND mm.mission.id NOT IN (
            SELECT r.mission.id FROM Review r WHERE r.member.id = :memberId
        )
    """)
    List<MemberMission> findCompletedMissionsWithoutReview(@Param("memberId") Long memberId);
    List<Review> findByMissionStoreNameAndRatingBetween(
            @Param("storeName") String storeName,
            @Param("minRating") Short minRating,
            @Param("maxRating") Short maxRating
    );
}
