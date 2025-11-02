package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("""
        SELECT mm
        FROM MemberMission mm
        JOIN FETCH mm.mission
        WHERE mm.member.id = :memberId
        AND mm.status = :status
    """)
    List<MemberMission> findByMemberAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status
    );

    @Query("""
        SELECT mm
        FROM MemberMission mm
        JOIN FETCH mm.mission m
        WHERE mm.member.id = :memberId
        AND mm.status = 'COMPLETE'
        AND m.id NOT IN (
            SELECT r.mission.id FROM Review r WHERE r.member.id = :memberId
        )
    """)
    List<MemberMission> findCompletedMissionsWithoutReview(@Param("memberId") Long memberId);
}
