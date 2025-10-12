package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "missions")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer rewardPoint;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private com.example.umc9th.domain.mission.entity.MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "mission")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "mission")
    private List<com.example.umc9th.domain.mission.entity.UserMission> userMissions = new ArrayList<>();
}
