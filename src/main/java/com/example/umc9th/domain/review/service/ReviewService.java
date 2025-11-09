package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.mission.entity.QMission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final EntityManager entityManager;

    public List<Review> getFilteredReviews(String storeName, Double minRating, Double maxRating) {
        QReview review = QReview.review;
        QMission mission = QMission.mission;

        JPAQuery<Review> query = new JPAQuery<>(entityManager);

        BooleanExpression predicate = mission.store.name.eq(storeName)
                .and(review.rating.between(minRating.shortValue(), maxRating.shortValue()));

        return query.select(review)
                .from(review)
                .join(review.mission, mission)
                .where(predicate)
                .fetch();
    }
}
